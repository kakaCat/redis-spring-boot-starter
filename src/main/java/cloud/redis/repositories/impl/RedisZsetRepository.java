package cloud.redis.repositories.impl;


import cloud.redis.repositories.RedisZsetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by JAVA on 2017/10/12.
 */
@Repository
public class RedisZsetRepository implements RedisZsetRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisZsetRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void test(){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();


    }


    @Override
    public Set<RedisZSetCommands.Tuple> listSortedsetRev(String key, int start, int end) {
        return (Set<RedisZSetCommands.Tuple>)redisTemplate.execute((RedisCallback<Set<RedisZSetCommands.Tuple>>) connection -> {
            return connection.zRevRangeWithScores(key.getBytes(), start, end);
        });
    }

    @Override
    public Long getRankRev(String key, String member) {

        return (Long)redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.zRevRank(key.getBytes(), member.getBytes());
        });

    }

    @Override
    public Double getMemberScore(String key, String member) {
        return (Double)redisTemplate.execute((RedisCallback<Double>) connection -> {
            return connection.zScore(key.getBytes(), member.getBytes());
        });
    }

    @Override
    public boolean saveToSortedset(String key, Long score, String member) {

        try {
            redisTemplate.execute(
                    (RedisCallback<Boolean>) connection -> connection.zAdd(key.getBytes(), score, member.getBytes()));

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, member[" + member + "],score[" + score + "]", t);
        }
        return false;
    }

    @Override
    public boolean delFromSortedset(String key, String member) {

        try {
            redisTemplate
                    .execute((RedisCallback<Long>) connection -> connection.zRem(key.getBytes(), member.getBytes()));

            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, member[" + member + "]", t);
        }
        return false;
    }

    @Override
    public Set<Object> SortedsetDesc(String key, int start, int end) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.reverseRange(key,start,end);
    }


}
