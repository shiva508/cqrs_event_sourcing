package com.pool.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.pool.dto.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount extends BaseEntity {
	@Id
	private String id;

	private String accountHolder;

	private Date creationDate;

	private AccountType accountType;

	private double balance;
}
