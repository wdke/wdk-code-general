package com.wdk.general.core.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by david on 18-4-16.
 */
public class AccessLog {
    private static Logger logger = LoggerFactory.getLogger(AccessLog.class);

    public static Logger getLogger() {
        return logger;
    }
}
