package com.pool.events;

import java.util.Date;

import com.pool.dto.AccountType;

public class AccountOpenEvent extends BaseEvent {
	private String accountHolder;
	private AccountType accountType;
	private Date createdDate;
	private double openingBalance;
}
