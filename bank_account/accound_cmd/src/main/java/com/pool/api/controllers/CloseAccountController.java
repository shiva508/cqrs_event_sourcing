package com.pool.api.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.api.commands.CloseAccountCommand;
import com.pool.api.commands.OpenAccountCommand;
import com.pool.api.dto.OpenAccountResponse;
import com.pool.domain.EventStoreRepository;
import com.pool.dto.BaseResponse;
import com.pool.events.EventModel;
import com.pool.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/closeaccount")
public class CloseAccountController {
	private final Logger logger = LoggerFactory.getLogger(CloseAccountController.class);

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private EventStoreRepository eventStoreRepository;

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponse> closeAccount(@PathVariable("id") String id) {
		try {
			logger.info("");
			commandDispatcher.send(new CloseAccountCommand(id));
			return new ResponseEntity<>(new OpenAccountResponse("Bank account clouser requested succesfuly", id),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Some thing whent wrong input:{} exception:{}", id, e.toString());
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public List<EventModel> getDta() {
		return eventStoreRepository.findAll();
	}

}
