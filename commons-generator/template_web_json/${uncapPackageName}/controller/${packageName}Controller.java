<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.controller;

<#include "/controller_imports.include">

@RequestMapping(value = "")
@Controller
public class ${className}Controller {

    static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

    @Resource
    I${className}Service ${classNameLower}Service;

    @RequestMapping(value = "/${classNameLower}")
    @ResponseBody
    public ${className}VO ${classNameLower}(@Valid @ModelAttribute("${classNameLower}IO") ${className}IO ${classNameLower}IO, RedirectAttributes attr) throws Exception {
        return ${classNameLower}Service.${classNameLower}(${classNameLower}IO, attr);
    }

}