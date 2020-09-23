package org.houqian.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Log4j2Demo {
    private static Logger logger = LogManager.getLogger(Log4j2Demo.class);

    public static void main(String[] args) throws Exception {
        // Init Log Context, set TTL
        // More KV if needed
        final String TRACE_ID = "traceId";
        final String TRACE_ID_VALUE = "XXX-YYY-ZZZ";
        MDC.put(TRACE_ID, TRACE_ID_VALUE);


        // Log in Main Thread
        logger.info("Log in main!");

        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Run task in thread pool
        executorService.submit(createTask()).get();

        // Log in Main Thread
        logger.info("Log in main!");

        executorService.submit(createTask()).get();

        logger.info("Exit main");

        executorService.shutdown();
    }

    private static Runnable createTask() {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                // Log in thread pool
//                ThreadContext.put("task", new Date().toString());
                logger.info("Log in Runnable!");
            }
        };
        return task;
    }
}