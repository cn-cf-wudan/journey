<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.service;

<#include "/serviceInterface_imports.include">

/**
 * @ClassName: I${className}Service
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author ${packager.author}
 * @date ${packager.date}
 */
public interface I${className}Service {

    /**
    * @Title: demoApi
    * @TitleExplain:
    * @Description: 业务层接口方法定义
    * @param ${className}VO
    * @return ${basepackage}.${packager.uncapPackageName}.vo.${className}VO
    * @version
    * @author wudan-mac
    */
    ${className}VO ${classNameLower}(${className}IO ${classNameLower}IO) throws Exception;

}