package com.pool.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pool.events.BaseEvent;
import com.pool.producers.EventProducer;

@Service
public class AccountEventProducer implements EventProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void produce(String topic, BaseEvent event) {
		this.kafkaTemplate.send(topic, event);
	}

}
