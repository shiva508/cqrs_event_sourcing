package com.pool.api.dto;

import java.util.List;
import com.pool.domain.BankAccount;
import com.pool.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {
	private List<BankAccount> accounts;
	
	public AccountLookupResponse(String message) {
		super(message);
	}
}
