/**
 * Created by zhangrui on 2018/3/21 0021.
 */
package com.wdk.code.general.server.config.aop;

import com.wdk.general.core.common.logger.AccessLog;
import com.wdk.general.core.common.logger.TalStopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP配置信息
 *
 * @author zhangrui
 * @createDate 2018/3/21 0021
 */
@Component
@Aspect
public class ActionAop {
	
	@Pointcut("execution(* com.wdk.code.general.server.service..*(..))")
	public void executeService() {
	}


	/**
	 * 前置通知，方法调用前被调用
	 *
	 * @param joinPoint
	 */
	@Before(value = "execution(* com.wdk.code.general.server.web..*(..))")
	public void doBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		AccessLog.getLogger().info("doBefore service:{} arg:{}", joinPoint.getSignature().getName(), args);
	}

	/**
	 * 后置返回通知
	 * 这里需要注意的是:
	 * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
	 * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
	 * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
	 *
	 * @param joinPoint
	 */
	@AfterReturning(value = "execution(* com.wdk.code.general.server.web..*(..))", returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		AccessLog.getLogger().info("doAfterReturning service:{} arg:{} result:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), result);
	}

	/**
	 * 后置异常通知
	 * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
	 * throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
	 * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
	 *
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(value = "executeService()", throwing = "exception")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {

		AccessLog.getLogger().warn("doAfterThrowing service:{} exception:{}", joinPoint.getSignature().getName(), exception.toString());
	}

	/**
	 * 环绕通知：
	 * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
	 * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
	 */
	@Around("executeService()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		TalStopWatch stopWatch = new TalStopWatch(proceedingJoinPoint.getSignature().getName());
		Object ret = proceedingJoinPoint.proceed();//调用执行目标方法
		stopWatch.stop();
		return ret;
	}
}

