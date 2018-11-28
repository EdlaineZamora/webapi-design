package com.webapi.design.paradigms.rpc;

import com.webapi.design.paradigms.rpc.data.ConversationData;
import com.webapi.design.paradigms.rpc.data.ConversationRepository;
import com.webapi.design.paradigms.rpc.data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/conversations")
public class ConversationEndpoint {

    @Autowired
    private ConversationRepository conversationRepository;

    @GetMapping(value = "/list")
    public List<ConversationData> getConversationById(@RequestParam(value = "id", required = false) Integer id) {
        if (nonNull(id)) {
            return asList(conversationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Has no conversation with id " + id)));
        }

        List<ConversationData> conversations = new ArrayList<>();

        conversationRepository.findAll()
                .iterator()
                .forEachRemaining(conversations::add);

        return conversations;
    }

    @PostMapping(value = "/list/archive")
    public ConversationData archiveConversation(@RequestParam(value = "id") Integer conversationId) {
        Optional<ConversationData> conversation = conversationRepository.findById(conversationId);

        if (conversation.isPresent()) {
            ConversationData conversationFromDatabase = conversation.get();
            return conversationRepository.save(conversationFromDatabase.cloneWithStatus(Status.ARCHIVED));
        }

        throw new RuntimeException("Has no conversation with id " + conversationId);
    }

    @PostMapping(value = "/list/close")
    public ConversationData closeConversation(@RequestParam(value = "id") Integer conversationId) {
        Optional<ConversationData> conversation = conversationRepository.findById(conversationId);

        if (conversation.isPresent()) {
            ConversationData conversationFromDatabase = conversation.get();
            return conversationRepository.save(conversationFromDatabase.cloneWithStatus(Status.CLOSED));
        }

        throw new RuntimeException("Has no conversation with id " + conversationId);
    }

}
