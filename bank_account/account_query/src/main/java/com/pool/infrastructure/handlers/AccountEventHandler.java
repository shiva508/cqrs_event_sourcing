package com.pool.infrastructure.handlers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pool.domain.AccountRepository;
import com.pool.domain.BankAccount;
import com.pool.events.AccountClosedEvent;
import com.pool.events.AccountOpenEvent;
import com.pool.events.FundsDipositedEvent;
import com.pool.events.FundsWithdrawnEvent;

@Service
public class AccountEventHandler implements EventHandler {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void on(AccountOpenEvent event) {
		var bankAccount = BankAccount.builder()
												.id(event.getId())
												.accountHolder(event.getAccountHolder())
												.creationDate(event.getCreatedDate())
												.accountType(event.getAccountType())
												.balance(event.getOpeningBalance())
												.build();
		accountRepository.save(bankAccount);
	}

	@Override
	public void on(FundsDipositedEvent event) {
		Optional<BankAccount> optionalBankAccount = accountRepository.findById(event.getId());
		optionalBankAccount.ifPresentOrElse(bankAccount -> {
			Double currentBalance = bankAccount.getBalance();
			Double updatedBalance = currentBalance + event.getAmount();
			bankAccount.setBalance(updatedBalance);
			accountRepository.save(bankAccount);
		}, () -> {
			System.out.println("Something went wrong");
		});

	}

	@Override
	public void on(FundsWithdrawnEvent event) {
		Optional<BankAccount> optionalBankAccount = accountRepository.findById(event.getId());
		optionalBankAccount.ifPresentOrElse(bankAccount -> {
			Double currentBalance = bankAccount.getBalance();
			Double updatedBalance = currentBalance - event.getAmount();
			bankAccount.setBalance(updatedBalance);
			accountRepository.save(bankAccount);
		}, () -> {
			System.out.println("Something went wrong");
		});

	}

	@Override
	public void on(AccountClosedEvent event) {
		accountRepository.deleteById(event.getId());

	}

}
