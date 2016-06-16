package org.journey.mingrui.web.ext;

import java.lang.annotation.*;

/**
 * Created by wudan-mac on 16/3/8.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
    String remark() default "";
    String operType() default "0";
    // String desc() default "";
}
