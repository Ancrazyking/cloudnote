package org.afeng.util;

import org.afeng.util.constants.Constants;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.ConnectionFactory;


import java.io.InputStream;
import java.util.Properties;

/**
 * 加载所有的配置文件
 *
 *
 * @author afeng
 * @date 2018/11/2 16:35
 **/
public class InitStart
{

    @SuppressWarnings("unused")
    private void InitSystem()
    {
        try
        {
            //HBase配置
            ClassLoader classLoader = InitStart.class.getClassLoader();
            InputStream hbase = classLoader.getResourceAsStream("hbase.properties");
            Properties hbasePro = new Properties();
            hbasePro.load(hbase);
            String hbase_zookeeper_quorum = hbasePro.getProperty("hbase_zookeeper_quorum");//ZooKeeper地址
            String hbase_zookeeper_property_clientPort = hbasePro.getProperty("hbase_zookeeper_property_clientPort");//ZooKeeper端口
            int hbase_pool_size = Integer.parseInt(hbasePro.getProperty("hbase_pool_size"));//连接池
            Constants.HBASE_WRITE_BUFFER = Long.parseLong(hbasePro.getProperty("hbaseWriteBuffer"));
            Constants.CONFIG = HBaseConfiguration.create();
            Constants.CONFIG.set("hbase.zookeeper.quorum", hbase_zookeeper_quorum);
            Constants.CONFIG.set("hbase.zookeeper.property.clientPort", hbase_zookeeper_property_clientPort);
            Constants.CONNECTION = ConnectionFactory.createConnection(Constants.CONFIG);

            //lucene配置
            InputStream lucene = classLoader.getResourceAsStream("lucene.properties");
            Properties lucenePro = new Properties();
            lucenePro.load(lucene);
            Constants.LUCENE_DIR = lucenePro.getProperty("lucene_dir");//文件检索目录
            //Constants.FILE_DIR = lucenePro.getProperty("file_dir");//content保存目录
            String lucene_page_count = lucenePro.getProperty("lucene_page_count");//查询分页
            Configuration.initFSDir(Constants.LUCENE_DIR);//初始化luceneFSD
            Constants.LUCENE_PAGE_COUNT = Integer.parseInt(lucene_page_count);


            //redis配置
            InputStream redis = classLoader.getResourceAsStream("redis.properties");
            Properties redisPro = new Properties();
            redisPro.load(redis);
            String redis_ip = redisPro.getProperty("redis_ip");
            int redis_port = Integer.parseInt(redisPro.getProperty("redis_port"));
            int redis_timeout = Integer.parseInt(redisPro.getProperty("redis_timeout"));
            String redis_auth = redisPro.getProperty("redis_auth");
            Constants.REDIS_IP = redis_ip;
            Constants.REDIS_PORT = redis_port;
            Constants.REDIS_TIMEOUT = redis_timeout;
            Constants.REDIS_AUTH = redis_auth;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
