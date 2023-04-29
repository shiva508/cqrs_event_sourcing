package com.pool.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pool.events.EventModel;

@Repository
public interface EventStoreRepository extends JpaRepository<EventModel, String> {
	List<EventModel> findByAggregrateIdentifier(String aggregrateIdentifier);
}
