package com.inghub.core.data.anotation;

import java.lang.annotation.*;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntitySequence {

    String name();
}
