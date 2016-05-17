package org.journey.exception.resolver;

import org.journey.exception.core.BusinessException;
import org.journey.exception.core.RestExceptionConstants;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;


/**
 * @author wudan-mac
 * @ClassName: ExceptionResolver
 * @ClassNameExplain: 异常解析
 * @Description: 统一处理异常，json 适用于 rest api 工程使用
 * 这里没有使用注解注入
 * 考虑到这里有两个resolver 分别为 rest api工程 和 web工程服务
 * 在具体的工程中需要哪个就去引用哪个即可
 * @date 2016年4月1日 下午3:42:49
 */
public class RestExceptionResolver extends SimpleMappingExceptionResolver {

    public RestExceptionResolver() {
        //值越小，越先执行
        setOrder(-1000);
    }


    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @Title: doResolveException
     * @TitleExplain:
     * @Description: 包装异常信息的方法
     * @version
     * @author wudan-mac
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        int code = 0;
        String errorMessage = "";

        if (ex instanceof BusinessException) {

            /**
             * 1.业务异常
             */
            BusinessException businessEx = (BusinessException) ex;
            code = businessEx.getCode();
            errorMessage = businessEx.getErrorMsg();
        } else if (ex instanceof MethodArgumentNotValidException) {

            /**
             * 2.springMVC 参数绑定异常
             */
            MethodArgumentNotValidException argumentEx = (MethodArgumentNotValidException) ex;
            code = RestExceptionConstants.ARGUMENT_ERROR_CODE;
            StringBuffer sb = new StringBuffer();
            List<FieldError> errorList = argumentEx.getBindingResult().getFieldErrors();
            for (FieldError error : errorList) {
                sb.append("字段");
                sb.append(error.getDefaultMessage());
            }
            errorMessage = sb.toString();
        } else if (ex instanceof HttpMessageNotReadableException) {

            /**
             * 3.参数反序列化异常
             */
            HttpMessageNotReadableException notReadEx = (HttpMessageNotReadableException) ex;
            code = RestExceptionConstants.ARGUMENT_ERROR_CODE;
            errorMessage = notReadEx.getMessage();
        } else if (ex instanceof UndeclaredThrowableException) {

            /**
             * 4.处理  undeclaredthrowableexception异常,目前只是GsonHttpMessageConverter转换时发生的异常
             */
            code = RestExceptionConstants.ARGUMENT_ERROR_CODE;
            errorMessage = RestExceptionConstants.ARGUMENT_ERROR_MSG;
        } else {
            code = RestExceptionConstants.SERVER_EXCEPTION_CODE;
            errorMessage = RestExceptionConstants.SERVER_EXCEPTION_MSG;
        }

        logger.error(ex);
        if (handleException(response, code, errorMessage)) {
            //真正处理异常的逻辑
            return new ModelAndView();
        }
        return null;
    }


    /**
     * @param response     响应
     * @param code         错误码
     * @param errorMessage 错误描述
     * @return boolean
     * @Title: handleException
     * @TitleExplain: 处理异常
     * @Description: 真正处理异常的地方, 将异常转为json返回给客户端
     * @author wudan-mac
     */
    private boolean handleException(HttpServletResponse response, int code, String errorMessage) {
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
            return true;
        } catch (IOException e) {
            String msg = "resolve business exception failed[biz_code=%d,biz_message=%s].";
            logger.error(String.format(msg, code, errorMessage), e);
        }
        return false;
    }

}
