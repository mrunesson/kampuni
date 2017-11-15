package org.linuxalert.kampuni;


import org.linuxalert.kampuni.model.Assortment;
import org.linuxalert.kampuni.model.ImmutableAssortment;
import org.linuxalert.kampuni.model.ImmutableItem;
import org.linuxalert.kampuni.model.Item;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/")
public class Api {

  public Api() {
    System.out.println("API");
  }

  @GET
  public Response getHello() {
    Item item = ImmutableItem.builder().name("foo").build();
    Assortment assortment = ImmutableAssortment.builder().sourceCreateTime(LocalDateTime.now()).items(List.of(item)).build();
    return Response.status(Response.Status.OK).entity(assortment).build();
  }
}
