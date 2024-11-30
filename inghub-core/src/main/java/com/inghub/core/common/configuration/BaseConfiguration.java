package com.inghub.core.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseConfiguration.class);

    protected BaseConfiguration() {
        info();
    }
    protected void info() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s class was added as configuration class.", this.getClass().getSimpleName()));
        }
    }

}
