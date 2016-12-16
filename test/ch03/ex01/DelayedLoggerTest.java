package ch03.ex01;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class DelayedLoggerTest {

	@Test
	public void logIf_EvaluatesConditionIfLogging() {
		final Logger logger = Logger.getLogger(DelayedLogger.class.getName());
		logger.setLevel(Level.FINEST);
		
		final AtomicInteger passCounter = new AtomicInteger();
		final DelayedLogger delayedLogger = new DelayedLogger(logger);
		delayedLogger.logIf(Level.FINEST, 
				 ()->{ synchronized(passCounter) { passCounter.getAndIncrement(); } return true;}, 
				 ()->{ synchronized(passCounter) { passCounter.getAndIncrement(); } return "message";});
		assertThat(passCounter.get(), is(2));
	}
	
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
