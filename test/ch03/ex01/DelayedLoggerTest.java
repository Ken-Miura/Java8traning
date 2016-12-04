package ch03.ex01;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class DelayedLoggerTest {

	@Test
	public void logIf_DoesNotEvaluateConditionIfNotLogging() {
		final Logger logger = Logger.getLogger(DelayedLogger.class.getName());
		logger.setLevel(Level.FINER);
				 
		 final DelayedLogger delayedLogger = new DelayedLogger(logger);
		 delayedLogger.logIf(Level.FINEST, 
				 ()->{throw new RuntimeException("condition is evaluated");}, 
				 ()->{throw new RuntimeException("message is evaluated");}); 
	}
}
