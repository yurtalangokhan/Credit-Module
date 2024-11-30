package com.inghub.core.data.jpa.configuration;

import com.inghub.core.common.configuration.BaseConfiguration;
import com.inghub.core.data.jpa.aware.JpaLocaleAware;
import com.inghub.core.data.jpa.aware.provider.JpaAwareProvider;
import com.inghub.core.data.jpa.configuration.constant.JpaAuditingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @author gyurtalan
 * @version 1.0
 */


@Configuration
@ComponentScan(basePackages = {JpaAuditingConstants.BASE_PACKAGE})
@EnableJpaAuditing(auditorAwareRef = JpaAuditingConstants.AWARE_BEAN_NAME,
        dateTimeProviderRef = JpaAuditingConstants.DATE_TIME_PROVIDER_BEAN_NAME)
public class JpaAuditingConfiguration extends BaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaAuditingConfiguration.class);

    /**
     * The constant UNKNOWN_CURRENT_AUDITOR.
     */
    public static final String CURRENT_AUDITOR = "admin user";

    @Bean
    @ConditionalOnMissingBean
    AuditorAware<String> jpaAuditorAware() {
        return new AuditorAware<>() {

            /**
             * {@inheritDoc}
             */
            @Override
            @NonNull
            public Optional<String> getCurrentAuditor() {
                return Optional.of(CURRENT_AUDITOR);
            }

        };
    }

    @Bean
    @ConditionalOnMissingBean
    DateTimeProvider jpaAuditorDateTimeProvider() {
        return new DateTimeProvider() {

            /**
             * {@inheritDoc}
             */
            @Override
            @NonNull
            public Optional<TemporalAccessor> getNow() {
                return Optional.of(LocalDateTime.now(TimeZone.getDefault().toZoneId()));
            }

        };
    }

    @Bean
    @ConditionalOnMissingBean
    public JpaLocaleAware localeAware() {
        return new JpaLocaleAware() {

            /**
             * {@inheritDoc}
             */
            @Override
            public Optional<Locale> getLocale() {
                return Optional.of(new Locale("tr", "TR"));
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    JpaAwareProvider jpaAwareProvider(@Qualifier("jpaAuditorAware") AuditorAware<String> jpaAuditorAware,
                                      @Qualifier("jpaAuditorDateTimeProvider") DateTimeProvider jpaAuditorDateTimeProvider,
                                      @Qualifier("localeAware") JpaLocaleAware localeAware) {
        return new JpaAwareProvider() {

            /**
             * {@inheritDoc}
             */
            @Override
            public AuditorAware<String> auditorAware() {
                return jpaAuditorAware;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public DateTimeProvider auditorDateTimeProvider() {
                return jpaAuditorDateTimeProvider;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public JpaLocaleAware localeAware() {
                return localeAware;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info() {
        super.info();
        LOGGER.info("JpaConfiguration class scan path is [" + JpaAuditingConstants.BASE_PACKAGE + "]");
    }

}