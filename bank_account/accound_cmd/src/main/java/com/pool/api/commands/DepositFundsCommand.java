package com.pool.api.commands;

import com.pool.commands.BaseCommand;

import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
	private double ammount;
}
