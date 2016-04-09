package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import rewards.internal.exception.RewardDataAccessException;


@Aspect	
@Component
public class DBExceptionHandlingAspect {
	
	private Logger logger = Logger.getLogger(getClass());

	@AfterThrowing(value="execution(public * rewards.internal.*.*Repository.*(..))", throwing="e")
	public void implExceptionHandling(RewardDataAccessException e) { 
		// Log a failure warning
		logger.warn("Failed sending an email to Mister Smith : " + e + "\n");
	}
	
}
