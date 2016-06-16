package org.journey.exception.core;

/**
 * @author wudan-mac
 * @ClassName: ServerExceptionConstants
 * @ClassNameExplain:
 * @Description: server工程异常码和消息定义
 * @date 16/5/25 上午9:27
 */
public class WebExceptionConstants {

    //参数绑定异常
    public static final int ARGUMENT_ERROR_CODE = 400;
    public static final String ARGUMENT_ERROR_MSG = "参数格式/绑定异常";

    //服务器异常
    public static final int SERVER_EXCEPTION_CODE = 500;
    public static final String SERVER_EXCEPTION_MSG = "服务器异常";

    //资源未找到
    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MSG = "资源未找到";

    //csrf错误
    public static final int CSRF_ERROR_CODE = 403;
    public static final String CSRF_ERROR_MSG = "CSRF TOKEN错误";

    //csrf错误
    public static final int ACCESS_DEFINE_CODE = 405;
    public static final String ACCESS_DEFINE_MSG = "没有访问权限";
}
