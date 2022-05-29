package com.pool.api.queries;

import com.pool.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
	private String accountHolder;
}
