package com.pool.infrastructure;

import java.util.List;

import com.pool.events.BaseEvent;

public interface EventStore {
	public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

	List<BaseEvent> getEvents(String aggregateId);
	
	public List<String> getAggregrateIds();
}
