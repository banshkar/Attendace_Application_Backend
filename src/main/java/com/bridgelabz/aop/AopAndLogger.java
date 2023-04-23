package com.bridgelabz.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
@Aspect
@Configuration
public class AopAndLogger {
	
	private Logger logger = LoggerFactory.getLogger(AopAndLogger.class);
	
	@Before(value="execution(* com.bridgelabz.controller.*.*(..) )")
	public void logStatementBefore(JoinPoint joinPoint) {
		logger.info(" initial Executing {}", joinPoint.getSignature());
		
	}
	
	@After(value = "execution(* com.bridgelabz.controller.*.*(..))")
	public void logStatementAfter(JoinPoint joinPoint) {
		logger.info("Completed Execution {}", joinPoint.getSignature());
	}

}
