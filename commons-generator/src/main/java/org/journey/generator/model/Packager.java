package org.journey.generator.model;


import org.journey.generator.util.ConstantUtil;

/**
 * @author wudan-mac
 * @ClassName: Packager
 * @ClassNameExplain: 要生成的接口包类
 * @Description:
 * @date 16/4/20 下午3:07
 */
public class Packager {

    //包名
    private String packageName;

    //小写包名
    private String uncapPackageName;

    //模板文件路径  默认是api 工程模板路径
    private String templetDir = ConstantUtil.API_TEMPLATE_DIR;

    //作者
    private String author;

    //时间
    private String date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUncapPackageName() {
        return uncapPackageName;
    }

    public void setUncapPackageName(String uncapPackageName) {
        this.uncapPackageName = uncapPackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        this.setUncapPackageName(packageName.toLowerCase());
    }

    public String getTempletDir() {
        return templetDir;
    }

    public void setTempletDir(String templetDir) {
        this.templetDir = templetDir;
    }
}
