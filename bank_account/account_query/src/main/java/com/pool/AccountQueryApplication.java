package com.pool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pool.api.queries.FindAccountByHolderQuery;
import com.pool.api.queries.FindAccountByIdQuery;
import com.pool.api.queries.FindAccountWithBalanceQuery;
import com.pool.api.queries.FindAllAccountsQuery;
import com.pool.api.queries.QueryHandler;
import com.pool.infrastructure.QueryDispatcher;

@SpringBootApplication
public class AccountQueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccountQueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}
}
