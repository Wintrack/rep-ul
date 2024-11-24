package ca.ulaval.glo4003;

import ca.ulaval.glo4003.init.registry.AppRegistry;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("all")
public class RepULMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepULMain.class);

    public static final String BASE_URI = "http://localhost:8181/";

    private static final AppRegistry APP_REGISTRY = new AppRegistry();

    public static void main(String[] args) throws Exception {
        LOGGER.info("Setup resources (API)");
        APP_REGISTRY.registerServices();
        APP_REGISTRY.initCronJob();
        try {
            LOGGER.info("Setup http server");
            final Server server = JettyHttpContainerFactory.createServer(
                    URI.create(BASE_URI),
                    APP_REGISTRY.registerConfig()
            );

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    LOGGER.info("Shutting down the application...");
                    server.stop();
                    LOGGER.info("Done, exit.");
                } catch (Exception e) {
                    LOGGER.error("Error shutting down the server", e);
                }
            }));

            LOGGER.info("Application started.%nStop the application using CTRL+C");

            Thread.currentThread().join();

        } catch (InterruptedException e) {
            LOGGER.error("Error startig up the server", e);
        }
    }
}