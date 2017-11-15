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

  exports org.linuxalert.kampuni;
}
