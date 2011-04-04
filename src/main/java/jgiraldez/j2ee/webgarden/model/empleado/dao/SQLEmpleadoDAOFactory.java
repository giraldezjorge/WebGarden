/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.empleado.dao;

import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;

import java.sql.Connection;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;
import jgiraldez.j2ee.webgarden.util.sql.SimpleDataSource;

/**
 * @author jorge
 *
 */
public final class SQLEmpleadoDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLEmpleadoDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLEmpleadoDAOFactory() {}
	
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
	
	public static SQLEmpleadoDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLEmpleadoDAO) daoClass.newInstance();
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}
	
//	public static void main (String[] args) {    
//
//		Connection connection = null;
//	
//		try {        
//		  
//			/* Get a connection. */
//		    DataSource dataSource = new SimpleDataSource();
//		    connection = dataSource.getConnection();        
//		              
//		    /* Get dao. */
//		    SQLEmpleadoDAO dao = SQLEmpleadoDAOFactory.getDAO();
//		
//		    /* Test "SQLEmpleadoDAO::create". */
//		    System.out.println("Test for 'SQLEmpleadoDAO::create'");
//		    //empleadoVO1
//		    String NIF = "11111111C";
//		    String pass = "hola";
//		    String pila = "Manuel";
//		    String ap1 = "Perez";
//			String ap2 = "Gomez";
//			String dir = "Santa Liberata 7 1ºC";
//			int cod_postal = 36300;
//			int tlf = 986357199;
//			String poblacion = "Baiona";
//			String provincia = "Pontevedra";
//			int num_cuenta = 12345;
//			boolean esAdmin = false;
//			
//			EmpleadoVO empleadoVO1 = new EmpleadoVO(NIF,pass, pila, ap1, ap2, 
//					dir, cod_postal, tlf, poblacion, provincia, 
//					num_cuenta, esAdmin);
//		    empleadoVO1 = dao.create(connection, empleadoVO1);
//			
//		    //empleadoVO2
//		    String NIF2 = "22222222C";
//		    String pass2 = "hola";
//		    String pila2 = "Patricia";
//		    String ap12 = "Martinez";
//			String ap22 = "Arias";
//			String dir2 = "Glorieta Colon 2 3ºD";
//			int cod_postal2 = 36300;
//			int tlf2 = 986358159;
//			String poblacion2 = "Baiona";
//			String provincia2 = "Pontevedra";
//			int num_cuenta2 = 12346;
//			boolean esAdmin2 = false;
//			
//			EmpleadoVO empleadoVO2 = new EmpleadoVO(NIF2,pass2, pila2, ap12, 
//					ap22, dir2, cod_postal2, tlf2, poblacion2, provincia2, 
//					num_cuenta2, esAdmin2);
//		    empleadoVO2 = dao.create(connection, empleadoVO2);
//		   
//		    
//		    System.out.println("empleadoVO1 created => " + empleadoVO1);
//		    System.out.println("empleadoVO2 created => " + empleadoVO2);
//		    
//		    /* Test "SQLEmpleadoDAO::exists". */
//		    System.out.println("Test for 'SQLEmpleadoDAO::exists'");
//		    if (!dao.exists(connection, empleadoVO1.getNIF())) {
//		        throw new Exception("Can not find empleado with NIF = " +
//		        		empleadoVO1.getNIF());
//		    }
//		    
//		    /* Test "SQLEmpleadoDAO::find". */
//		    System.out.println("Test for 'SQLEmpleadoDAO::find'");
//		    System.out.println(dao.find(connection, empleadoVO1.getNIF()));
//		    
//		    /* Test "SQLEmpleadoDAO::update". */
//		    System.out.println("Test for 'SQLEmpleadoDAO::update'");
//		    empleadoVO2.setPoblacion("Pontevedra");
//		    dao.update(connection, empleadoVO2);
//		    System.out.println(dao.find(connection, empleadoVO2.getNIF()));
//		        
//		    /* Test "SQLEmpleadoDAO::remove". */
//		    System.out.println("Test for 'SQLEmpleadoDAO::remove'");
//		    dao.remove(connection, empleadoVO1.getNIF());
//		    dao.remove(connection, empleadoVO2.getNIF());
//		    
//		    if (dao.exists(connection, empleadoVO1.getNIF())) {
//		        throw new Exception("SQLEmpleadoDAO::remove failed");
//		    }
//		    
//		    if (dao.exists(connection, empleadoVO2.getNIF())) {
//		        throw new Exception("SQLEmpleadoDAO::remove failed");
//		    }
//		        
//		    /* Tests OK. */
//		    System.out.println("Tests OK !!!!");
//		    
//		} catch (Exception e) {
//		    e.printStackTrace();
//		} finally {
//		    try {
//		        GeneralOperations.closeConnection(connection);
//		    } catch (InternalErrorException e) {
//		        e.printStackTrace();
//		    }
//		}
//	} // main
}
