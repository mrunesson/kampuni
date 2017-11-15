package org.linuxalert.kampuni;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;

import static io.undertow.servlet.Servlets.servlet;

public class Main {

  private static Undertow server;

  public static void main(String[] args) throws Exception {
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
            .addMapping("/kampuni/*"));

    DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
    manager.deploy();

    server = Undertow
        .builder()
        .addHttpListener(port, "0.0.0.0")
        .setHandler(manager.start())
        .build();

    server.start();
  }
}