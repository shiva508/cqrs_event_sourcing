package com.pool.infrastructure;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pool.domain.EventStoreRepository;
import com.pool.events.BaseEvent;
import com.pool.events.EventModel;
import com.pool.exception.ArrigateNotFoundException;
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

		var version = expectedVersion;
		for (var event : events) {
			version++;
			event.setVersion(version);
			var eventModel = EventModel.builder().timeStamp(new Date()).aggregrateIdentifier(aggregateId)
					.aggregrateType(AccountEventStore.class.getTypeName()).version(version)
					.eventType(event.getClass().getTypeName()).build();

			var persistedEvent = eventStoreRepository.save(eventModel);
			if (null != persistedEvent) {
				// KAFKA IMPLEMENTATION
			}

		}
	}

	@Override
	public List<BaseEvent> getEvents(String aggregateId) {
		var eventStream = eventStoreRepository.findByaggregrateIdentifier(aggregateId);

		if (null == eventStream || eventStream.isEmpty()) {
			throw new ArrigateNotFoundException("Incurrect accound Id proviede");
		}
		return eventStream.stream().map(data -> data.getEventData()).collect(Collectors.toList());
	}

}
