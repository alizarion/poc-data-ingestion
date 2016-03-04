package io.github.alizarion.common.dao;

import io.github.alizarion.common.entities.Message;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
public class MessageDao {

    private EntityManager em;

    public MessageDao(EntityManager em) {
        this.em = em;
    }


    @SuppressWarnings("unchecked")
    public Set<Message> findAll() {
            return new HashSet<>(
                    this.em.createNamedQuery(Message.FIND_ALL_MESSAGES)
                            .getResultList());
    }
}
