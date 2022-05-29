package com.pool.api.queries;

import com.pool.api.dto.EqualityType;
import com.pool.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
	private EqualityType equalityType;
	private double balance;
}
