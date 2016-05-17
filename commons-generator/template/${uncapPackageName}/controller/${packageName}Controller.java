<#assign className = packager.packageName>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${packager.uncapPackageName}.controller;

<#include "/controller_imports.include">

/**
 * @ClassName: ${className}Controller
 * @ClassNameExplain:
 * @Description:
 * @author ${packager.author}
 * @date ${packager.date}
 */
@RequestMapping(value = "")
@Controller
public class ${className}Controller {

    static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

    @Resource
    I${className}Service ${classNameLower}Service;

    /**
     * @api {post} /${classNameLower} 中文名
     * @apiName name
     * @apiGroup name
     * @apiDescription text
     * @apiParam {type} name description
     * @apiSuccess {type} name description
     * @apiSuccessExample {json} Success-Response: HTTP/1.1 200 OK
     *  {
     *    "header": {
     *
     *    },
     *    "response":{
     *       "firstname": "John",
     *       "lastname": "Doe"
     *      }
     *   }
     * @apiVersion 0.0.0
     * @author ${packager.author}
     * @date ${packager.date}
     */
    @RequestMapping(value = "/${classNameLower}", method = {RequestMethod.POST})
    @ResponseBody
    public ${className}VO ${classNameLower}(@Valid @RequestBody ${className}IO ${classNameLower}IO) throws Exception {
        return ${classNameLower}Service.${classNameLower}(${classNameLower}IO);
    }

}