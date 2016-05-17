<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.io;

/**
 * @ClassName: ${className}IO
 * @ClassNameExplain:
 * @Description:
 * @author ${packager.author}
 * @date ${packager.date}
 */
public class ${className}IO extends RequestHeader{

}