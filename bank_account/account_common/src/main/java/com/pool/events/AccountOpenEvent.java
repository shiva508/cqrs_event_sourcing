package com.pool.events;

import java.util.Date;

import com.pool.dto.AccountType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AccountOpenEvent extends BaseEvent {
	private String accountHolder;
	private AccountType accountType;
	private Date createdDate;
	private double openingBalance;
}
