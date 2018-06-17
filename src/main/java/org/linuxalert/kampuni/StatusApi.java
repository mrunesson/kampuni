package org.linuxalert.kampuni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("status")
public class StatusApi {

  private static final AssortmentService ASSORTMENT_SERVICE = AssortmentService.getInstance();

  @GET
  @Path("health")
  public Response getHealth() {
    if (ASSORTMENT_SERVICE.health()) {
      return Response.status(Response.Status.OK).entity("OK").build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("I have old data.").build();
    }
  }

  @GET
  @Path("ready")
  public Response getReady() {
    if (ASSORTMENT_SERVICE.ready()) {
      return Response.status(Response.Status.OK).entity("OK").build();
    } else {
      return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("I am not ready yet.").build();
    }
  }
}
