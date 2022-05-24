package com.pool.api.commands;

public interface CommandHandler {
	public void handle(OpenAccountCommand command);

	public void handle(DepositFundsCommand command);

	public void handle(WithdrawFundsCommand command);

	public void handle(CloseCommand command);
}
