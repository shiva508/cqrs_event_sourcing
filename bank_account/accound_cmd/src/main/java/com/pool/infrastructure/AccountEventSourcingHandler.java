package com.pool.infrastructure;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pool.domain.AccountAggregate;
import com.pool.domain.AggregrateRoute;
import com.pool.handlers.EventSourcingHandler;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {
	private final EventStore eventStore;

	@Autowired
	public AccountEventSourcingHandler(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public AccountAggregate getById(String id) {
		var aggregarte = new AccountAggregate();

		var events = eventStore.getEvents(id);
		if (null != events && !events.isEmpty()) {
			aggregarte.replayEvents(events);
			var latestVersion = events.stream().map(data -> data.getVersion()).max(Comparator.naturalOrder());
			aggregarte.setVersion(latestVersion.get());
		}
		return aggregarte;
	}

	@Override
	public void save(AggregrateRoute route) {
		eventStore.saveEvent(route.getId(), route.getUnCommittedChanges(), route.getVersion());
		route.getUnCommittedChanges();
	}
}
