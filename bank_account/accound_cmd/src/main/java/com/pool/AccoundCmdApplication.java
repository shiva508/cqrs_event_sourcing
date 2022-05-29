package com.pool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pool.api.commands.CloseAccountCommand;
import com.pool.api.commands.CommandHandler;
import com.pool.api.commands.DepositFundsCommand;
import com.pool.api.commands.OpenAccountCommand;
import com.pool.api.commands.WithdrawFundsCommand;
import com.pool.infrastructure.CommandDispatcher;

@SpringBootApplication
public class AccoundCmdApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccoundCmdApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}
}
