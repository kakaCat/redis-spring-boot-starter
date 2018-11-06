//package cloud.redis.autoconfigure.annotation;
//
//import org.springframework.core.annotation.AliasFor;
//
//import java.lang.annotation.*;
//
///**
// * Created by JAVA on 2018/5/22.
// */
//@Target({ElementType.TYPE,ElementType.METHOD})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface CacheRedisAdd {
//
//    @AliasFor("cacheNames")
//    String[] value() default {};
//
//    @AliasFor("value")
//    String[] cacheNames() default {};
//
//    String key() default "";
//
//    String keyGenerator() default "";
//
//    String cacheManager() default "";
//
//    String cacheResolver() default "";
//
//    String condition() default "";
//
//    String unless() default "";
//
//    long expire() default 0;
//
//}
