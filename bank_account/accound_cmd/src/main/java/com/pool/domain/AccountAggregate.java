package com.pool.domain;

import java.util.Date;

import com.pool.api.commands.OpenAccountCommand;
import com.pool.events.AccountOpenEvent;
import com.pool.events.FundsDipositedEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAggregate extends AggregrateRoute {
	private Boolean active;
	private double balance;

	public AccountAggregate(OpenAccountCommand command) {
		raiseEvent(AccountOpenEvent.builder().id(command.getId()).accountHolder(command.getAccountHolder())
				.createdDate(new Date()).accountType(command.getAccountType())
				.openingBalance(command.getOpeningBalance()).build());
	}

	public void apply(AccountOpenEvent event) {
		this.id = event.getId();
		this.active = true;
		this.balance = event.getOpeningBalance();
	}

	public void depositFunds(double amount) {
		if (!this.active) {
			throw new IllegalStateException("Funds can not be deposited to closed acount");
		}
		if (amount <= 0) {
			throw new IllegalStateException("Deposit ammount must be greater than 0!");
		}
		raiseEvent(FundsDipositedEvent.builder().id(this.id).amount(amount).build());
	}

	public void apply(FundsDipositedEvent event) {
		this.id = event.getId();
		this.balance += event.getAmount();
	}
}
