package socialNetworkLite.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class GenericApplication {

	private static Logger logger = LoggerFactory.getLogger(GenericApplication.class);

	public static void main(String[] args) throws Throwable {
		try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
			ctx.load("classpath:application-context.xml");
			ctx.refresh();
			ctx.registerShutdownHook();
			runApplication(args, ctx);
		} catch (Throwable t) {
			logger.error("Application had exception.", t);
			throw t;
		}
	}

	private static void runApplication(String[] args, GenericXmlApplicationContext ctx) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		if (args.length < 2) {
			throw new IllegalArgumentException("Require arguments: beanName runMethodName");
		}
		String beanName = args[0];
		String methodName = args[1];
		Object bean = ctx.getBean(beanName);
		Method method = bean.getClass().getMethod(methodName);
		method.invoke(bean);
	}
}
