module org.linuxalert.kampuni {
  requires java.base;
  requires java.sql;
  requires undertow.core;
  requires undertow.servlet;
  requires jersey.server;
  requires jersey.common;
  requires jersey.container.servlet.core;
  requires java.ws.rs;
  requires java.xml;
  requires java.xml.bind;
  opens org.linuxalert.kampuni.model;

  requires static java.xml.ws.annotation;
  requires static org.immutables.value;
  requires com.fasterxml.jackson.databind; // Workaround to accept @Generated in generated code. https://github.com/rzwitserloot/lombok/issues/1372

  exports org.linuxalert.kampuni;
}
