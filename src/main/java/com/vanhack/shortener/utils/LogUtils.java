package com.vanhack.shortener.utils;

import lombok.extern.log4j.Log4j2;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@Log4j2
public class LogUtils {

    private static final String INFO_MESSAGE = "M={}, status={}, url={}";

    private static final String INIT = "init";
    private static final String FINISH = "finish";

    public static void logInfoInit(final String url){
        logInfo(INIT, url);
    }

    public static void logInfoFinish(final String url){
        logInfo(FINISH, url);
    }

    private static void logInfo(final String status, final String url){
        final String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        log.info(INFO_MESSAGE, methodName, status, url);
    }

}
