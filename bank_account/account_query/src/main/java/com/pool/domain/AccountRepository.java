package com.pool.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, String> {
	public Optional<BankAccount> findByAccountHolder(String accountHolder);

	public Optional<BaseEntity> findByBalanceGreaterThan(String accountHolder);

	public Optional<BaseEntity> findByBalanceLessThan(String accountHolder);
}
