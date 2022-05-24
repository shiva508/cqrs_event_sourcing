package com.pool.api.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pool.domain.AccountAggregate;
import com.pool.handlers.EventSourcingHandler;

@Service
public class AccountCloseCommand implements CommandHandler {

	@Autowired
	private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

	@Override
	public void handle(OpenAccountCommand command) {
		var aggregate = new AccountAggregate(command);
		eventSourcingHandler.save(aggregate);
	}

	@Override
	public void handle(DepositFundsCommand command) {
		var aggregate = eventSourcingHandler.getById(command.getId());
		aggregate.depositFunds(command.getAmmount());
		eventSourcingHandler.save(aggregate);
	}

	@Override
	public void handle(WithdrawFundsCommand command) {
		var aggregate = eventSourcingHandler.getById(command.getId());
		if (command.getAmmount() > aggregate.getBalance()) {
			throw new IllegalStateException("No sufficient Ammount in account");
		}
		aggregate.withdrawnFunds(command.getAmmount());
		eventSourcingHandler.save(aggregate);
	}

	@Override
	public void handle(CloseCommand command) {
		var aggregate = eventSourcingHandler.getById(command.getId());
		aggregate.closeAccount();
		eventSourcingHandler.save(aggregate);
	}

}
