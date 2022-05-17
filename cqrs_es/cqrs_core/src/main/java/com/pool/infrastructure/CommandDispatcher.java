package com.pool.infrastructure;

import com.pool.commands.BaseCommand;
import com.pool.commands.CommandHandlerMethod;

public interface CommandDispatcher {
	public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

	public void send(BaseCommand command);
}
