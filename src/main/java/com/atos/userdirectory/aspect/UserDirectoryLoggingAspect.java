package com.atos.userdirectory.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserDirectoryLoggingAspect {

	/**
	 * Class that allows the management of the AOP (Aspect Oriented Programming)
	 */

	// Setup logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// setup pointcuts

	@Pointcut("execution(* com.atos.userdirectory.rest.UserRestController.*(..))")
	private void forControllerLayer() {

	}

	@Pointcut("execution(* com.atos.userdirectory.service.UserServiceImpl.*(..))")
	private void forServiceLayer() {

	}

	@Pointcut("execution(* com.atos.userdirectory.dao.UserDAOImpl.*(..))")
	private void forDAOLayer() {

	}

	@Pointcut("forControllerLayer() || forServiceLayer() || forDAOLayer()")
	private void forAppFlow() {

	}

	// add @before advice

	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// display called method
		String theMethod = theJoinPoint.getSignature().toShortString();
		logger.info("=====>> @Before: method: " + theMethod);

		// display arguments
		int i = 1;
		for (Object tempArg : theJoinPoint.getArgs()) {
			logger.info("=====>> Arg " + i + ": " + tempArg);
		}

	}

	// add @AfterReturning advice

	@AfterReturning(pointcut = "forControllerLayer() || forServiceLayer() || forDAOLayer()", returning = "result")
	public void afterReturning(JoinPoint theJoinPoint, Object result) {

		// display called method
		String theMethod = theJoinPoint.getSignature().toShortString();
		logger.info("=====>> @AfterReturning: method: " + theMethod);

		// display result
		logger.info("=====>> @AfterReturning: result: " + result);
	}

}
