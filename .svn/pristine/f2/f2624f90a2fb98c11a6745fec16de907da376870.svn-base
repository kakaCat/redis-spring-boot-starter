package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisStringRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by JAVA on 2017/10/12.
 */
@Component
public class RedisStringRepository implements RedisStringRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisStringRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }


    public void test(){
        ValueOperations<String, Object> saveBean = redisTemplate.opsForValue();
    }

    @Override
    public boolean saveBean(String key, Object value) {

        try {
            ValueOperations<String, Object> saveBean = redisTemplate.opsForValue();
            saveBean.set(key,value);

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }

    @Override
    public boolean saveBeanExp(String key, Object value,int seconds) {

        try {
            ValueOperations<String, Object> saveBean = redisTemplate.opsForValue();
            saveBean.set(key,value,seconds, TimeUnit.SECONDS);

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }

    @Override
    public Object getBean(String key) {

        Object value = (Object)redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return value;
    }

    @Override
    public boolean saveSeq(String key, long seqNo) {

        try {
            redisTemplate.delete(key);
            redisTemplate.opsForValue().increment(key, seqNo);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, seqNo[" + seqNo + "]", t);
        }
        return false;
    }


    @Override
    public boolean saveNX(String key, String val) {
        /** 设置成功，返回 1 设置失败，返回 0 **/
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.setNX(key.getBytes(), val.getBytes());
        });
    }

    @Override
    public boolean delKey(String key) {
        try {

            redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key.getBytes()));

            return true;
        } catch (Throwable t) {
            logger.error("删除缓存[" + key + "]失败, ", t);
        }
        return false;
    }
}
