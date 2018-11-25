package com.webapi.design.paradigms.rest;

import com.webapi.design.paradigms.rest.data.ConferenceData.TalkData;
import com.webapi.design.paradigms.rest.data.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TalkEndpoint {

    @Autowired
    private final ConferenceRepository conferenceRepository;

    public TalkEndpoint(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @GetMapping(value = "/conferences/{conferenceId}/talks")
    public List<TalkData> getAllTalksByConferenceId(@PathVariable Integer conferenceId) {
        return conferenceRepository.findById(conferenceId).get().getTalks();
    }

}
