package org.journey.dao.redis.annotation;

import java.lang.annotation.*;

/**
 * @author wudan-mac
 * @ClassName: NotInRedis
 * @ClassNameExplain:
 * @Description: 标注该字段不存储在redis
 * @date 16/4/27 下午2:32
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotInRedis {
}
