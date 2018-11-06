package cloud.redis.repositories;

import java.util.Set;

/**
 * Created by JAVA on 2017/10/25.
 */
public interface RedisGenericRepo {

    /**
     * 判断是否缓存了数据
     *
     * @param key
     *            数据KEY
     * @return 判断是否缓存了
     */
     boolean isCached(String key);


    /**
     * 从缓存中删除数据
     *
     * @param key
     * @return
     */
     boolean delKey(String key);


    /**
     * 设置超时时间
     *
     * @param key
     * @param seconds
     */
     boolean setExpire(String key, int seconds);

    /**
     * 数据需要分页的导入
     *
     * @param repoName
     * @param key
     * @param value
     */
    boolean insertPage(String repoName, String key, String value);

    /**
     * 获取分页
     *
     * @param repoName
     * @param pageNo
     * @param pageSize
     */
    Set<Object> getPage(String repoName, int pageNo, int pageSize);



}
