package cloud.redis.repositories;

import java.util.List;

/**
 * Created by JAVA on 2017/10/12.
 */
public interface RedisListRepo {

    /**
     * 功能: 存到指定的队列中<br />
     * 左近右出<br\> 作者: 耿建委
     *
     * @param key
     * @param val
     * @param size
     *            队列大小限制 0：不限制
     */
    boolean saveToQueue(String key, String val, long size);


    /**
     *
     * 功能: 从指定队列里取得数据<br />
     * 作者: 耿建委
     *
     * @param key
     * @param size
     *            数据长度
     * @return
     */
    List<String> getFromQueue(String key, long size);

    /**
     *
     * 功能: 从指定队列里取得数据<br />
     * 作者: 耿建委
     *
     * @param key
     * @return
     */
    String popQueue(String key);

    /**
     * 将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表
     *
     * @param <T>
     *
     * @param key
     * @param value
     * @return
     */
    Long lpush(String key, String value);

    /**
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。 与 LPUSH 相反，当
     * key 不存在的时候不会进行任何操作
     *
     * @param key
     * @param value
     * @return
     */
    Long lpushx(String key, String value);


    /**
     * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0
     *
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end
     * 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推
     *
     * @param key
     * @return
     */
    List<String> lrange(String key, long start, long end);


    /**
     * 移除并且返回 key 对应的 list 的第一个元素
     *
     * @param key
     * @return
     */
    String lpop(String key);
}
