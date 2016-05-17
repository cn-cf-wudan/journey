<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.service;

<#include "/serviceInterface_imports.include">

public interface I${className}Service {

    ModelAndView ${classNameLower}(${className}IO ${classNameLower}IO, RedirectAttributes attr) throws Exception;

}