package org.journey.generator.util;

/**
 * @author wudan-mac
 * @ClassName: StringHelper
 * @ClassNameExplain:
 * @Description:
 * @date 16/4/21 上午11:58
 */
public class StringHelper {

    /**
     * @param sqlName 要处理的字符串
     * @return java.lang.String 驼峰形式的字符串
     * @Title: makeAllWordFirstLetterUpperCase
     * @TitleExplain:
     * @Description: 将传入字符串转为驼峰形式
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String makeAllWordFirstLetterUpperCase(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        String preStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (preStr.length() == 1) {
                result += strs[i];
            } else {
                result += capitalize(strs[i]);
            }
            preStr = strs[i];
        }
        return result;
    }

    /**
     * @param inString   要处理的字符串
     * @param oldPattern 要替换的字符
     * @param newPattern 替换后的字符
     * @return java.lang.String
     * @Title: replace
     * @TitleExplain:
     * @Description: 替换字符串中的字符
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (inString == null) {
            return null;
        }
        if (oldPattern == null || newPattern == null) {
            return inString;
        }

        StringBuffer sbuf = new StringBuffer();
        // output StringBuffer we'll build up
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));

        // remember to append any characters to the right of a match
        return sbuf.toString();
    }

    /**
     * @param str 要处理的字符串
     * @return java.lang.String
     * @Title: capitalize
     * @TitleExplain:
     * @Description: 首字母大写
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * @param str 要处理的字符串
     * @return java.lang.String
     * @Title: firstToLower
     * @TitleExplain:
     * @Description: 首字母小写
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String firstToLower(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * @param str 要处理的字符串
     * @return java.lang.String
     * @Title: uncapitalize
     * @TitleExplain:
     * @Description: 全部小写
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * @param str     要处理的字符串
     * @param capitalize  true  首字母大写  false 首字母小写
     * @return java.lang.String
     * @Title: changeFirstCharacterCase
     * @TitleExplain:
     * @Description: 改变单词首字母状态
     * @version 1.0.0
     * @author wudan-mac
     */
    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }
}
