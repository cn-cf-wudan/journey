package org.journey.exception.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.journey.exception.core.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
* @ClassName: ExceptionAop 
* @ClassNameExplain: 通过aop处理异常
* @Description: 将异常封装成自定义异常
* @author wudan-mac
* @date 2016年4月1日 上午11:30:15 
 */
@Component
@Aspect
@Order(1)
public class ExceptionAop{
	
	public static final String CUT = "execution (* org.journey..*.*(..))";//切入点表达式
	
	/**
	* @Title: around 
	* @TitleExplain: 环绕通知
	* @Description: 环绕通知 
	* @param pjp 连接点
	* @return Object
	* @author wudan-mac
	 */
	@Around(CUT)
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Object[] args = pjp.getArgs();
		Object result = null;
		try{
			result = pjp.proceed(args);
		}catch(Throwable throwable){
			if(throwable instanceof BusinessException){//业务异常直接抛出
				throw throwable;
			}else{//不识别的check异常
				throw throwable;
			}
			
		}
		return result;
		
	}

}
