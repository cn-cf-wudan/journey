package org.journey.dao.redis.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.journey.dao.redis.annotation.NotInRedis;
import org.journey.dao.redis.annotation.RedisDateFormat;
import org.journey.dao.redis.annotation.RedisKeySuffix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.exceptions.JedisDataException;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wudan-mac
 * @ClassName: Reflections
 * @ClassNameExplain: 中文释义:反射
 * @Description: 与反射相关的工具类
 * @date 16/4/9 下午2:55
 */
public class Reflections {

    static Logger logger = LoggerFactory.getLogger(Reflections.class);

    static Gson gson = new Gson();

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

    /**
     * @param bean 传入的bean
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * key    redis键
     * poMap  redis  HashMap
     * @Title: getRedisHashFromBean
     * @TitleExplain:
     * @Description: 将传入的bean转成reidsHashMap
     * @version 1.0.0
     * @author wudan-mac
     */
    public static Map<String, Object> getRedisHashFromBean(Object bean) throws Exception {

        if (bean == null) {
            throw new JedisDataException("bean sent to redis cannot be null");
        }

        //根据java bean 注解获得对应javabean的存储键
        final String key = getRedisKey(bean);
        if (StringUtils.isEmpty(String.valueOf(key))) {
            throw new JedisDataException("bean key to redis cannot be null");
        }

        //用来存储全部字段的map
        final Map<String, String> poMap = new HashMap();

        //反射获得对应javabean 的class对象
        Class clazz = bean.getClass();
        //取得全部字段
        Field[] fields = clazz.getDeclaredFields();
        //循环处理字段
        for (Field field : fields) {

            /**
             * 排除序列化id
             */
            if (RedisConstant.SERIAL_VERSION_UID_FIELD_NAME.equals(field.getName())) {
                continue;
            }

            /**
             * 排除key 前缀字段
             */
            if (RedisConstant.REDIS_KEY_PREFIX_FIELD_NAME.equals(field.getName())) {
                continue;
            }

            /**
             * 如果该字段被标注不写入redis 则跳过
             */
            if (field.getAnnotation(NotInRedis.class) != null) {
                continue;
            }

            /**
             * 反射get方法获取属性值
             */
            Object tempValueObject;
            tempValueObject = Reflections.invokeGetter(bean, field.getName());
            if (tempValueObject == null) {
                continue;
            }

            /**
             * 判断字段类型是否为基本类型
             * 是 直接写入字段值
             * 不是  json序列化后写入json
             */
            String value = "";
            if (!field.getType().isPrimitive()) {

                /**
                 * 如果该字段标注了日期格式注解 则按注解格式进行json序列化
                 */
                if (field.getType().equals(Date.class) && field.getAnnotation(RedisDateFormat.class) != null) {
                    Gson gsonTemp = new GsonBuilder().setDateFormat(field.getAnnotation(RedisDateFormat.class).pattern()).create();
                    value = gsonTemp.toJson(tempValueObject);
                } else {
                    value = gson.toJson(tempValueObject);
                }
            } else {
                value = tempValueObject.toString();
            }
            poMap.put(field.getName(), value);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("key", key);
        resultMap.put("poMap", poMap);

        return resultMap;
    }

    /**
     * @Title: getBeanFromRedisHash
     * @TitleExplain:
     * @Description: 将传入的redisHashMap  转换成 javaBean
     * @param map redis Hash
     * @param clazz 要转换成的javaBean 类型
     * @return T JavaBean对像
     * @version 1.0.0
     * @author wudan-mac
     */
    public static <T> T getBeanFromRedisHash(Map<String, String> map, Class<T> clazz) throws Exception {

        //实例化返回的对象
        T result = clazz.newInstance();

        //取得全部字段
        Field[] fields = clazz.getDeclaredFields();

        /**
         * 将取得的hashmap对象转化为java对象
         */
        Object value;
        for (Field field : fields) {

            /**
             * 如果该字段被标注不写入redis 则跳过
             */
            if (field.getAnnotation(NotInRedis.class) != null) {
                continue;
            }

            String valueInMap = map.get(field.getName());
            /*if (field.getType().equals(String.class)) {
                //这样做是为了解决字符串中出现  空格等特殊字符引起的 json反序列化错误
                value = gson.fromJson("\"" + valueInMap + "\"", field.getType());
            } else */
            if (field.getType().equals(Date.class)) {
                //如果是日期类型 则按照注解格式反序列化
                Gson gsonTemp = new GsonBuilder().setDateFormat(field.getAnnotation(RedisDateFormat.class).pattern()).create();
                value = gsonTemp.fromJson(valueInMap, field.getType());
            } else {
                value = gson.fromJson(valueInMap, field.getType());
            }
            //设置当前字段的值
            Reflections.invokeSetter(
                    result,
                    field.getName(),
                    value
            );
        }
        return result;
    }
}
