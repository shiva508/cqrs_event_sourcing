package com.pool.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.pool.domain.BaseEntity;
import com.pool.queries.BaseQuery;
import com.pool.queries.QueryHandlerMethod;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

	private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

	@Override
	public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
		var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
		handlers.add(handler);

	}

	@Override
	public <U extends BaseEntity> List<U> send(BaseQuery query) {
		var handlers = routes.get(query.getClass());
		if (handlers == null || handlers.size() == 0) {
			throw new RuntimeException("No Query handler was registerd");
		}

		if (handlers.size() > 1) {
			throw new RuntimeException("Can not send Query to more that one handler");
		}
		return handlers.get(0).handle(query);
	}

}
