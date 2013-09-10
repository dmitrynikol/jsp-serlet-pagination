package com.dmitrynikol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The factory used to create an instance of Connection.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class ConnectionFactory {
	String url = "jdbc:mysql://localhost/store";
	String user = "root";
	String password = "root";
	String driverClass = "com.mysql.jdbc.Driver";
	
	private static ConnectionFactory instance = null;

	private ConnectionFactory() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the singleton instance of the factory.
	 * 
	 * @return the singleton instance
	 */
	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}

	/**
	 * Creates a new Connection.
	 * 
	 * @return the new Connection
	 * @throws SQLException if an error occurs during creating a connection
	 * @throws ClassNotFoundException if an error occurs during creating a connection
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}