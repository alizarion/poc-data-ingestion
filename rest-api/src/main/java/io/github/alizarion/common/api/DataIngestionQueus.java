package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.Message;
import io.github.alizarion.common.services.EntityFacade;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author selim@openlinux.fr.
 */
@JMSDestinationDefinitions(

        value =  {
                @JMSDestinationDefinition(
                        name = "java:/jboss/exported/jms/queue/MessageQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "MessageQueue"
                )
        }
)
@Path("/messages/queue")
public class DataIngestionQueus {

    @Inject
    @Named("entityFacade")
    EntityFacade facade;

    @Resource(lookup = "java:/jboss/exported/jms/queue/MessageQueue")
     private Queue hoover;
     @Inject
     private JMSContext context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Message message){
        context.createProducer().send(hoover, context.createObjectMessage(message));

        return Response.status(Response.Status.OK).build();
    }


}
