package com.pool.api.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.api.commands.OpenAccountCommand;
import com.pool.api.commands.RestoreReadDbCommand;
import com.pool.api.dto.OpenAccountResponse;
import com.pool.dto.BaseResponse;
import com.pool.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/restorereaddb")
public class RestoreReadDbController {
	private final Logger logger = LoggerFactory.getLogger(RestoreReadDbController.class);

	@Autowired
	private CommandDispatcher commandDispatcher;

	@PostMapping("/newaccount")
	public ResponseEntity<BaseResponse> restoreReadDb() {
		var id = UUID.randomUUID().toString();
		try {
			logger.info("");
			commandDispatcher.send(new RestoreReadDbCommand());
			return new ResponseEntity<>(new OpenAccountResponse("Bank account creation requested succesfuly", id),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Some thing whent wrong input:{} exception:{}", "Restore", e.toString());
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
