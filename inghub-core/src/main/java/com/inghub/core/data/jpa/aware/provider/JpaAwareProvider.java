package com.inghub.core.data.jpa.aware.provider;

import com.inghub.core.data.jpa.aware.JpaLocaleAware;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;

/**
 * @author gyurtalan
 * @version 1.0
 */
public interface JpaAwareProvider {

    AuditorAware<String> auditorAware();

    DateTimeProvider auditorDateTimeProvider();

    JpaLocaleAware localeAware();

}