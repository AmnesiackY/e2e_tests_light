package io.testomat.e2e_tests_light.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger("console");

    public static void info(Object message) {
        logger.info(String.valueOf(message));
    }

    public static void debug(Object message) {
        logger.debug(String.valueOf(message));
    }

    public static void warning(Object message) {
        logger.warn(String.valueOf(message));
    }

    public static void error(String category, Object message) {
        logger.error("[" + category + "] " + message);
    }
}

