package com.pool.commands;

import com.pool.messages.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
	public BaseCommand(String id) {
		super(id);

	}
}
