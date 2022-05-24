package com.pool.producers;

import com.pool.events.BaseEvent;

public interface EventProducer {
	public void produce(String topic, BaseEvent event);
}
