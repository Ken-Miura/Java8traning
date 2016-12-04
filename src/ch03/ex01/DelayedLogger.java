package ch03.ex01;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class DelayedLogger {

	private final Logger logger;
	
	public DelayedLogger (Logger logger) {
		Objects.requireNonNull(logger, "logger must not be null");
		this.logger = logger;
	}
	
	
	public void logIf (Level level, BooleanSupplier conditionSupplier, Supplier<String> msgSupplier) {
		Objects.requireNonNull(level, "level must not be null");
		Objects.requireNonNull(conditionSupplier, "conditionSupplier must not be null");
		Objects.requireNonNull(msgSupplier, "msgSupplier must not be null");
		if (!logger.isLoggable(level)) {
			return;
		}
		if (conditionSupplier.getAsBoolean()) {
			logger.log(level, msgSupplier.get());
		}
	}
	
	public static void main(String[] args) {
		final Level level = Level.FINEST;
		
		final Logger logger = Logger.getLogger(DelayedLogger.class.getName());
		logger.setLevel(level);
		
		 final ConsoleHandler handler = new ConsoleHandler();
		 handler.setLevel(level);
		 logger.addHandler(handler);
		 logger.setUseParentHandlers(false);
				 
		 final DelayedLogger delayedLogger = new DelayedLogger(logger);
		 int[] a = new int[11];
		 int i = 10;
		 delayedLogger.logIf(Level.FINEST, ()->i==10, ()->"a[10] = " + a[10]); 
	}
}
