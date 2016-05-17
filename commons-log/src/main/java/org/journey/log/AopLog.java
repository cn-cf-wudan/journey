package org.journey.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.journey.exception.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author wudan-mac
 * @ClassName: AopLog
 * @ClassNameExplain:通过aop处理日志
 * @Description: 统一处理日志 ，该切面的执行顺序要在ExceptionAop之后，这样才能保证错误堆栈打印出来
 * @date 2016年4月1日 上午10:20:32
 */
@Component
@Aspect
@Order(3)
public class AopLog {

    private static Logger logger = LoggerFactory.getLogger(AopLog.class);
    private static Logger slowLogger = LoggerFactory.getLogger("slowLog");//慢日志

    private final String CUT = "execution (* org.journey..*.service.impl.*.*(..))";//切点表达式

    /**
     * @param pjp 连接点对象
     * @return void
     * @Title: before
     * @TitleExplain:前置通知
     * @Description: 方法执行前执行
     * @version
     * @author wudan-mac
     */
    @Before(CUT)
    public void before(JoinPoint pjp) throws Throwable {
        logger.debug("----------" + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + " start------------");
        for (Object obj : pjp.getArgs()) {
            logger.debug("params:" + (obj != null ? obj.toString() : ""));
        }
        return;
    }

    /**
     * @param pjp 连接点对象
     * @return void
     * @Title: after
     * @TitleExplain: 后置通知
     * @Description: 方法执行后执行
     * @author wudan-mac
     */
    @After(CUT)
    public void after(JoinPoint pjp) throws Throwable {
        logger.debug("----------" + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + " end------------");
        return;
    }

    /**
     * @param pjp 连接点对象
     * @return Object
     * @Title: around
     * @TitleExplain: 环绕通知
     * @Description: 环绕通知
     * @author wudan-mac
     */
    @Around(CUT)
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = pjp.proceed(args);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            //打印错误日志和堆栈信息
            logger.error("{}{} failed.", pjp.getTarget().getClass(), pjp.getSignature().getName());
            for (Object obj : pjp.getArgs()) {
                logger.error(obj != null ? obj.toString() : "");
            }
            logger.error("", e);
            throw e;
        }

        //慢日志־
        long end = System.currentTimeMillis();
        if (end - start >= 3000) {//大于三秒记录
            slowLogger.info(pjp.getSignature().getName() + " cost " + (end - start) + "ms ---------------");
        }

        return result;

    }

}
