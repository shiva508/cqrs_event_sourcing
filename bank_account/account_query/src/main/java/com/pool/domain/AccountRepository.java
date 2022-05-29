package com.pool.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, String> {
	public Optional<BankAccount> findByAccountHolder(String accountHolder);

	public List<BaseEntity> findByBalanceGreaterThan(double balance);

	public List<BaseEntity> findByBalanceLessThan(double balance);
}
