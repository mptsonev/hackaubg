package guice;

import com.google.inject.AbstractModule;

public class GeneralModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MyGuiceServletConfig.class).asEagerSingleton();
	}
}
