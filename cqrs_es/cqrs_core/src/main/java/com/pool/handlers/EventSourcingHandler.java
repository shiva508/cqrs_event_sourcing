package com.pool.handlers;

import com.pool.domain.AggregrateRoute;

public interface EventSourcingHandler<T> {
	public void save(AggregrateRoute route);

	public T getById(String id);

	public void republishEvents();
}
