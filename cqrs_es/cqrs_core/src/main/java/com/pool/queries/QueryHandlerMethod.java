package com.pool.queries;

import java.util.List;

import com.pool.domain.BaseEntity;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {

	public List<BaseEntity> handle(T query);
}
