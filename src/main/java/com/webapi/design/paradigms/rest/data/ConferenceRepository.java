package com.webapi.design.paradigms.rest.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends CrudRepository<ConferenceData, Integer> {

}
