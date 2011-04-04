/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.sql;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public class DataSourceLocator {

	private static final String JNDI_PREFIX = "java:comp/env/jdbc/";

    private static Map dataSources = Collections.synchronizedMap(new HashMap());
    
    private DataSourceLocator() {}
    
    public static void addDataSource(String name, DataSource dataSource) {
    
    	dataSources.put(name, dataSource);
    }
    
    public static DataSource getDataSource(String name) 
    		throws InternalErrorException{
    
	    DataSource dataSource = (DataSource) dataSources.get(name);
	    
	    if (dataSource == null) {
	
	        try {
	        	
	        	InitialContext initialContext = new InitialContext();
	            dataSource = (DataSource) initialContext.lookup(
	            		JNDI_PREFIX + name);
	            dataSources.put(name, dataSource);
	            
	        } catch (Exception e) {
	            throw new InternalErrorException(e);
	        }
	    
	    }
	    
	    return dataSource;
	}
}
