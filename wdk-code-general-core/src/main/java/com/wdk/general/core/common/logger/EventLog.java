package com.wdk.general.core.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: junwei
 * @Date: david 2018/10/29
 */
public class EventLog {
    private static Logger logger = LoggerFactory.getLogger(EventLog.class);

    public static Logger getLogger() {
        return logger;
    }
}
