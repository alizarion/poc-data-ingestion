package io.github.alizarion.common.services;

import io.github.alizarion.common.dao.MessageDao;
import io.github.alizarion.common.entities.Message;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * @author selim@openlinux.fr.
 */
@Named("entityFacade")
public class EntityFacade implements Serializable {

    @PersistenceContext
    EntityManager em;

    private MessageDao messageDao;


    @PostConstruct
    protected void init(){
        this.messageDao =
                new MessageDao(this.em);
    }

    @Transactional
    public void mergeMessage(final Message message){
        this.em.merge(message);
    }

}
