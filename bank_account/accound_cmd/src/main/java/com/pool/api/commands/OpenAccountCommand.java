package com.pool.api.commands;

import com.pool.commands.BaseCommand;
import com.pool.dto.AccountType;

import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
	private String accountHolder;
	private AccountType accountType;
	private double openingBalance;
}
