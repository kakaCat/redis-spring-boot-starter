package cloud.redis.autoconfigure.data;


import cloud.redis.repositories.*;
import cloud.redis.repositories.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by JAVA on 2018/2/28.
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;


    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"redisGenericRepo"}
    )

    public RedisGenericRepo redisGenericRepo(RedisTemplate redisTemplate) {

        return new RedisGenericRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"redisHashRepo"}
    )

    public RedisHashRepo redisHashRepo(RedisTemplate redisTemplate) {

        return new RedisHashRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"redisListRepo"}
    )

    public RedisListRepo redisListRepo(RedisTemplate redisTemplate) {

        return new RedisListRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"redisSetRepo"}
    )

    public RedisSetRepo redisSetRepo(RedisTemplate redisTemplate) {

        return new RedisSetRepository(redisTemplate);
    }


    @Bean
    @ConditionalOnMissingBean(
            name = {"redisStringRepo"}
    )

    public RedisStringRepo redisStringRepo(RedisTemplate redisTemplate) {

        return new RedisStringRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"redisZsetRepo"}
    )

    public RedisZsetRepo redisZsetRepo(RedisTemplate redisTemplate) {

        return new RedisZsetRepository(redisTemplate);
    }






}
