package com.pool.api.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pool.api.dto.EqualityType;
import com.pool.domain.AccountRepository;
import com.pool.domain.BankAccount;
import com.pool.domain.BaseEntity;

@Service
public class AccountQueryHandler implements QueryHandler {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<BaseEntity> handle(FindAllAccountsQuery query) {
		List<BankAccount> bankAccounts = accountRepository.findAll();
		List<BaseEntity> baseEntities = bankAccounts.stream().collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		return baseEntities;
	}

	@Override
	public List<BaseEntity> handle(FindAccountByHolderQuery query) {
		var bankAccounts = accountRepository.findByAccountHolder(query.getAccountHolder());
		if (!bankAccounts.isPresent()) {
			return null;
		}
		List<BaseEntity> baseEntities = new ArrayList<>();
		baseEntities.add(bankAccounts.get());
		return baseEntities;
	}

	@Override
	public List<BaseEntity> handle(FindAccountByIdQuery query) {
		var bankAccounts = accountRepository.findById(query.getId());

		if (!bankAccounts.isPresent()) {
			return null;
		}
		List<BaseEntity> baseEntities = new ArrayList<>();
		baseEntities.add(bankAccounts.get());
		return baseEntities;
	}

	@Override
	public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
		List<BaseEntity> baseEntities = query.getEqualityType() == EqualityType.GREATER_THAN
				? accountRepository.findByBalanceGreaterThan(query.getBalance())
				: accountRepository.findByBalanceLessThan(query.getBalance());
		return baseEntities;
	}

}
