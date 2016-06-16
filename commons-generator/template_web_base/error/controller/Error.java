package com.sinldo.server.business.basic.error.controller;

import com.sinldo.exceptions.ServerExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author wudan-mac
 * @ClassName: Error
 * @ClassNameExplain: web异常处理控制类
 * @Description:
 * @date 16/5/25 上午10:12
 */
@RequestMapping(value = "/error")
@Controller
public class Error {

    Logger logger = LoggerFactory.getLogger(Error.class);

    /**
     * @param
     * @return
     * @Title: error400
     * @TitleExplain:
     * @Description: 参数绑定错误
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/400")
    public ModelAndView error400(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjax(request)) {
            returnJson(response, WebExceptionConstants.ARGUMENT_ERROR_CODE,
                    request.getParameter("msg") == null ? WebExceptionConstants.ARGUMENT_ERROR_MSG : request.getParameter("msg"));
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView("base/400");
            modelAndView.addObject("msg", request.getParameter("msg"));
            return modelAndView;
        }
    }

    /**
     * @param
     * @return
     * @Title: error404
     * @TitleExplain:
     * @Description: 404not_found
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/404")
    public ModelAndView error404(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjax(request)) {
            returnJson(response, ServerExceptionConstants.NOT_FOUND_CODE, ServerExceptionConstants.NOT_FOUND_MSG);
            return null;
        } else {
            return new ModelAndView("base/404");
        }
    }

    /**
     * @param
     * @return
     * @Title: error403
     * @TitleExplain:
     * @Description: 无访问权限
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/403")
    public ModelAndView error403(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjax(request)) {
            returnJson(response, ServerExceptionConstants.CSRF_ERROR_CODE, ServerExceptionConstants.CSRF_ERROR_MSG);
            return null;
        } else {
            return new ModelAndView("base/403");
        }
    }

    /**
     * @param
     * @return
     * @Title: error500
     * @TitleExplain:
     * @Description: 服务器异常
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/500")
    public ModelAndView error500(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjax(request)) {
            returnJson(response, WebExceptionConstants.SERVER_EXCEPTION_CODE, WebExceptionConstants.SERVER_EXCEPTION_MSG);
            return null;
        } else {
            return new ModelAndView("base/500");
        }
    }

    /**
     * @param
     * @return
     * @Title: error405
     * @TitleExplain:
     * @Description: 无访问权限
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/405")
    public ModelAndView error405(RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjax(request)) {
            returnJson(response, WebExceptionConstants.ACCESS_DEFINE_CODE, WebExceptionConstants.ACCESS_DEFINE_MSG);
            return null;
        } else {
            return new ModelAndView("base/405");
        }
    }

    /**
     * @param
     * @return
     * @Title: isAjax
     * @TitleExplain:
     * @Description: 判断是否为ajax请求
     * @version 1.0.0
     * @author wudan-mac
     */
    private boolean isAjax(HttpServletRequest httpRequest) {
        if (!"XMLHttpRequest".equalsIgnoreCase(httpRequest
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param
     * @return void
     * @Title: returnJson
     * @TitleExplain:
     * @Description: 返回json 数据
     * @version
     * @author wudan-mac
     */
    private void returnJson(HttpServletResponse response, int code, String errorMessage) {

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(String.format("{\"code\":%d, \"message\":\"%s\"}", code, errorMessage).getBytes("UTF-8"));

            response.setStatus(code);
            response.setContentType("application/json;charset=UTF-8");
            response.setContentLength(output.size());

            ServletOutputStream responseOutputStream = response.getOutputStream();
            output.writeTo(responseOutputStream);
            responseOutputStream.flush();
            response.flushBuffer();
        } catch (IOException e) {
            String msg = "resolve business exception failed[biz_code=%d,biz_message=%s].";
            logger.error(String.format(msg, code, errorMessage), e);
        }
    }
}
