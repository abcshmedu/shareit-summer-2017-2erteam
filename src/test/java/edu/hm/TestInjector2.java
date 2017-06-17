package edu.hm;

import static org.mockito.Mockito.mock;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import geschaeftslogik.*;
import persistence.Persistence;

/**
 * Context Listener to enable usage of google guice together with jersey.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class TestInjector2 extends GuiceServletContextListener {

    private static final Injector INJECTOR = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(MediaService.class).toInstance(mock(MediaService.class));
            bind(Persistence.class).toInstance(mock(Persistence.class));
        }
    });

    @Override
    protected Injector getInjector() {
        return INJECTOR;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     * @return Injector instance.
     */
    public static Injector getInjectorInstance() {
        return INJECTOR;
    }

}
