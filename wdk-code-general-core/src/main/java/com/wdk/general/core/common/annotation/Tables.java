package com.wdk.general.core.common.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Tables {

        String name() default "";

        String comment() default "";

}
