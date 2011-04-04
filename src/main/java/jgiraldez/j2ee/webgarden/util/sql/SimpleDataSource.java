package jgiraldez.j2ee.webgarden.util.sql;



import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;


/**
 * @author jorge
 *
 */
public class SimpleDataSource implements DataSource {
	
	private static final String DRIVER_CLASS_NAME_PARAMETER = 
		"SimpleDataSource/driverClassName";
	private static final String URL_PARAMETER = "SimpleDataSource/url";
	private static final String USER_PARAMETER = "SimpleDataSource/user";
	private static final String PASSWORD_PARAMETER = 
		"SimpleDataSource/password";
	
	private static String url;
	private static String user;
	private static String password;
	
	static {
		
		try {
			/* read configuration parameters */
			String driverClassName = ConfigurationParametersManager.
				getParameter(DRIVER_CLASS_NAME_PARAMETER);
			url = ConfigurationParametersManager.getParameter(URL_PARAMETER);
			user = ConfigurationParametersManager.getParameter(USER_PARAMETER);
			password = ConfigurationParametersManager.
				getParameter(PASSWORD_PARAMETER);
			
			/* load driver*/
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // static
	
	public Connection getConnection() throws SQLException {
		
//		System.out.println(url + user + password);
//		System.out.println(driverClassName);
		return DriverManager.getConnection(url, user, password);
	}

	public Connection getConnection(String username, String password) 
			throws SQLException {
		
		throw new UnsupportedOperationException("Not implemented");
	}

	public PrintWriter getLogWriter() throws SQLException {
		throw new UnsupportedOperationException("Not implemented");
	}

	public int getLoginTimeout() throws SQLException {
		throw new UnsupportedOperationException("Not implemented");
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		throw new UnsupportedOperationException("Not implemented");	
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		throw new UnsupportedOperationException("Not implemented");		
	}
	
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}
	
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

}
