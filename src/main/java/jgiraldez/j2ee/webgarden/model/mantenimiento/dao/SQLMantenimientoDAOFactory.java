/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento.dao;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class SQLMantenimientoDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLMantenimientoDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLMantenimientoDAOFactory() {}
	
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
	
	public static SQLMantenimientoDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLMantenimientoDAO) daoClass.newInstance();
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}
}
