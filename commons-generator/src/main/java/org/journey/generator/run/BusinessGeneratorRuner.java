package org.journey.generator.run;


import org.journey.generator.main.SinldoGenerator;
import org.journey.generator.model.Packager;
import org.journey.generator.util.ConstantUtil;
import org.journey.generator.util.PropertiesProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wudan-mac
 * @ClassName: GeneratorRuner
 * @ClassNameExplain: 接口文件生成器
 * @Description:
 * @date 16/4/20 下午3:16
 */
public class BusinessGeneratorRuner {

    public static void main(String[] args) throws Exception{

        SinldoGenerator sinldoGenerator = new SinldoGenerator();
        //清空目录
        sinldoGenerator.clean();
        //这里根据工程情况自己定义
        PropertiesProvider.setBasePackage("org.journey.mingrui.web.business.carinfo");
        //接口名称使用驼峰形式定义 首字母大写
        //sinldoGenerator.generatePackage("Test");
        Packager packager = new Packager();
        packager.setPackageName("ListCarInfo");
        packager.setTempletDir(ConstantUtil.WEB_JSON_TEMPLATE_DIR);
        packager.setAuthor("wudan-mac");
        packager.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sinldoGenerator.generatePackage(packager);

    }
}
