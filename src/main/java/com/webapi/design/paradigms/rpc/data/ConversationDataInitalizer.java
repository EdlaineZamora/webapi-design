package com.webapi.design.paradigms.rpc.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.webapi.design.paradigms.rpc.data.ConversationData.ConversationDataBuilder.aConversationData;

@Component
public class ConversationDataInitalizer implements ApplicationRunner {

    @Autowired
	private ConversationRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(aConversationData().build());
        repository.save(aConversationData().withName("General-Channel").build());
    }

}
