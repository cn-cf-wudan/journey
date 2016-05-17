package org.journey.generator.main;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.journey.generator.model.Packager;
import org.journey.generator.util.ConstantUtil;
import org.journey.generator.util.FileHelper;
import org.journey.generator.util.PropertiesProvider;
import org.journey.generator.util.StringTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wudan-mac
 * @ClassName: SinldoGenerator
 * @ClassNameExplain: 生成器类
 * @Description:
 * @date 16/4/20 下午3:10
 */
public class SinldoGenerator {

    /**
     * @Title: generatePackage
     * @TitleExplain:
     * @Description: 对外开放的生成文件方法
     * @param packageName 包名
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    public void generatePackage(String packageName) throws Exception {
        Packager packager = new Packager();
        packager.setPackageName(packageName);
        generatePackage(packager);
    }

    /**
     * @Title: generatePackage
     * @TitleExplain:
     * @Description: 生成接口包和文件的方法
     * @param packager 封装了包属性的类
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    public void generatePackage(Packager packager) throws Exception {

        System.out.println("***************************************************************");
        System.out.println("* BEGIN generate package:" + packager.getPackageName());
        System.out.println("***************************************************************");
        File projectFile = new File("");
        String projectPath =  projectFile.getAbsolutePath();
        /**
         * 获取模板文件路径
         * 兼容idea  和  eclipse
         * 两个工具工程路径不同
         */
        String templatePath = "";
        if(projectPath.indexOf(PropertiesProvider.getProperty(ConstantUtil.MODEL_NAME_KEY)) >= 0){
            templatePath = projectPath + File.separator
                    + packager.getTempletDir();
        }else{
            templatePath = PropertiesProvider.getProperty(ConstantUtil.MODEL_NAME_KEY)
                    + File.separator
                    + packager.getTempletDir();
        }

        //获取模板文件夹对象
        File templateRootDir = new File(templatePath).getAbsoluteFile();

        //设置freemarker配置对象
        Configuration config = new Configuration();
        //设置模板文件夹
        config.setDirectoryForTemplateLoading(templateRootDir);
        //设置数字格式化格式
        config.setNumberFormat(ConstantUtil.NUMBER_FORMAT);
        //设置布尔类型格式
        config.setBooleanFormat(ConstantUtil.BOOLEAN_FORMAT);

        /**
         * 获取模板文件夹下全部模板文件对象
         */
        List<File> files = new ArrayList();
        FileHelper.listFiles(templateRootDir, files);

        /**
         * 遍历文件对象集合 并根据需要生成文件
         */
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            //获取模板文件相对路径
            String templateRelativePath = FileHelper.getRelativePath(templateRootDir, file);
            //输出路径
            String outputFilePath = templateRelativePath;

            /**
             *忽略文件夹 和 隐藏文件
             */
            if (file.isDirectory() || file.isHidden()) {
                continue;
            }

            /**
             * 忽略空路径
             */
            if (templateRelativePath.trim().equals("")) {
                continue;
            }

            /**
             * 忽略注释文件
             */
            if (file.getName().toLowerCase().endsWith(ConstantUtil.INCLUDE_FILE_SUFFIX)) {
                continue;
            }
            //生成文件
            generateFile(packager, config, templateRelativePath, outputFilePath);
        }
    }

    /**
     * @Title: generateFile
     * @TitleExplain:
     * @Description: 生成文件
     * @param packager 包信息对象
     * @param config 配置参数对象
     * @param templateRelativePath 模板文件相对路径
     * @param outputFilePath 输出文件路径
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    private void generateFile(Packager packager, Configuration config, String templateRelativePath, String outputFilePath) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, TemplateException {
        //获取模板对象
        Template template = config.getTemplate(templateRelativePath);
        //获取生成文件对象
        String targetFilename = getTargetFilename(packager, outputFilePath);
        //获取模板数据对象
        Map templateDataModel = getTemplateDataModel(packager);
        //获取生成文件的绝对路径
        File absoluteOutputFilePath = getAbsoluteOutputFilePath(targetFilename);
        System.out.println("[generate]\t template:" + templateRelativePath + " to " + targetFilename);
        //保存文件
        saveNewOutputFileContent(template, templateDataModel, absoluteOutputFilePath);
    }

    /**
     * @Title: getTemplateDataModel
     * @TitleExplain:
     * @Description: 得到模板数据对象
     * @param packager 包信息对象
     * @return java.util.Map
     * @version 1.0.0
     * @author wudan-mac
     */
    private Map getTemplateDataModel(Packager packager) {
        Map model = new HashMap();
        model.putAll(PropertiesProvider.getProperties());
        model.put(ConstantUtil.PACKAGE_KEY, packager);
        return model;
    }

    /**
     * @Title: getAbsoluteOutputFilePath
     * @TitleExplain:
     * @Description: 获取生成文件绝对路径
     * @param targetFilename 生成文件名
     * @return java.io.File
     * @version 1.0.0
     * @author wudan-mac
     */
    private File getAbsoluteOutputFilePath(String targetFilename) {
        String outRoot = getOutRootDir();
        File outputFile = new File(outRoot, targetFilename);
        outputFile.getParentFile().mkdirs();
        return outputFile;
    }

    /**
     * @Title: getOutRootDir
     * @TitleExplain:
     * @Description: 获取输出文件夹
     * @return java.lang.String
     * @version
     * @author wudan-mac
     */
    private String getOutRootDir() {
        File projectFile = new File("");
        String projectPath =  projectFile.getAbsolutePath();
        /**
         * 获取输出文件路径
         * 兼容idea  和  eclipse
         * 两个工具工程路径不同
         */
        String outputPath = "";
        if(projectPath.indexOf(PropertiesProvider.getProperty(ConstantUtil.MODEL_NAME_KEY)) >= 0){
            outputPath = projectPath + File.separator
                    + PropertiesProvider.getProperty(ConstantUtil.OUT_DIR_KEY);
        }else{
            outputPath = PropertiesProvider.getProperty(ConstantUtil.MODEL_NAME_KEY)
                    + File.separator
                    + PropertiesProvider.getProperty(ConstantUtil.OUT_DIR_KEY);
        }

        return outputPath;
    }

    /**
     * @Title: getTargetFilename
     * @TitleExplain:
     * @Description: 获取生成文件名
     * @param packager 包信息对象
     * @param templateFilepath 模板文件路径
     * @return java.lang.String
     * @version 1.0.0
     * @author wudan-mac
     */
    private String getTargetFilename(Packager packager, String templateFilepath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map fileModel = getFilepathDataModel(packager);
        String targetFilename = resolveFile(templateFilepath, fileModel);
        return targetFilename;
    }

    /**
     * @Title: getFilepathDataModel
     * @TitleExplain:
     * @Description: 得到生成"文件目录/文件路径"的Model
     * @param packager 包信息对象
     * @return java.util.Map
     * @version 1.0.0
     * @author wudan-mac
     */
    private Map getFilepathDataModel(Packager packager) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map fileModel = BeanUtils.describe(packager);
        fileModel.putAll(PropertiesProvider.getProperties());
        return fileModel;
    }

    private String resolveFile(String templateFilepath, Map fileModel) {
        return new StringTemplate(templateFilepath, fileModel).toString();
    }

    /**
     * @Title: saveNewOutputFileContent
     * @TitleExplain:
     * @Description:
     * @param template 模板对象
     * @param model 模板数据对象
     * @param outputFile 输出文件
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    private void saveNewOutputFileContent(Template template, Map model, File outputFile) throws IOException, TemplateException {
        FileWriter out = new FileWriter(outputFile);
        //调用freemarker api根据模板生成文件
        template.process(model, out);
        out.close();
    }

    /**
     * @Title: clean
     * @TitleExplain:
     * @Description: 清空输出文件夹
     * @param
     * @return void
     * @version 1.0.0
     * @author wudan-mac
     */
    public void clean() throws IOException {
        String outRoot = getOutRootDir();
        FileUtils.deleteDirectory(new File(outRoot));
        System.out.println("[Delete Dir]	"+outRoot);
    }
}
