package cloud.redis.repositories;

import java.util.Set;

/**
 * Created by JAVA on 2017/10/12.
 */
public interface RedisSetRepo {

    /**
     * 将数据存入缓存的集合中
     *
     * @param key
     * @param val
     * @return
     */
    boolean saveToSet(String key, Object value);

    /**
     *
     *
     * @param key
     *            缓存Key
     * @return keyValue
     * @author:mijp
     * @since:2017/1/16 13:23
     */
    String getFromSet(String key);

    /**
     * 列出set中所有成员
     *
     * @param setName
     *            set名
     * @return
     */
    Set<Object> listSet(String setName);



}
