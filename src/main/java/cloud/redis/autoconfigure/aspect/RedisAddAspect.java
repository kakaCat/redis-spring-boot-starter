//package cloud.redis.autoconfigure.aspect;
//
//import cloud.redis.autoconfigure.annotation.CacheRedisAdd;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * Created by JAVA on 2018/5/22.
// */
//
//
//@Aspect
////保证该AOP在@Transactional之前执行
//@Order(-1)
//@Component
//public class RedisAddAspect {
//    private static final Logger logger = LoggerFactory.getLogger(RedisAddAspect.class);
//
//
//    @Before("@annotation(cache)")
//    public void changeDataSource(JoinPoint point, CacheRedisAdd cache)
//            throws Throwable {
//
//        String name = cache.value();
//        String key = cache.key();
//        long expire =cache.expire();
//
//
//
//    }
//
//
//    @After("@annotation(cache)")
//    public void restoreDataSource(JoinPoint point, CacheRedisAdd cache) {
//        String name = cache.value();
//        String key = cache.key();
//        long expire =cache.expire();
//
//
//
//    }
//}
