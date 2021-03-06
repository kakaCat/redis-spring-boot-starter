package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisHashRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * Created by JAVA on 2017/10/12.
 */
@Configuration
public class RedisHashRepository implements RedisHashRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisHashRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void test(){
        redisTemplate.opsForHash();
    }

    //添加一个map
    @Override
    public boolean hashput(String hName, String key, String value) {

        try {
            redisTemplate.opsForHash().put(hName, key, value);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + hName + "]失败, hName[" + key + "],value[" + value + "]", t);
        }
        return false;
    }

    @Override
    public Map<Object, Object> hgetAll(String key) {

        return redisTemplate.opsForHash().entries(key);
    }

    //获取map的value值
    @Override
    public Object hashGet(String key, String hKey) {
        return redisTemplate.opsForHash().get(key, hKey);
    }

    @Override
    public boolean IshashCached(String key, String hKey) {
        return (boolean)redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.hExists(key.getBytes(), hKey.getBytes());
        });
    }

    @Override
    public boolean delFromMap(String key, String hKey) {

        try {
            redisTemplate
                    .execute((RedisCallback<Long>) connection -> connection.hDel(key.getBytes(), hKey.getBytes()));
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, hKey[" + hKey + "]", t);
        }
        return false;
    }

    @Override
    public boolean hsetnx(String key, String hKey, String value) {
        try {
            redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.hSetNX(key.getBytes(),
                    key.getBytes(), value.getBytes()));
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, hKey[" + hKey + "]", t);
        }
        return false;
    }


}
