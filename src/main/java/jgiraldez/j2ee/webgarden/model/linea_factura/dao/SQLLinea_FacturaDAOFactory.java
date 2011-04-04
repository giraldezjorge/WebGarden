/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.linea_factura.dao;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class SQLLinea_FacturaDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLLinea_FacturaDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLLinea_FacturaDAOFactory() {}
	
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
	
	public static SQLLinea_FacturaDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLLinea_FacturaDAO) daoClass.newInstance();
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}
}
