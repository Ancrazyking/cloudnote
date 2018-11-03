package org.afeng.util.constants;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * 程序中使用的静态字段
 *
 * @author afeng
 * @date 2018/11/2 16:38
 **/
public class Constants
{
    //笔记本信息
    public static final String NOTEBOOK_TABLE_NAME = "notebook";//表名
    public static final String NOTEBOOK_FAMILY_NOTEBOOKINF = "notebookinfo";//列族
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_CREATETIME = "createtime";//列2：创建笔记本时间
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_STATUS = "status";//列3：笔记本状态
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_NOTELIST = "notelist";//列4：笔记本下笔记信息列表,json符串
    //笔记信息
    public static final String NOTE_TABLE_NAME = "note";//表名
    public static final String NOTE_FAMLIY_NOTEINFO = "noteinfo";//列族1：笔记信息
    public static final String NOTE_NOTEINFO_CLU_NOTENAME = "notename";//列1：笔记名字
    public static final String NOTE_NOTEINFO_CLU_CREATETIME = "createtime";//列2：创建时间
    public static final String NOTE_NOTEINFO_CLU_STATUS = "status";//列3：笔记状态
    public static final String NOTE_FAMLIY_CONTENTINFO = "contentinfo";//列族2：笔记内容
    public static final String NOTE_CONTENTINFO_CLU_CONTENT = "content";//列1：笔记内容
    //user信息
    public static final String USER_INFO = "userinfo";
    public static final String STRING_SEPARATOR = "|";
    public static final String ROWKEY_SEPARATOR = "_";//rowkey的分隔符
    //笔记rowkey前缀
    public static final String NOTE_PREFIX = "note" + Constants.ROWKEY_SEPARATOR;
    //特殊笔记列表
    public static final String RECYCLE = Constants.ROWKEY_SEPARATOR + "0000000000000";//回收站
    public static final String STAR = Constants.ROWKEY_SEPARATOR + "0000000000001";//收藏
    public static final String ACTIVITY = Constants.ROWKEY_SEPARATOR + "0000000000002";//活动
    //HBase配置
    public static Configuration CONFIG;//配置信息类
    public static HBaseAdmin ADMIN; //表管理类
    public static Connection CONNECTION;
    public static long HBASE_WRITE_BUFFER; //批量写的buffer大小
    //lucene配置
    public static String LUCENE_DIR;//lucene库文件夹
    public static String FILE_DIR;//content文件夹
    public static int LUCENE_PAGE_COUNT;//Lucene分页,每页大小
    //redis配置
    public static String REDIS_IP;
    public static int REDIS_PORT;
    public static int REDIS_TIMEOUT;
    public static String REDIS_AUTH;


}
