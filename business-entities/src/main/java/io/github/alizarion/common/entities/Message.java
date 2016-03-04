package io.github.alizarion.common.entities;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author selim@openlinux.fr.
 */
@Entity
@NamedQuery(name = Message.FIND_ALL_MESSAGES,
        query = "select m from Message m")
@Table(name = "message")
@XmlAccessorType(XmlAccessType.NONE)
public class Message implements Serializable {

    public static final String FIND_ALL_MESSAGES = "Message.FIND_ALL_MESSAGES";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;


    @Column(name = "task_type")
    @XmlAttribute(name = "SenderType")
    private String senderType;

    @XmlAttribute(name = "SenderName")
    @Column(name = "task_name")
    private String senderName;

    @XmlAttribute(name = "TaskKey")
    @Column(name = "task_key")
    private  String taskKey;

    @XmlAttribute(name = "TaskPriority")
    @Column(name = "task_priority")
    private Integer taskPriority;

    @Column(name = "Metadata")
    @JsonRawValue
    @JsonDeserialize(using = JRecordDeserializer.class)
    @XmlAttribute(name = "Metadata")
    public String metadata;

    @XmlAttribute(name = "ActivityKey")
    @Column(name = "activity_key")
    private String  activityKey;

    @XmlAttribute(name = "ActivityName")
    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "task_utc_creation_date")
    @XmlAttribute(name = "TaskUtcCreationDate")
    private Date taskUtcCreationDate;

    @XmlAttribute(name = "TaskUtcDueDate")
    @Column(name = "task_utc_due_date")
    private Date  taskUtcDueDate;

    @XmlAttribute(name = "UtcEventDate")
    @Column(name = "utc_event_date")
    private Date utcEventDate;

    public Message() {
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getActivityKey() {
        return activityKey;
    }

    public void setActivityKey(String activityKey) {
        this.activityKey = activityKey;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getTaskUtcCreationDate() {
        return taskUtcCreationDate;
    }

    public void setTaskUtcCreationDate(Date taskUtcCreationDate) {
        this.taskUtcCreationDate = taskUtcCreationDate;
    }

    public Date getTaskUtcDueDate() {
        return taskUtcDueDate;
    }

    public void setTaskUtcDueDate(Date taskUtcDueDate) {
        this.taskUtcDueDate = taskUtcDueDate;
    }

    public Date getUtcEventDate() {
        return utcEventDate;
    }

    public void setUtcEventDate(Date utcEventDate) {
        this.utcEventDate = utcEventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (senderType != null ? !senderType.equals(message.senderType) : message.senderType != null) return false;
        if (senderName != null ? !senderName.equals(message.senderName) : message.senderName != null) return false;
        if (taskKey != null ? !taskKey.equals(message.taskKey) : message.taskKey != null) return false;
        if (taskPriority != null ? !taskPriority.equals(message.taskPriority) : message.taskPriority != null)
            return false;
        if (metadata != null ? !metadata.equals(message.metadata) : message.metadata != null) return false;
        if (activityKey != null ? !activityKey.equals(message.activityKey) : message.activityKey != null) return false;
        if (activityName != null ? !activityName.equals(message.activityName) : message.activityName != null)
            return false;
        if (taskUtcCreationDate != null ? !taskUtcCreationDate.equals(message.taskUtcCreationDate) : message.taskUtcCreationDate != null)
            return false;
        if (taskUtcDueDate != null ? !taskUtcDueDate.equals(message.taskUtcDueDate) : message.taskUtcDueDate != null)
            return false;
        return utcEventDate != null ? utcEventDate.equals(message.utcEventDate) : message.utcEventDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (senderType != null ? senderType.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (taskKey != null ? taskKey.hashCode() : 0);
        result = 31 * result + (taskPriority != null ? taskPriority.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        result = 31 * result + (activityKey != null ? activityKey.hashCode() : 0);
        result = 31 * result + (activityName != null ? activityName.hashCode() : 0);
        result = 31 * result + (taskUtcCreationDate != null ? taskUtcCreationDate.hashCode() : 0);
        result = 31 * result + (taskUtcDueDate != null ? taskUtcDueDate.hashCode() : 0);
        result = 31 * result + (utcEventDate != null ? utcEventDate.hashCode() : 0);
        return result;
    }
}


