package com.pool.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pool.domain.EventStoreRepository;
import com.pool.events.BaseEvent;
import com.pool.exception.ConcurrancyException;

@Service
public class AccountEventStore implements EventStore {

	private final EventStoreRepository eventStoreRepository;

	@Autowired
	public AccountEventStore(EventStoreRepository eventStoreRepository) {
		super();
		this.eventStoreRepository = eventStoreRepository;
	}

	@Override
	public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
		var eventStream = eventStoreRepository.findByaggregrateIdentifier(aggregateId);
		if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
			throw new ConcurrancyException();
		}
	}

	@Override
	public List<BaseEvent> getEvents(String aggregateId) {
		return null;
	}

}
