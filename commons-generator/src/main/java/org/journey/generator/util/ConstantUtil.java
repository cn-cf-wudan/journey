package org.journey.generator.util;

/**
 * @author wudan-mac
 * @ClassName: ConstantUtil
 * @ClassNameExplain:
 * @Description:
 * @date 16/4/21 上午11:29
 */
public class ConstantUtil {

    //模板文件夹参数的key值
    public final static String TEMPLATE_DIR_KEY = "templateDirRoot";

    //数字格式化模板
    public final static String NUMBER_FORMAT = "###############";

    //布尔值格式化模板
    public final static String BOOLEAN_FORMAT = "true,false";

    //注释文件后缀
    public final static String INCLUDE_FILE_SUFFIX = ".include";

    //包model存放时的key
    public final static String PACKAGE_KEY = "packager";

    //输出文件夹的key
    public final static String OUT_DIR_KEY = "outRoot";

    //模块名称参数的key
    public static final String MODEL_NAME_KEY = "modelName";

    //api工程模板文件路径
    public final static String API_TEMPLATE_DIR = "template";

    //web 工程模板文件路径(返回modelView)
    public final static String WEB_MODEL_VIEW_TEMPLATE_DIR = "template_web_model";

    //web工程模板文件路径(返回json)
    public final static String WEB_JSON_TEMPLATE_DIR = "template_web_json";

    //web工程基础模板文件路径(登录/登出/失效/error等)
    public final static String WEB_BASE_TEMPLATE_DIR = "template_web_base";

}
