package com.pool.infrastructure;

import java.util.List;

import com.pool.domain.BaseEntity;
import com.pool.queries.BaseQuery;
import com.pool.queries.QueryHandlerMethod;

public interface QueryDispatcher {
	public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

	public <U extends BaseEntity> List<U> send(BaseQuery query);
}
