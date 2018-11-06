package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisSetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by JAVA on 2017/10/12.
 * @author yun
 */
@Repository
public class RedisSetRepository implements RedisSetRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisSetRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void test(){

        SetOperations<String, Object> set=   redisTemplate.opsForSet();
    }


    @Override
    public boolean saveToSet(String key, Object value) {
        try {
            SetOperations<String, Object> so = redisTemplate.opsForSet();

            so.add(key, value);

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }

    @Override
    public String getFromSet(String key) {

        String result = null;

        try {
            result = (String)redisTemplate.opsForSet().pop(key);

            return result;
        } catch (Throwable t) {
            logger.error("缓存SET [" + key + "]失败, ", t);
        }

        return null;
    }

    @Override
    public Set<Object> listSet(String setName) {
        return redisTemplate.opsForHash().keys(setName);
    }




}
