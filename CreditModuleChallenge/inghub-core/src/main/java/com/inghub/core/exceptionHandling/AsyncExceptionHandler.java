package com.inghub.core.exceptionHandling;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author gyurtalan
 * @version 1.0
 */

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {

/*        log.error("Async UnCaughtException handled with Error: [{}], MethodName: [{}], Class: [{}]",
                throwable.getStackTrace(), method.getExceptionTypes(), objects.getClass());*/

    }
}
