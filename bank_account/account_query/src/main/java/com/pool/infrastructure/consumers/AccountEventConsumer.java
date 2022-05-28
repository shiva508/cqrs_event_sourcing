package com.pool.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import com.pool.events.AccountClosedEvent;
import com.pool.events.AccountOpenEvent;
import com.pool.events.FundsDipositedEvent;
import com.pool.events.FundsWithdrawnEvent;
import com.pool.infrastructure.handlers.EventHandler;

@Service
public class AccountEventConsumer implements EventConsumer {

	@Autowired
	private EventHandler eventHandler;

	@KafkaListener(topics = { "AccountOpenEvent" }, groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(AccountOpenEvent event, Acknowledgment ack) {

		eventHandler.on(event);
		ack.acknowledge();
	}

	@KafkaListener(topics = { "FundsDipositedEvent" }, groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(FundsDipositedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

	@KafkaListener(topics = { "FundsWithdrawnEvent" }, groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(FundsWithdrawnEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

	@KafkaListener(topics = { "AccountClosedEvent" }, groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(AccountClosedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

}
