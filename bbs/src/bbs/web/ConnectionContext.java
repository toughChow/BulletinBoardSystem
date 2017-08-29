package bbs.web;

import java.sql.Connection;

public class ConnectionContext {
	
	private ConnectionContext(){}
	
	private static ConnectionContext instance = new ConnectionContext();

	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

	public static ConnectionContext getInstance() {
		return instance;
	}
	
	public void bind(Connection connection) {
		connectionThreadLocal.set(connection);
	}

	public Connection get() {
		return connectionThreadLocal.get();
	}

	public void remove() {
		connectionThreadLocal.remove();
	}
}
