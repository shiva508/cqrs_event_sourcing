package com.pool.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pool.api.commands.DepositFundsCommand;
import com.pool.dto.BaseResponse;
import com.pool.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/depositfunds")
public class DepositFundsController {
	private final Logger logger = LoggerFactory.getLogger(DepositFundsController.class);

	@Autowired
	private CommandDispatcher commandDispatcher;

	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse> depositFunds(@PathVariable("id") String id,
			@RequestBody DepositFundsCommand command) {
		try {
			command.setId(id);
			commandDispatcher.send(command);
			return new ResponseEntity<>(new BaseResponse("Deposit ammout requested succesfuly"), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Some thing whent wrong input:{} exception:{}", command, e.toString());
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
