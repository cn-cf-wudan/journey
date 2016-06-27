package org.journey.exception.resolver;

import org.journey.exception.core.BusinessException;
import org.journey.exception.core.WebExceptionConstants;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wudan-mac
 * @ClassName: WebExceptionResolver
 * @ClassNameExplain: 统一处理异常web 适用于 web 工程使用
 * 				这里没有使用注解注入
 * 				考虑到这里有两个resolver 分别为 rest api工程 和 web工程服务
 * 			    在具体的工程中需要哪个就去引用哪个即可
 * @Description:
 * @date 16/5/17 下午2:25
 */
public class WebExceptionResolver extends SimpleMappingExceptionResolver {

    public WebExceptionResolver() {
        //值越小，越先执行
        setOrder(-1000);
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        int code = 0;
        String errorMessage = "";
        if (ex instanceof BusinessException) {
            modelAndView.setViewName("redirect:/error/500");
        } else if (ex instanceof MethodArgumentNotValidException) {//处理参数绑定异常
            MethodArgumentNotValidException argumentEx = (MethodArgumentNotValidException) ex;
            code = WebExceptionConstants.ARGUMENT_ERROR_CODE;
            StringBuffer sb = new StringBuffer();
            List<FieldError> errorList = argumentEx.getBindingResult().getFieldErrors();
            for (FieldError error : errorList) {
                sb.append(error.getObjectName());
                sb.append("对象的");
                sb.append(error.getField());
                sb.append("字段");
                sb.append(error.getDefaultMessage() + "\n");
            }
            errorMessage = sb.toString();
            logger.debug(errorMessage);
            modelAndView.addObject("msg", errorMessage);
            modelAndView.setViewName("redirect:/error/400");
        } else if (ex instanceof HttpMessageNotReadableException) {//处理反序列化异常
            modelAndView.addObject("msg", "入参格式异常");
            modelAndView.setViewName("redirect:/error/400");
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            code = WebExceptionConstants.ARGUMENT_ERROR_CODE;
            StringBuffer sb = new StringBuffer();
            List<FieldError> errorList = bindException.getBindingResult().getFieldErrors();
            for (FieldError error : errorList) {
                sb.append(error.getObjectName());
                sb.append("对象的");
                sb.append(error.getField());
                sb.append("字段");
                sb.append(error.getDefaultMessage() + "\n");
            }
            errorMessage = sb.toString();
            logger.debug(errorMessage);
            modelAndView.addObject("msg", errorMessage);
            modelAndView.setViewName("redirect:/error/400");
        } else {
            modelAndView.setViewName("redirect:/error/500");
        }
        logger.error(ex);
        return modelAndView;
    }
}
