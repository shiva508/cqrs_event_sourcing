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
import com.pool.producers.EventProducer;

@Service
public class AccountEventStore implements EventStore {

	private final EventStoreRepository eventStoreRepository;
	private final EventProducer eventProducer;

	@Autowired
	public AccountEventStore(EventStoreRepository eventStoreRepository, EventProducer eventProducer) {
		super();
		this.eventStoreRepository = eventStoreRepository;
		this.eventProducer = eventProducer;
	}

	@Override
	public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
		var eventStream = eventStoreRepository.findByAggregrateIdentifier(aggregateId);
		if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
			throw new ConcurrancyException();
		}

		var version = expectedVersion;
		for (var event : events) {
			version++;
			event.setVersion(version);
			var eventModel = EventModel.builder().timeStamp(new Date()).aggregrateIdentifier(aggregateId)
					.aggregrateType(AccountEventStore.class.getTypeName()).version(version)
					.eventType(event.getClass().getTypeName()).eventData(event).build();

			var persistedEvent = eventStoreRepository.save(eventModel);
			if (!persistedEvent.getId().isEmpty()) {
				eventProducer.produce(event.getClass().getSimpleName(), event);
			}

		}
	}

	@Override
	public List<BaseEvent> getEvents(String aggregateId) {
		var eventStream = eventStoreRepository.findByAggregrateIdentifier(aggregateId);

		if (null == eventStream || eventStream.isEmpty()) {
			throw new ArrigateNotFoundException("Incurrect accound Id proviede");
		}
		return eventStream.stream().map(data -> data.getEventData()).collect(Collectors.toList());
	}

	@Override
	public List<String> getAggregrateIds() {
		var eventStreams = eventStoreRepository.findAll();
		if (null == eventStreams || eventStreams.isEmpty()) {
			throw new IllegalStateException("Nothing is found");
		}
		return eventStreams.stream().map(EventModel::getAggregrateIdentifier).collect(Collectors.toList());
	}

}
