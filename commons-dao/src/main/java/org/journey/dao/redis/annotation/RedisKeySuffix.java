package org.journey.dao.redis.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: RedisKeySuffix
 * @ClassNameExplain: redis 存储key后缀注解类
 * @Description:
 * @author wudan-mac
 * @date 16/4/11 上午11:11
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisKeySuffix {
}
