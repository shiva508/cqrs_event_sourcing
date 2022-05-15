package com.pool.commands;

public interface CommandDispatcher {
	public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

	public void send(BaseCommand command);
}
