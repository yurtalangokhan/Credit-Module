package com.inghub.core.data.jpa.configuration.constant;

/**
 * @author gyurtalan
 * @version 1.0
 */
public final class JpaAuditingConstants {

    public static final String BASE_PACKAGE="com.inghub.core.data.jpa";
    public static final String AWARE_BEAN_NAME = "jpaAuditorAware";
    public static final String DATE_TIME_PROVIDER_BEAN_NAME = "jpaAuditorDateTimeProvider";
    public static final String HIBERNATE_PROPERTIES_PREFIX = "spring.jpa.properties.hibernate";
    public static final String DATASOURCE_PROXY_PROCESSOR_ENABLED_PROPERTY = "enable_datasource_proxy_processor";
}
