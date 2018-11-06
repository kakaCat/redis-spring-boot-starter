package cloud.redis.repositories;

import java.util.Map;

/**
 * Created by JAVA on 2017/10/12.
 *
 */
public interface RedisHashRepo {

    /**
     * 保存到hash集合中
     *
     * @param hName
     *            集合名
     * @param key
     * @param value
     */
    boolean hashput(String hName, String key, String value);

    /**
     * 根据key获取所以值
     *
     * @param key
     * @return
     */
    Map<Object, Object> hgetAll(String key);

    /**
     * 从hash集合里取得
     *
     * @param key
     * @param hKey
     * @return
     */
    Object hashGet(String key, String hKey);

    /**
     * 判断hash集合中是否缓存了数据
     *
     * @param key
     * @param hKey
     *            数据KEY
     * @return 判断是否缓存了
     */
    boolean IshashCached(String key, String hKey);


    /**
     * 从hashmap中删除一个值
     *
     * @param key
     *            map名
     * @param hKey
     *            成员名称
     */
    boolean delFromMap(String key, String hKey);

    /**
     * 保存到hash集合中 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与
     * key 关联。如果字段已存在，该操作无效果。
     *
     * @param key
     *            集合名
     * @param hKey
     * @param value
     */
    boolean hsetnx(String key, String hKey, String value);
}
