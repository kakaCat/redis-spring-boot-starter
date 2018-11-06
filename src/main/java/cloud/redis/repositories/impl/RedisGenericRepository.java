package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisGenericRepo;
import cloud.redis.repositories.RedisZsetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by JAVA on 2017/10/25.
 */
@Repository
public class RedisGenericRepository implements RedisGenericRepo {

    private static final int INIT_PAGE = 1;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;
    @Autowired
    private RedisZsetRepo redisZsetRepo;

    @Autowired
    public RedisGenericRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @Override
    public boolean isCached(String key) {
        try {

            return (boolean)redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                return connection.exists(key.getBytes());
            });

        } catch (Throwable t) {
            logger.error("删除缓存[" + key + "]失败, ", t);
        }
        return false;
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

    @Override
    public boolean setExpire(String key, int seconds) {
        try {
            redisTemplate
                    .execute((RedisCallback<Boolean>) connection -> connection.expire(key.getBytes(), seconds));


            return true;
        } catch (Throwable t) {
            logger.error("设置缓存[" + key + "]有效时间失败, seconds[" + seconds + "]", t);
        }
        return false;
    }

    @Override
    public boolean insertPage(String repoName, String key, String value) {
        Long score= System.currentTimeMillis();
        try {

            ValueOperations<String, Object> saveBean = redisTemplate.opsForValue();
            saveBean.set(key,value);

            redisTemplate.execute(
                    (RedisCallback<Boolean>) connection -> connection.zAdd(repoName.getBytes(), score, value.getBytes()));

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, member[" + value + "],score[" + score + "]", t);
        }
        return false;
    }

    @Override
    public Set<Object> getPage(String repoName, int pageNo, int pageSize) {
        int start =0 ;
        int end = 0;
        if(INIT_PAGE==pageNo){
            end=pageSize-1;
        }else {
            start=pageNo*(pageSize-1);
            end=pageNo*pageSize-1;
        }

        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.reverseRange(repoName,start,end);
    }
}
