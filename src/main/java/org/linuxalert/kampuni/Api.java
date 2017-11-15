package org.linuxalert.kampuni;


import org.linuxalert.kampuni.model.Assortment;
import org.linuxalert.kampuni.model.Item;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class Api {

  @GET
  public Response getHello() {
    Item item = new Item();
    item.setName("foo");
    item.setSubName("bar");
    Assortment assortment = new Assortment();
    assortment.setItems(List.of(item));
    return Response.status(Response.Status.OK).entity(assortment).build();
  }
}
