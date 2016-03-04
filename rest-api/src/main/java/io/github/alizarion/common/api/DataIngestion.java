package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.Message;
import io.github.alizarion.common.services.EntityFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author selim@openlinux.fr.
 */
@Path("/messages")
public class DataIngestion {

    @Inject
    @Named("entityFacade")
    EntityFacade facade;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Message message){
        this.facade.mergeMessage(message);
        return Response.status(Response.Status.OK).build();
    }


}
