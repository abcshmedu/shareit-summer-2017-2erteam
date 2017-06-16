package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.persistence.Persistence;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import static org.mockito.Mockito.mock;
import geschaeftslogik.MediaService;
import com.google.inject.AbstractModule;

/**
 * 
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
//CHECKSTYLE:OFF
public class TestInjector implements Feature {
    private static final Injector INJECTOR = Guice.createInjector(new AbstractModule() {
    @Override
    protected void configure() {
            bind(MediaService.class).toInstance(mock(MediaService.class));
            bind(Persistence.class).toInstance(mock(Persistence.class));
        }
    });
    
    public boolean configure(FeatureContext context) {
        ServiceLocator serviceLocator = ServiceLocatorProvider.getServiceLocator(context);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(getInjector());
        return true;
    }
    
    /**
     * Getter.
     * @return injector
     */
    public static Injector getInjector() {
        return INJECTOR;
    }
}
