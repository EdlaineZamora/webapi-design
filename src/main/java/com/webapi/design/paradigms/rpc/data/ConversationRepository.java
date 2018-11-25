package com.webapi.design.paradigms.rpc.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends CrudRepository<ConversationData, Integer> {
}
