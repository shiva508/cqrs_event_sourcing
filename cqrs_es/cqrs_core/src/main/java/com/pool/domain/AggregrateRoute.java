package com.pool.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pool.events.BaseEvent;

public abstract class AggregrateRoute {
	protected String id;
	private int version = -1;
	private final List<BaseEvent> changes = new ArrayList<>();

	private final Logger logger = Logger.getLogger(AggregrateRoute.class.getName());

	public String getId() {
		return this.id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<BaseEvent> getUnCommittedChanges() {
		return this.changes;
	}

	public void markChangesAsCommitted() {
		this.changes.clear();
	}

	protected void applyChanges(BaseEvent event, Boolean isNewEvent) {
		try {
			var method = getClass().getDeclaredMethod("apply", event.getClass());
			method.setAccessible(true);
			method.invoke(this, event);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.log(Level.WARNING, "Something went wrong");
		} finally {
			if (isNewEvent) {
				changes.add(event);
			}
		}
	}

	public void raiseEvent(BaseEvent event) {
		this.applyChanges(event, true);
	}

	public void replayEvents(Iterable<BaseEvent> events) {
		events.forEach(event -> applyChanges(event, false));
	}
}
