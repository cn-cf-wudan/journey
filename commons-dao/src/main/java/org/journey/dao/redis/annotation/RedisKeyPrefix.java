package org.journey.dao.redis.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: RedisKeyPrefix
 * @ClassNameExplain: redis 存储key前缀注解类
 * @Description:
 * @author wudan-mac
 * @date 16/4/11 上午11:10
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisKeyPrefix {

}
