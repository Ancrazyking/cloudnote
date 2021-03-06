package org.afeng.util;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import org.afeng.util.constants.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * 配置Lucene
 *
 * @author afeng
 * @date 2018/11/3 14:39
 **/
public class Configuration
{

    public static String DIR = "./indexDir/";
    private static Directory directory;
    private static MMSegAnalyzer analyzer;
    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static void initFSDir(String dir)
    {
        try
        {
            logger.debug("Configuration init start!");
            if (dir != null && !"".equals(dir))
            {
                DIR = dir;
            }
            directory = FSDirectory.open(new File(DIR));
            analyzer = new MMSegAnalyzer();
        } catch (Exception e)
        {
            logger.error("Configuration init error! dir is " + dir, e);
        }
    }


    public static Directory getDirectory()
    {

        if (directory == null)
        {
            try
            {
                logger.debug("Configuration init start!");
                if (Constants.LUCENE_DIR != null && "".equals(Constants.LUCENE_DIR))
                {
                    DIR = Constants.LUCENE_DIR;
                }
                directory = FSDirectory.open(new File(DIR));
                analyzer = new MMSegAnalyzer();
            } catch (Exception e)
            {
                logger.error("Configuration init error! dir is", e);
            }
        }
        return directory;
    }


    public static Analyzer getAnalyzer()
    {
        return analyzer;
    }


}
