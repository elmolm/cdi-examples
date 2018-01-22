package blog.elmland.cdiexamples.cdi2.dynamicfactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {
	@Produces
	public Logger createLogger(InjectionPoint injectionPoin) {
		return LoggerFactory.getLogger(injectionPoin.getMember().getDeclaringClass());
	}
}
