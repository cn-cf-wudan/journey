package org.journey.generator.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wudan-mac
 * @ClassName: FileHelper
 * @ClassNameExplain: 文件操作辅助类
 * @Description:
 * @date 16/4/20 下午3:40
 */
public class FileHelper {

    /**
     * @param baseDir 基础路径
     * @param file    文件绝对路径
     * @return java.lang.String
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 获取相对路径
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file))
            return "";
        if (baseDir.getParentFile() == null)
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    /**
     * @param file      文件夹
     * @param collector 存储文件夹下文件的集合
     * @return void
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 获取文件夹下全部文件
     * @version 1.0.0
     * @author wudan-mac
     */
    public static void listFiles(File file, List collector) throws IOException {
        collector.add(file);
        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                listFiles(subFiles[i], collector);
            }
        }
    }

    /**
     * @param file 文件
     * @return boolean  true 需要忽略   false 不需要忽略
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 判断是否为要忽略的文件
     * @version 1.0.0
     * @author wudan-mac
     */
    private static boolean isIgnoreFile(File file) {
        List ignoreList = new ArrayList();
        ignoreList.add(".svn");
        ignoreList.add("CVS");
        ignoreList.add(".idea");
        ignoreList.add(".iml");
        for (int i = 0; i < ignoreList.size(); i++) {
            if (file.getName().equals(ignoreList.get(i))) {
                return true;
            }
        }
        return false;
    }
}
