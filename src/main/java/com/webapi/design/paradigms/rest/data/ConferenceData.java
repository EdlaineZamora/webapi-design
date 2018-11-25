package com.webapi.design.paradigms.rest.data;

import javax.persistence.*;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Entity
public class ConferenceData {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true
    )
    private List<TalkData> talks;

    public ConferenceData() {
    }

    public ConferenceData(Integer id, String name, List<TalkData> talks) {
        this.id = id;
        this.name = name;
        this.talks = talks;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TalkData> getTalks() {
        return talks;
    }

    public ConferenceData cloneUpdatingAllFields(ConferenceData conferenceData) {
        return new ConferenceData(id, conferenceData.getName(), conferenceData.getTalks());
    }

    public ConferenceData cloneUpdatingExistingFields(ConferenceData conferenceData) {
        ConferenceData resultantConference = new ConferenceData(id, name, talks);

        if (nonNull(conferenceData.getName())) {
            resultantConference.setName(conferenceData.getName());
        }
        if (nonNull(conferenceData.getTalks()) && !conferenceData.getTalks().isEmpty()) {
            resultantConference.getTalks().clear();
            resultantConference.getTalks().addAll(conferenceData.getTalks());
        }
        return resultantConference;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Entity
    public static class TalkData {
        @Id
        @GeneratedValue
        public Integer id;
        public String name;
    }

    public static final class ConferenceResponseBuilder {
        private Integer id;
        private String name;
        private List<TalkData> talks;

        private ConferenceResponseBuilder() {
            this.name = "She's Tech";
            this.talks = createDefaultTalks();
        }

        private List<TalkData> createDefaultTalks() {
            TalkData talk = new TalkData();
            talk.name = "Designing APIs";

            return asList(talk);
        }

        public static ConferenceResponseBuilder aConferenceResponse() {
            return new ConferenceResponseBuilder();
        }

        public ConferenceResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ConferenceResponseBuilder withTalks(List<TalkData> talks) {
            this.talks = talks;
            return this;
        }

        public ConferenceResponseBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public ConferenceData build() {
            return new ConferenceData(id, name, talks);
        }

    }

}
