<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.service.impl;

<#include "/serviceImpl_imports.include">

/**
 * @ClassName: ${className}ServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author ${packager.author}
 * @date ${packager.date}
 */
@Service
public class ${className}ServiceImpl implements I${className}Service {

    static final Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Resource

    @Override
    public ${className}VO ${classNameLower}(${className}IO ${classNameLower}IO) throws Exception {
        
        ${className}VO ${classNameLower}VO = new ${className}VO();


        return ${classNameLower}VO == null ? new ${className}VO() : ${classNameLower}VO;
    }
}