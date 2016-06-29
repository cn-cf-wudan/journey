package org.journey.demoapi.business.getuserinfo.controller;

import org.journey.demoapi.business.getuserinfo.io.GetUserInfoIO;
import org.journey.demoapi.business.getuserinfo.service.IGetUserInfoService;
import org.journey.demoapi.business.getuserinfo.vo.GetUserInfoVO;
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
 * @ClassName: GetUserInfoController
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-06-29 19:55:29
 */
@RequestMapping(value = "")
@Controller
public class GetUserInfoController {

    static final Logger logger = LoggerFactory.getLogger(GetUserInfoController.class);

    @Resource
    IGetUserInfoService getUserInfoService;

    /**
     * @api {post} /getUserInfo 中文名
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
     * @date 2016-06-29 19:55:29
     */
    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST})
    @ResponseBody
    public GetUserInfoVO getUserInfo(@Valid @RequestBody GetUserInfoIO getUserInfoIO) throws Exception {
        return getUserInfoService.getUserInfo(getUserInfoIO);
    }

}