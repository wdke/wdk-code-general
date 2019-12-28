package com.wdk.general.core.common.logger;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TalStopWatch {
    private static final long NANOS_IN_A_MILLI = 1000000L;
    private static Logger logger = LoggerFactory.getLogger(TalStopWatch.class);
    private String globalTag;
    private long startTime;
    private long nanoStartTime;
    private long nanoLapStartTime;
    private long elapsedTime;
    private long timeThreshold = 50L;
    private List<String> stopWatchStrings;

    public TalStopWatch(String globalTag) {
        this.start(globalTag);
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getElapsedTime() {
        return this.elapsedTime == -1L ? (System.nanoTime() - this.nanoLapStartTime) / 1000000L : this.elapsedTime;
    }

    public void start(String globalTag) {
        this.globalTag = globalTag;
        this.startTime = System.currentTimeMillis();
        this.nanoStartTime = System.nanoTime();
        this.stopWatchStrings = new ArrayList();
        this.restLapTime();
    }

    public void stop() {
        this.log();
    }

    public void stop(String tag) {
        this.stop(tag, "");
    }

    public void stop(String tag, String message) {
        this.lap(tag, message);
        this.log();
    }

    private void log() {
        long totalElapsedTime = (System.nanoTime() - this.nanoStartTime) / 1000000L;
        if (this.timeThreshold == 0L || totalElapsedTime > this.timeThreshold) {
            StringBuilder sb = new StringBuilder("StopWatch:start[" + this.getStartTime() + "] tag[" + this.globalTag + "] total[" + totalElapsedTime + "]");
            String logStr = sb.toString();
            if (!this.stopWatchStrings.isEmpty()) {
                sb.append(" :");
                Iterator var5 = this.stopWatchStrings.iterator();

                while (var5.hasNext()) {
                    String lapStr = (String) var5.next();
                    sb.append(lapStr);
                    sb.append(",");
                }

                logStr = sb.substring(0, sb.length() - 1);
            }

            logger.info(logStr);
        }

    }

    public void lap(String tag) {
        this.lap(tag, "");
    }

    public void lap(String tag, String message) {
        this.stopWatchStrings.add(this.createLapLog(tag, message));
        this.restLapTime();
    }

    private String createLapLog(String tag, String message) {
        return " tag[" + tag + "] " + this.getElapsedTime() + (StringUtils.isEmpty(message) ? "" : " message[" + message + "]");
    }

    private void restLapTime() {
        this.nanoLapStartTime = System.nanoTime();
        this.elapsedTime = -1L;
    }

    public long getTimeThreshold() {
        return this.timeThreshold;
    }

    public void setTimeThreshold(long timeThreshold) {
        this.timeThreshold = timeThreshold;
    }
}
