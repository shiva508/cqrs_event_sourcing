package com.pool.api.controller;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.api.dto.AccountLookupResponse;
import com.pool.api.dto.EqualityType;
import com.pool.api.queries.FindAccountByHolderQuery;
import com.pool.api.queries.FindAccountByIdQuery;
import com.pool.api.queries.FindAccountWithBalanceQuery;
import com.pool.api.queries.FindAllAccountsQuery;
import com.pool.domain.BankAccount;
import com.pool.infrastructure.QueryDispatcher;

@RestController
@RequestMapping("/api/v1/bankaccountlookup")
public class AccountLookupController {
	private final Logger logger = LoggerFactory.getLogger(AccountLookupController.class);

	@Autowired
	private QueryDispatcher queryDispatcher;

	@GetMapping("/all")
	public ResponseEntity<AccountLookupResponse> getAllAccounts() {
		try {
			List<BankAccount> bankAccounts = queryDispatcher.send(new FindAllAccountsQuery());
			if (null == bankAccounts || bankAccounts.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {

				var response = AccountLookupResponse.builder().accounts(bankAccounts)
						.message(
								MessageFormat.format("Successfull request with accounts size {0}", bankAccounts.size()))
						.build();
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			var safeErrorMessage = "Failed to complete all accounts request";
			logger.error(safeErrorMessage);
			return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/byid/{id}")
	public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable("id") String id) {
		try {
			List<BankAccount> bankAccounts = queryDispatcher.send(new FindAccountByIdQuery(id));
			if (null == bankAccounts || bankAccounts.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {

				var response = AccountLookupResponse.builder().accounts(bankAccounts)
						.message(
								MessageFormat.format("Successfull request with accounts size {0}", bankAccounts.size()))
						.build();
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			var safeErrorMessage = "Failed to complete accounts request by id";
			logger.error(safeErrorMessage);
			return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/byholder/{accountHolder}")
	public ResponseEntity<AccountLookupResponse> getAccountByHolder(
			@PathVariable("accountHolder") String accountHolder) {
		try {
			List<BankAccount> bankAccounts = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
			if (null == bankAccounts || bankAccounts.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {

				var response = AccountLookupResponse.builder().accounts(bankAccounts)
						.message(
								MessageFormat.format("Successfull request with accounts size {0}", bankAccounts.size()))
						.build();
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			var safeErrorMessage = "Failed to complete accounts request by id";
			logger.error(safeErrorMessage);
			return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/withbalance/{equalityType}/{balanace}")
	public ResponseEntity<AccountLookupResponse> getWithBalance(@PathVariable("equalityType") EqualityType equalityType,
			@PathVariable("balanace") Double balanace) {
		try {
			List<BankAccount> bankAccounts = queryDispatcher.send(new FindAccountWithBalanceQuery(equalityType,balanace));
			if (null == bankAccounts || bankAccounts.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {

				var response = AccountLookupResponse.builder().accounts(bankAccounts)
						.message(
								MessageFormat.format("Successfull request with accounts size {0}", bankAccounts.size()))
						.build();
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			var safeErrorMessage = "Failed to complete accounts request by id";
			logger.error(safeErrorMessage);
			return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
