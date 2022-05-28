package com.pool.infrastructure.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import com.pool.events.AccountClosedEvent;
import com.pool.events.AccountOpenEvent;
import com.pool.events.FundsDipositedEvent;
import com.pool.events.FundsWithdrawnEvent;

public interface EventConsumer {
	public void consume(@Payload AccountOpenEvent event, Acknowledgment ack);

	public void consume(@Payload FundsDipositedEvent event, Acknowledgment ack);

	public void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack);

	public void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
