package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisListRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAVA on 2017/10/12.
 */
@Repository
public class RedisListRepository implements RedisListRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisListRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void test(){
        ListOperations<String, Object> list = redisTemplate.opsForList();
    }


    @Override
    public boolean saveToQueue(String key, String val, long size) {
        try {
            ListOperations<String, String> lo = redisTemplate.opsForList();

            if (size > 0 && lo.size(key) >= size) {
                lo.rightPop(key);
            }
            lo.leftPush(key, val);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, val[" + val + "],size[" + size + "]", t);
        }
        return false;
    }

    //取出多个数据
    @Override
    public List<String> getFromQueue(String key, long size) {
        boolean flag = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.exists(key.getBytes());
        });

        if (flag) {
            return new ArrayList<>();
        }
        ListOperations<String, String> lo = redisTemplate.opsForList();
        if (size > 0) {
            return lo.range(key, 0, size - 1);
        } else {
            return lo.range(key, 0, lo.size(key) - 1);
        }
    }

    //取出一个
    @Override
    public String popQueue(String key) {
        return (String)redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long lpushx(String key, String value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    @Override
    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }


    //分页
    @Override
    public List<String> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public String lpop(String key) {
        return (String)redisTemplate.opsForList().leftPop(key);
    }

}
