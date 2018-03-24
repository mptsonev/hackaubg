package guice;

import com.google.inject.AbstractModule;

import services.DBConnectionService;

public class GeneralModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(DBConnectionService.class).asEagerSingleton();
	}
}
