package org.journey.demoapi.business.demo.controller;

import org.journey.demoapi.business.demo.io.DemoIO;
import org.journey.demoapi.business.demo.service.IDemoService;
import org.journey.demoapi.business.demo.vo.DemoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: DemoController
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-17 17:25:48
 */
@RequestMapping(value = "")
@Controller
public class DemoController {

    static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    IDemoService demoService;

    /**
     * @api {post} /demo 中文名
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
     * @author wudan-mac
     * @date 2016-05-17 17:25:48
     */
    @RequestMapping(value = "/demo", method = {RequestMethod.POST})
    @ResponseBody
    public DemoVO demo(@Valid @RequestBody DemoIO demoIO) throws Exception {
        return demoService.demo(demoIO);
    }

}