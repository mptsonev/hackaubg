package guice;

import com.google.inject.AbstractModule;

import services.UtilService;

public class GeneralModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(UtilService.class).asEagerSingleton();
	}
}
