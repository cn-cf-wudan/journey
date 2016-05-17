package org.journey.generator.main;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @author wudan-mac
 * @ClassName: PaginationPlugin
 * @ClassNameExplain: mybatis分页插件扩展类
 * @Description:
 * @date 16/4/22 上午9:31
 */
public class PaginationPlugin extends PluginAdapter {

    /**
     * @param
     * @return boolean
     * @Title: validate
     * @TitleExplain:
     * @Description: 重写方法 默认返回true
     * @version
     * @author wudan-mac
     */
    @Override
    public boolean validate(List<String> list) {
        return true;
    }


    /**
     * @param document          xml文档对象
     * @param introspectedTable 数据库表映射对象
     * @return boolean
     * @Title: sqlMapDocumentGenerated
     * @TitleExplain:
     * @Description: 重写方法用来在生成的mapper.xml文件中添加自定义sql
     * @version
     * @author wudan-mac
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        //获取数据库表名
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        //获取xml文件根节点
        XmlElement parentElement = document.getRootElement();
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
}
