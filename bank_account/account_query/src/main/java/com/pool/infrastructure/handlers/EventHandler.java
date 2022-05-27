package com.pool.infrastructure.handlers;

import com.pool.events.AccountClosedEvent;
import com.pool.events.AccountOpenEvent;
import com.pool.events.FundsDipositedEvent;
import com.pool.events.FundsWithdrawnEvent;

public interface EventHandler {
	public void on(AccountOpenEvent event);

	public void on(FundsDipositedEvent event);

	public void on(FundsWithdrawnEvent event);

	public void on(AccountClosedEvent event);
}
