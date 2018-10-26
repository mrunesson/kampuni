package org.linuxalert.kampuni;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;

import static io.undertow.servlet.Servlets.servlet;

public class Main {

  private static final System.Logger LOGGER = System.getLogger(Main.class.getName());
  private static Undertow server;

  public static void main(String[] args) throws Exception {
    LOGGER.log(System.Logger.Level.DEBUG, "Starting");
    AssortmentService.getInstance(); // Ensure cache get populated.
    startContainer(8080);
  }

  public static void startContainer(int port) throws Exception {
    DeploymentInfo servletBuilder = Servlets.deployment()
        .setClassLoader(Main.class.getClassLoader())
        .setContextPath("")
        .setDeploymentName("app.war")
        .addServlets(servlet("jerseyServlet", ServletContainer.class)
            .setLoadOnStartup(1)
            .addInitParam("javax.ws.rs.Application", JerseyApp.class.getName())
            .addInitParam("jersey.config.server.provider.packages", "com.jersey.jaxb")
            .addMapping("/kampuni/*"))
        .addServlets(servlet("metricsServlet", MetricsServlet.class)
            .setLoadOnStartup(1)
            .addMapping("/kampuni/status/metrics"));
    DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
    manager.deploy();

    DefaultExports.initialize();

    server = Undertow
        .builder()
        .addHttpListener(port, "0.0.0.0")
        .setHandler(manager.start())
        .build();

    server.start();
  }
}
