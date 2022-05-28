package com.pool.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pool.events.EventModel;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
	List<EventModel> findByAggregrateIdentifier(String aggregrateIdentifier);
}
