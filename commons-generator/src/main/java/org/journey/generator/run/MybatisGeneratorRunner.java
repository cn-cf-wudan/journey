package org.journey.generator.run;

import org.mybatis.generator.api.ShellRunner;

/**
 * @author wudan-mac
 * @ClassName: MybatisGenerator
 * @ClassNameExplain: mybatis 代码生成工具
 * @Description:
 * @date 16/4/15 下午3:09
 */
public class MybatisGeneratorRunner {

    /**
     * 这个类是用来生成 底层dao 文件的
     * 请不要随意使用这个类 否则可能会造成基础dao文件被覆盖
     * @param args
     */
    public static void main(String[] args) {
        String config = MybatisGeneratorRunner.class.getClassLoader()
                .getResource("mybatis-generator-config.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
}
