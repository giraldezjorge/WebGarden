/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class SQLMantenimiento_EmpDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLMantenimiento_EmpDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLMantenimiento_EmpDAOFactory() {}
	
	private static Class getDAOClass() {
		Class theClass = null;
		
		try {
			String daoClassName = ConfigurationParametersManager.
				getParameter(DAO_CLASS_NAME_PARAMETER);
			theClass = Class.forName(daoClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theClass;
	}
	
	public static SQLMantenimiento_EmpDAO getDAO() 
			throws InternalErrorException {
		
		try {
			return (SQLMantenimiento_EmpDAO) daoClass.newInstance();
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}
}
