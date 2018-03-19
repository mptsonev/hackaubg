package guice;

import com.google.inject.AbstractModule;

import services.DBConnectionService;
import services.UtilService;

public class GeneralModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(UtilService.class).asEagerSingleton();
		bind(DBConnectionService.class).asEagerSingleton();
	}
}
