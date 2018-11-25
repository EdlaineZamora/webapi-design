package com.webapi.design.paradigms.rpc.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ConversationData {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Status status;

    public ConversationData() {
    }

    public ConversationData(Integer id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public ConversationData cloneWithStatus(Status status) {
        return new ConversationData(id, name, status);
    }

    public static final class ConversationDataBuilder {

        private Integer id;
        private String name;
        private Status status;

        private ConversationDataBuilder() {
            this.name = "Team-Channel";
            this.status = Status.ACTIVE;
        }

        public static ConversationDataBuilder aConversationData() {
            return new ConversationDataBuilder();
        }

        public ConversationDataBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ConversationDataBuilder withState(Status status) {
            this.status = status;
            return this;
        }

        public ConversationData build() {
            return new ConversationData(id, name, status);
        }
    }

}
