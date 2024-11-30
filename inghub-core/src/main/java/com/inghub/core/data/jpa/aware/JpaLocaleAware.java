package com.inghub.core.data.jpa.aware;

import java.util.Locale;
import java.util.Optional;

/**
 * @author gyurtalan
 * @version 1.0
 */

public interface JpaLocaleAware {

    Optional<Locale> getLocale();
}
