package org.journey.exception.core;

/**
 * @author wudan-mac
 * @ClassName: ExceptionConstants
 * @ClassNameExplain: 业务异常常量类
 * @Description: 开发者在此自定义业务异常
 * @date 2016年4月1日 上午09:16:19
 */
public class RestExceptionConstants {

    //响应成功
    public static final int SUCCESS_CODE = 10000;
    public static final String SUCCESS_MSG = "SUCCESS";

    //参数绑定异常
    public static final int ARGUMENT_ERROR_CODE = 10001;
    public static final String ARGUMENT_ERROR_MSG = "参数格式错误";

    //服务器异常
    public static final int SERVER_EXCEPTION_CODE = 50000;
    public static final String SERVER_EXCEPTION_MSG = "服务器异常";


}
