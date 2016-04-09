package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rewards.internal.monitor.Monitor;
import rewards.internal.monitor.MonitorFactory;

// IMPORTANT NOTE 1: The JUnit tests you will run in this lab already
// work. Just getting a green test does not indicate success. You must
// also get logging messages in the console.
//
// IMPORTANT NOTE 2: Students often find this one of the hardest labs.
// If you get totally stick PLEASE ask a colleague or your instructor -
// don't waste the whole lab trying to fix your first pointcut expression.
//
// 	TODO-01: Read the Notes above! Then ...
//  Indicate this class is an aspect.  
//	Also mark it as a component.  
//	Place an @Autowired annotation on the constructor.

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = Logger.getLogger(getClass());
	private MonitorFactory monitorFactory;

	@Autowired
	public LoggingAspect(MonitorFactory monitorFactory) {
		super();
		this.monitorFactory = monitorFactory;
	}

	
	//	TODO-02: Mark this method with an advice type annotation.
	//	Decide which advice type is most appropriate.
	//  Write a pointcut expression that selects only find* methods.
	//
	//  HINT: The pointcut expression can be very hard to work out, if
	//  you get stuck refer to the slides & try writing a very specific
	//  pointcut expression to match AccountRepository.findByCreditCard(...).
	//  If that works, try to make it match any find*() method on any
	//  class whose name ends in Repository.
	//
	//  If you are really stuck, PLEASE ask a colleague or your instructor
	// for help.

	@Before("execution(* *..*Repository.find*(..))")
	public void implLogging(JoinPoint joinPoint) {
		logger.info("Logging: Class - " + joinPoint.getTarget().getClass()
				+ "; Executing before " + joinPoint.getSignature().getName()
				+ "() method");
	}
	
	
	//	TODO-06: Mark this method as around advice.  Write a pointcut 
	//	expression to match on all update* methods on Repository classes.
	//
	//  HINT: Again, the pointcut expression can be hard to work out, so if
	//  you get stuck, refer to the pointcut expression you wrote above for 
	//  implLogging(), this one is similar.
	// 
	//  If you are really stuck, PLEASE ask a colleague or your instructor.

	@Around("execution(* *..*Repository.update*(..))")
	public Object monitor(ProceedingJoinPoint repositoryMethod) throws Throwable {
		String name = createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {

			//  TODO-07: Add the logic to proceed with the target method invocation.
			//  Be sure to return the target method's return value to the caller.

//			return new String("Comment this line after completing TODO-07");
			return repositoryMethod.proceed();

		} finally {
			monitor.stop();
			logger.info(monitor);
		}
	}
	
	//	TODO-08: After completing the monitor method above, 
	//	save all work, run the RewardNetworkTests.  It should pass,
	//	and you should see console output from the monitor method.
	
	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	} 
}