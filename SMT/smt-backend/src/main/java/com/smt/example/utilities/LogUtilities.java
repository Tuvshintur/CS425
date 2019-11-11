package com.smt.example.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Component;

@Component
public class LogUtilities {

    public static void log(String key, Integer level, String msg, Throwable throwable) {
        switch (level) {
            case Priority.DEBUG_INT:
                Logger.getLogger(key).debug(msg);
                break;
            case Priority.INFO_INT:
                Logger.getLogger(key).info(msg);
                break;
            case Priority.WARN_INT:
                Logger.getLogger(key).warn(msg);
                break;
            case Priority.ERROR_INT:
                Logger.getLogger(key).error(msg);
                break;
            case Priority.FATAL_INT:
                Logger.getLogger(key).fatal(msg, throwable);
                break;
        }
    }

    public static void debug(String key, String msg) {
        log(key, Priority.DEBUG_INT, msg, null);
    }

    public static void info(String key, String msg) {
        log(key, Priority.INFO_INT, msg, null);
    }

    public static void warn(String key, String msg) {
        log(key, Priority.WARN_INT, msg, null);
    }

    public static void error(String key, String msg) {
        log(key, Priority.ERROR_INT, msg, null);
    }

    public static void error(String key, String msg, boolean error) {
        log(key, Priority.ERROR_INT, msg, null);
    }

    public static void fatal(String key, String msg, Exception ex) {
        log(key, Priority.FATAL_INT, msg, ex);
    }

    public static void fatal(String key, String msg, Throwable throwable) {
        log(key, Priority.FATAL_INT, msg, throwable);
    }
}