package org.journey.exception.core;

import org.springframework.stereotype.Component;

/**
 * @author wudan-mac
 * @ClassName: ExceptionConstants
 * @ClassNameExplain: 业务异常常量类
 * @Description: 开发者在此自定义业务异常
 * @date 2016年4月1日 上午09:16:19
 */
@Component
public class RestExceptionConstants {

    //响应成功
    public static final int SUCCESS_CODE = 10000;
    public static final String SUCCESS_MSG = "SUCCESS";

    //参数绑定异常
    public static final int ARGUMENT_ERROR_CODE = 10001;
    public static final String ARGUMENT_ERROR_MSG = "valid.param.err";
    public static final String ARGUMENT_JSON_ERROR_MSG = "json格式错误";

    //服务器异常
    public static final int SERVER_EXCEPTION_CODE = 50000;
    public static final String SERVER_EXCEPTION_MSG = "服务器异常";


}
