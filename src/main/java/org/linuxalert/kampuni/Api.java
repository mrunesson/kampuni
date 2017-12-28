package org.linuxalert.kampuni;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class Api {

  private static final AssortmentService ASSORTMENT_SERVICE = AssortmentService.getInstance();

  @GET
  public Response getHello() {
    return Response.status(Response.Status.OK).entity(ASSORTMENT_SERVICE.getAll()).build();
  }

  @GET
  @Path("{id}")
  public Response getHello(@PathParam("id") String id) {
    return Response.status(Response.Status.OK).entity(ASSORTMENT_SERVICE.getId(id)).build();
  }
}
