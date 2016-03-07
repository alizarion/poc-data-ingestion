package io.github.alizarion.common.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
/**
 * @author selim@openlinux.fr.
 */
/**
 * @author sbn
 */
@MessageDriven(name = "MessageQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jboss/exported/jms/queue/MessageQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "maxSession",
                propertyValue = "1"),
        @ActivationConfigProperty(propertyName = "DLQMaxResent",
                propertyValue = "300")
})
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageQueue implements MessageListener {

    @Inject
    EntityFacade  entityFacade;

    @Override
    public void onMessage(Message message) {

        if (message instanceof ObjectMessage){
            try {
                if (((ObjectMessage) message)
                        .getObject()
                        instanceof io.github.alizarion.common.entities.Message){
                    io.github.alizarion.common.entities.Message messageDTO  =
                            (io.github.alizarion.common.entities.Message)(
                                    (ObjectMessage) message).getObject();
                    entityFacade.mergeMessage(messageDTO);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}


