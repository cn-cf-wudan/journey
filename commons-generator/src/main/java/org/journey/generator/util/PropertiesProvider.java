package org.journey.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * @author wudan-mac
 * @ClassName: PropertiesProvider
 * @ClassNameExplain: 配置文件辅助类
 * @Description:
 * @date 16/4/20 下午3:40
 */
public class PropertiesProvider {

    private final static String BASE_PACKAGE_KEY = "basepackage";

    static Properties props;

    private PropertiesProvider() {
    }

    /**
     * @Title: initProperties
     * @TitleExplain:
     * @Description: 初始化配置
     * @param
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    private static void initProperties() {
        try {
            props = loadAllProperties("sinldo-generator.properties");
            for (Iterator it = props.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println("[Property] " + entry.getKey() + "=" + entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException("Load Properties error", e);
        }
    }

    /**
     * @Title: getProperties
     * @TitleExplain:
     * @Description: 获取配置文件对象的方法
     * @param
     * @return java.util.Properties 配置文件对象
     * @version 1.0.0
     * @author wudan-mac
     */
    public static Properties getProperties() {
        if (props == null)
            initProperties();
        return props;
    }

    /**
     * @Title: setPropertie
     * @TitleExplain:
     * @Description: 添加配置参数
     * @param key 键
     * @param value 值
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    private static void setPropertie(String key, String value) {
        getProperties().put(key, value);
    }

    /**
     * @Title: setBasePackage
     * @TitleExplain:
     * @Description: 添加基础包参数
     * @param basePackage 基础包名
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    public static void setBasePackage(String basePackage){
        setPropertie(BASE_PACKAGE_KEY, basePackage);
    }

    /**
     * @Title: getProperty
     * @TitleExplain:
     * @Description: 获取配置(有默认值)
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.String
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String getProperty(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }

    /**
     * @Title: getProperty
     * @TitleExplain:
     * @Description: 获取配置
     * @param key 键
     * @return java.lang.String
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    /**
     * @Title: loadAllProperties
     * @TitleExplain:
     * @Description: 读取配置文件内容
     * @param resourceName 配置文件路径
     * @return Properties 配置文件对象
     * @version 1.0.0
     * @author wudan-mac
     */
    public static Properties loadAllProperties(String resourceName) throws IOException {
        Properties properties = new Properties();
        Enumeration urls = PropertiesProvider.class.getClassLoader().getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = (URL) urls.nextElement();
            InputStream is = null;
            try {
                URLConnection con = url.openConnection();
                con.setUseCaches(false);
                is = con.getInputStream();
                properties.load(is);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        return properties;
    }
}
