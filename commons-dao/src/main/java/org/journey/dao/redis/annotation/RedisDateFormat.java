package org.journey.dao.redis.annotation;

import java.lang.annotation.*;

/**
 * @author wudan-mac
 * @ClassName: RedisDateFormat
 * @ClassNameExplain:
 * @Description: redis存储日期格式的注解
 * @date 16/5/17 上午9:00
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisDateFormat {

    //日期格式
    String pattern() default "yyyy-MM-dd HH:mm:ss";

}
