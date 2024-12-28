package com.challenge.anotion;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {
	@Around("@annotation(TimeMonitor)")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println(String.format("Method Name: %s, Arg Name: %s", joinPoint.getSignature().getName(),
				Arrays.toString(((CodeSignature) joinPoint.getSignature()).getParameterNames())));
		Object proceed = joinPoint.proceed();
		System.out.println(
				String.format("Method %s contains the following return type: %s", joinPoint.getSignature().getName(),
						((MethodSignature) joinPoint.getSignature()).getReturnType().toGenericString()));
		return proceed;
	}

}
