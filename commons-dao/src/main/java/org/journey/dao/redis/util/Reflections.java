package org.journey.dao.redis.util;


import org.journey.dao.redis.annotation.RedisKeySuffix;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wudan-mac
 * @ClassName: Reflections
 * @ClassNameExplain: 中文释义:反射
 * @Description: 与反射相关的工具类
 * @date 16/4/9 下午2:55
 */
public class Reflections {

    /**
     * @param object    要反射的对象
     * @param fieldName 要反射的字段
     * @return java.lang.Object 获得的值
     * @Title: invokeGetter
     * @TitleExplain:
     * @Description: 反射某个类 某个字段get 方法 获得属性值
     * @version 1.0.0
     * @author wudan-mac
     */
    public static Object invokeGetter(Object object, String fieldName) throws Exception {
        PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
        Method get = pd.getReadMethod();
        return get.invoke(object, new Object[]{});
    }

    /**
     * @param object    要反射的对象
     * @param fieldName 要反射的字段
     * @param value     要设置的值
     * @return void
     * @Title: invokeSetter
     * @TitleExplain:
     * @Description: 反射某个类 某个字段set 方法 设置属性值
     * @version 1.0.0
     * @author wudan-mac
     */
    public static void invokeSetter(Object object, String fieldName, Object value) throws Exception {
        PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
        //获得set方法
        Method method = pd.getWriteMethod();
        method.invoke(object, value);
    }

    /**
     * @param object 要反射的对象
     * @return java.lang.String reids存储键
     * @Title: getRedisKey
     * @TitleExplain:
     * @Description: 根据注解获得java对象转redis时的键
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String getRedisKey(Object object) throws Exception {
        Annotation suffixAnnotation = null;
        String key = "";
        if (object == null) {
            return "";
        }
        Class clazz = object.getClass();

        //根据bean名字获取前缀
        key += clazz.getName();
        //获取key前缀
        key = key.substring(key.lastIndexOf(".") + 1, key.length());

        //取得全部字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            //找到后缀
            if (null == suffixAnnotation) {
                suffixAnnotation = f.getAnnotation(RedisKeySuffix.class);
                if (null != suffixAnnotation) {
                    key += RedisConstant.REDIS_KEY_SEPARATOR + invokeGetter(object, f.getName());
                    break;
                }
            }
        }
        return key;
    }

    /**
     * @param object          java对象
     * @param annotationClazz 注解的class对象
     * @return java.lang.String 对应字段的值
     * @Title: getValueByAnnotation
     * @TitleExplain:
     * @Description: 根据注解获取字段的值
     * @version
     * @author wudan-mac
     */
    public static String getValueByAnnotation(Object object, Class annotationClazz) throws Exception {
        String value = "";
        Annotation annotation = null;
        if (object == null) {
            return "";
        }
        Class clazz = object.getClass();
        //取得全部字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            //找到值
            if (null == annotation) {
                annotation = f.getAnnotation(annotationClazz);
                if (null != annotation) {
                    value = invokeGetter(object, f.getName()) + "";
                    break;
                }
            }
        }
        return value;
    }
}
