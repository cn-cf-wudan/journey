package org.journey.generator.util;

import java.util.Iterator;
import java.util.Map;

/**
 * @author wudan-mac
 * @ClassName: StringTemplate
 * @ClassNameExplain: 字符串操作模板类
 * @Description:
 * @date 16/4/21 上午11:58
 */
public class StringTemplate {

    private String str;
    private Map params;

    public StringTemplate(String str, Map params) {
        this.str = str;
        this.params = params;
    }

    /**
     * @param
     * @return java.lang.String 替换后的字符串
     * @Title: toString
     * @TitleExplain:
     * @Description: 替换str中的参数
     * @version
     * @author wudan-mac
     */
    public String toString() {
        String result = str;
        for (Iterator it = params.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            String strValue = value == null ? "" : value.toString();
            result = StringHelper.replace(result, "${" + key + "}", strValue);
        }
        return result;
    }


}
