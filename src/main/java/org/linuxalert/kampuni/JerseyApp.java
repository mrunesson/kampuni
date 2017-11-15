package org.linuxalert.kampuni;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApp extends ResourceConfig {
  public JerseyApp() {
    packages(true, "org.linuxalert");
  }
}
