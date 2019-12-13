package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    /***
     *
     * @return
     */
    public static Logger getLogger(){
       return LoggerFactory.getLogger(LogUtil.class);
    }
}
