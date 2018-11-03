package org.afeng.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.afeng.util.constants.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author afeng
 * @date 2018/11/3 9:50
 **/
public class RedisTools
{
    public static int SECONDS = 3600 * 24;//指定key过期时间,单位s

    private static JedisPool pool;//JedisPool连接池

    static
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1000);
        config.setMaxTotal(10240);
        if (pool == null)
        {
            pool = new JedisPool(config, Constants.REDIS_IP, Constants.REDIS_PORT, Constants.REDIS_TIMEOUT);
        }
    }

    public static Jedis getJedis()
    {
        return pool.getResource();
    }

    public static void closeJedis(Jedis jedis)
    {
        pool.returnResource(jedis);
    }

    /**
     * Redis添加String类型数据
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value)
    {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key))
        {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, SECONDS);
        RedisTools.closeJedis(jedis);
    }

    public static void set(Jedis jedis, String key, String value)
    {
        if (jedis.exists(key))
        {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, SECONDS);
    }

    /**
     * Redis设置String类型数据,支持自定义过期时间
     *
     * @param key
     * @param value
     * @param exp
     */
    public static void set(String key, String value, int exp)
    {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key))
        {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, exp);
        RedisTools.closeJedis(jedis);
    }

    public static void set(Jedis jedis, String key, String value, int exp)
    {
        if (jedis.exists(key))
        {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, exp);
    }

    /**
     * Redis String类型,通过key获取value
     *
     * @param key
     * @return
     */
    public static String get(String key)
    {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key))
        {
            return jedis.get(key);
        }
        RedisTools.closeJedis(jedis);
        return null;
    }

    public static String get(Jedis jedis, String key)
    {
        if (jedis.exists(key))
        {
            return jedis.get(key);
        }
        return null;
    }


    /**
     * Redis  设置List类型数据  集合类型,双端链表
     *
     * @param key
     * @param list
     * @return
     */
    public static Long setRList(String key, List<String> list)
    {
        Jedis jedis = RedisTools.getJedis();
        String[] array = list.toArray(new String[list.size()]);
        Long rpush = jedis.rpush(key, array);
        RedisTools.closeJedis(jedis);
        return rpush;
    }

    public static Long setLList(String key, List<String> list)
    {
        Jedis jedis = RedisTools.getJedis();
        String[] array = list.toArray(new String[list.size()]);
        Long lpush = jedis.lpush(key, array);
        return lpush;
    }

    public static Long appendRightList(String key, String str)
    {
        Jedis jedis = RedisTools.getJedis();
        Long rpush = jedis.rpush(key, str);
        RedisTools.closeJedis(jedis);
        return rpush;
    }

    public static Long appendLeftList(String key, String str)
    {
        Jedis jedis = RedisTools.getJedis();
        Long lpush = jedis.lpush(key, str);
        RedisTools.closeJedis(jedis);
        return lpush;
    }

    public static Long deleteValueOfList(String key, int count, String value)
    {
        Jedis jedis = RedisTools.getJedis();
        Long lrem = jedis.lrem(key, count, value);
        RedisTools.closeJedis(jedis);
        return lrem;
    }

    /**
     * Redis  key获取List类型
     *
     * @param key
     * @return
     */
    public static List<String> getList(String key)
    {
        Jedis jedis = RedisTools.getJedis();
        List<String> list = new ArrayList<>();
        long len = jedis.llen(key);
        if (len == 0)
        {
            return null;
        }
        list = jedis.lrange(key, 0, len);
        RedisTools.closeJedis(jedis);
        return list;
    }

    public static List<String> getList(Jedis jedis, String key)
    {
        List<String> list = new ArrayList<>();
        long len = jedis.llen(key);
        if (len == 0)
        {
            return null;
        }
        list = jedis.lrange(key, 0, len);

        return list;
    }


    /**
     * 判断kkey是否存在
     *
     * @param key
     * @return
     */
    public static boolean exists(String key)
    {
        boolean exists = false;
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key))
        {
            exists = true;
        }
        RedisTools.closeJedis(jedis);
        return exists;
    }

    public static boolean exists(Jedis jedis, String key)
    {
        boolean exists = false;
        if (jedis.exists(key))
        {
            exists = true;
        }
        return exists;
    }

    /**
     * 删除key
     *
     * @param key
     */
    public static void del(String key)
    {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key))
        {
            jedis.del(key);
        }
        RedisTools.closeJedis(jedis);
    }

    public static void del(Jedis jedis, String key)
    {
        if (jedis.exists(key))
        {
            jedis.del(key);
        }

    }

    /**
     * 清空当前库
     */
    public static void flushDB()
    {
        Jedis jedis = RedisTools.getJedis();
        jedis.flushDB();
        RedisTools.closeJedis(jedis);
    }


    /**
     * Redis Set集合
     * 添加一个String元素到对应的Set集合,成功返回1,元素已经在集合中返回0.
     * key对应的set不存在返回错误
     *
     * @param key
     * @param ownerId
     * @param toId
     * @param value
     */
    public static void push(String key, int ownerId, int toId, String value)
    {
        Jedis jedis = RedisTools.getJedis();
        jedis.sadd(key + ownerId, key + toId);
        if (jedis.exists(key + toId))
        {
            jedis.del(key + toId);
        }
        jedis.set(key + toId, value);
        jedis.expire(key + ownerId, SECONDS);
        jedis.expire(key + toId, SECONDS);
        RedisTools.closeJedis(jedis);
    }


    /**
     * 将bean转换为json
     * 使得json可以保存到redis
     *
     * @param session
     * @return
     */
    public static String changeJsonToSave(HttpSession session)
    {

        String json = JSON.toJSONString(session);
        return session.getClass().getCanonicalName() + "ゎ∴♂㊣ф≒ж☆♀∴ぁ" + json;
    }

    /**
     * 存放单个bean到redis
     *
     * @param jedis
     * @param key
     * @param session
     */
    public static void saveBeanToRedis(Jedis jedis, String key, HttpSession session)
    {
        jedis.lpush(key, changeJsonToSave(session));
    }

    public static void saveBeanToRedis(String key, HttpSession session)
    {
        Jedis jedis = RedisTools.getJedis();
        jedis.lpush(key, changeJsonToSave(session));
        RedisTools.closeJedis(jedis);
    }

    /**
     * 存放lsit集合到redis
     *
     * @param key
     * @param sessions
     */
    public static void saveBeanListToRedis(String key, List<HttpSession> sessions)
    {
        Jedis jedis = RedisTools.getJedis();
        if (sessions != null)
        {
            for (HttpSession session : sessions)
            {
                saveBeanToRedis(jedis, key, session);
            }
        }
        RedisTools.closeJedis(jedis);
    }

    public static void saveBeanListToRedis(Jedis jedis, String key, List<HttpSession> sessions)
    {
        if (sessions != null)
        {
            for (HttpSession session : sessions)
            {
                saveBeanToRedis(jedis, key, session);
            }
        }
    }


    /**
     * 将Json字符串转换为bean对象
     *
     * @param beanJson
     * @return
     */
    public static HttpSession changeJsonToBean(String beanJson)
    {
        if (beanJson != null && !"".equals(beanJson))
        {
            String[] params = beanJson.split("ゎ∴♂㊣ф≒ж☆♀∴ぁ");
            if (params != null && params.length == 2)
            {
                String className = "";
                String realJson = "";
                HttpSession session = null;
                try
                {
                    className = params[0];
                    realJson = params[1];
                    //反射创建该类的实例
                    session = (HttpSession) Class.forName(className).newInstance();
                    //将json字符串转换为对象
                    session = JSON.parseObject(realJson, HttpSession.class);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                return session;
            } else
            {
                return null;
            }

        } else
        {
            return null;
        }
    }


    /**
     * 通过key,查询benaList
     *
     * @param key
     * @return
     */
    public static List<HttpSession> getBeanList(String key)
    {
        Jedis jedis = getJedis();
        long len = jedis.llen(key);
        List<String> list = jedis.lrange(key, 0, len - 1);
        List<HttpSession> sessions = new ArrayList<>();
        for (String value : list)
        {
            sessions.add(changeJsonToBean(value));
        }
        RedisTools.closeJedis(jedis);
        return sessions;
    }

    public static List<HttpSession> getBeanList(Jedis jedis, String key)
    {
        long len = jedis.llen(key);
        List<String> list = jedis.lrange(key, 0, len - 1);
        List<HttpSession> sessions = new ArrayList<>();
        for (String value : list)
        {
            sessions.add(changeJsonToBean(value));
        }

        return sessions;
    }


    /**
     * 通过key,查询单个的bean
     *
     * @param key
     * @return
     */
    public static HttpSession getBean(String key)
    {
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(key, 0, 0);
        HttpSession session = null;
        for (String value : list)
        {
            session = changeJsonToBean(value);
        }
        RedisTools.closeJedis(jedis);
        return session;
    }

    public static HttpSession getBean(Jedis jedis, String key)
    {
        List<String> list = jedis.lrange(key, 0, 0);
        HttpSession session = null;
        for (String value : list)
        {
            session = changeJsonToBean(value);
        }
        return session;
    }


}
