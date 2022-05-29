package com.pool.api.queries;

import java.util.List;

import com.pool.domain.BaseEntity;

public interface QueryHandler {
	public List<BaseEntity> handle(FindAllAccountsQuery query);

	public List<BaseEntity> handle(FindAccountByHolderQuery query);

	public List<BaseEntity> handle(FindAccountByIdQuery query);

	public List<BaseEntity> handle(FindAccountWithBalanceQuery query);

}
