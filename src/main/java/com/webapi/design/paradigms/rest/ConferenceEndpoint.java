package com.webapi.design.paradigms.rest;

import com.webapi.design.paradigms.rest.data.ConferenceData;
import com.webapi.design.paradigms.rest.data.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conferences")
public class ConferenceEndpoint {

    @Autowired
    private final ConferenceRepository conferenceRepository;

    public ConferenceEndpoint(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @GetMapping
    public List<ConferenceData> getAllConferences() {
        List<ConferenceData> conferences = new ArrayList<>();
        conferenceRepository.findAll().iterator()
                .forEachRemaining(conferences::add);
        return conferences;
    }

    @GetMapping(value = "{conferenceId}")
    public ConferenceData getConferenceById(@PathVariable Integer conferenceId) {
        return conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Has no conference with id " + conferenceId));
    }

    @PostMapping
    public ConferenceData createConferenceWithTalks(@RequestBody ConferenceData conferenceData) {
        return conferenceRepository.save(conferenceData);
    }

    @PutMapping(value = "{conferenceId}")
    public ConferenceData refreshAllConferenceFieldsById(@PathVariable Integer conferenceId, @RequestBody ConferenceData conferenceData) {
        ConferenceData conferenceToUpdate = conferenceData;

        Optional<ConferenceData> conferenceFromDatabase = conferenceRepository.findById(conferenceId);
        if (conferenceFromDatabase.isPresent()) {
            conferenceToUpdate = conferenceFromDatabase.get().cloneUpdatingAllFields(conferenceData);
        }
        return conferenceRepository.save(conferenceToUpdate);
    }

    @PatchMapping(value = "{conferenceId}")
    public ConferenceData refreshExistingConferenceFieldsById(@PathVariable Integer conferenceId, @RequestBody ConferenceData conferenceData) {
        ConferenceData conferenceToUpdate = conferenceData;

        Optional<ConferenceData> conferenceFromDatabase = conferenceRepository.findById(conferenceId);
        if (conferenceFromDatabase.isPresent()) {
            conferenceToUpdate = conferenceFromDatabase.get().cloneUpdatingExistingFields(conferenceData);
        }
        return conferenceRepository.save(conferenceToUpdate);
    }

    @DeleteMapping(value = "{conferenceId}")
    public void deleteConferenceById(@PathVariable Integer conferenceId) {
        conferenceRepository.deleteById(conferenceId);
    }

}
