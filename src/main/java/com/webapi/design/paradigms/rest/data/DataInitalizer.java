package com.webapi.design.paradigms.rest.data;

import com.webapi.design.paradigms.rest.data.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.webapi.design.paradigms.rest.data.ConferenceData.ConferenceResponseBuilder.aConferenceResponse;

@Component
public class DataInitalizer implements ApplicationRunner {

    @Autowired
	private ConferenceRepository conferenceRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        conferenceRepository.save(aConferenceResponse().withName("She's Tech").build());
        conferenceRepository.save(aConferenceResponse().withName("O'Reilly").build());
    }

}
