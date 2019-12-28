package com.wdk.general.core.common.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableKeys {
}
