package org.linuxalert.kampuni;

import io.prometheus.client.Counter;
import org.linuxalert.kampuni.model.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/v1/")
public class Api {

  private static final AssortmentService ASSORTMENT_SERVICE = AssortmentService.getInstance();
  static final Counter requests = Counter.build().name("requests_total").help("Total requests.").register();

  @GET
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public Response getAll() {
    requests.inc();
    return Response.status(Response.Status.OK).entity(ASSORTMENT_SERVICE.getAll()).build();
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public Response getId(@PathParam("id") String id) {
    requests.inc();
    if (Objects.isNull(id) || id.isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    Result result = ASSORTMENT_SERVICE.getId(id);
    return Response.status(Response.Status.OK).entity(result).build();
  }
}
