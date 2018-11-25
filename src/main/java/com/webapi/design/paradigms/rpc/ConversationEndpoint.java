package com.webapi.design.paradigms.rpc;

import com.webapi.design.paradigms.rpc.data.ConversationData;
import com.webapi.design.paradigms.rpc.data.ConversationRepository;
import com.webapi.design.paradigms.rpc.data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversations")
public class ConversationEndpoint {

    @Autowired
    private ConversationRepository conversationRepository;

    @GetMapping
    public List<ConversationData> getAllConversations() {
        List<ConversationData> conversations = new ArrayList<>();

        conversationRepository.findAll()
                .iterator()
                .forEachRemaining(conversations::add);

        return conversations;
    }

    @GetMapping(value = "{conversationId}")
    public ConversationData getConversationById(@PathVariable Integer conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Has no conversation with id " + conversationId));
    }

    @PostMapping(value = "{conversationId}/archive")
    public ConversationData archiveConversation(@PathVariable Integer conversationId) {
        Optional<ConversationData> conversation = conversationRepository.findById(conversationId);

        if (conversation.isPresent()) {
            ConversationData conversationFromDatabase = conversation.get();
            return conversationRepository.save(conversationFromDatabase.cloneWithStatus(Status.ARCHIVED));
        }

        throw new RuntimeException("Has no conversation with id " + conversationId);
    }

    @PostMapping(value = "{conversationId}/close")
    public ConversationData closeConversation(@PathVariable Integer conversationId) {
        Optional<ConversationData> conversation = conversationRepository.findById(conversationId);

        if (conversation.isPresent()) {
            ConversationData conversationFromDatabase = conversation.get();
            return conversationRepository.save(conversationFromDatabase.cloneWithStatus(Status.CLOSED));
        }

        throw new RuntimeException("Has no conversation with id " + conversationId);
    }

}
