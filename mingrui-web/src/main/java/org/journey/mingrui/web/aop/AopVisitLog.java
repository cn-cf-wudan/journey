package org.journey.mingrui.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.journey.dao.mysql.mingrui.achieve.VisitLogMapper;
import org.journey.mingrui.web.ext.MethodLog;
import org.journey.mingrui.web.util.CommonUtil;
import org.journey.po.mingrui.mysql.VisitLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author wudan-mac
 * @ClassName: AopVisitLog
 * @ClassNameExplain:
 * @Description:
 * @date 16/6/16 上午9:01
 */
@Component
@Aspect
@Order(2)
public class AopVisitLog {

    private static Logger logger = LoggerFactory.getLogger(AopVisitLog.class);
    private final String CUT = "execution (* org.journey..*.controller.*.*(..))";//切点表达式

    @Resource
    VisitLogMapper visitLogMapper;

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
        try {
            logger.debug("记录访问日志--------------");
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String ip = CommonUtil.getIpAddr(request);
            VisitLog visitLog = new VisitLog();
            String functionDescription = getMthodRemark(pjp);
            String functionName = pjp.getSignature().getName();
            visitLog.setVisitIp(ip);
            visitLog.setFunctionName(functionName);
            visitLog.setFunctionDescription(functionDescription);
            visitLog.setVisitTime(new Date());
            visitLogMapper.insertSelective(visitLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pjp.proceed();
    }

    // 获取方法的中文备注____用于记录用户的操作日志描述
    public static String getMthodRemark(ProceedingJoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    MethodLog methodCache = m.getAnnotation(MethodLog.class);
                    if (methodCache != null) {
                        methode = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methode;
    }
}
