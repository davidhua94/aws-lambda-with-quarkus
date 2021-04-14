package org.github.davidhua94.core;

import java.lang.annotation.*;

/**
 * @author David Hua
 * @date 2021/3/29
 * @desc
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Api {
    String value() default "";
}
