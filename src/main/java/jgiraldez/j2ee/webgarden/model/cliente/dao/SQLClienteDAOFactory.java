/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.cliente.dao;

import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;

import java.sql.Connection;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.configuration.ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;
import jgiraldez.j2ee.webgarden.util.sql.SimpleDataSource;

/**
 * @author jorge
 *
 */
public final class SQLClienteDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLClienteDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLClienteDAOFactory() {}
	
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
	
	public static SQLClienteDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLClienteDAO) daoClass.newInstance();
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
//		    SQLClienteDAO dao = SQLClienteDAOFactory.getDAO();
//		
//		    /* Test "SQLClienteDAO::create". */
//		    System.out.println("Test for 'SQLClienteDAO::create'");
//		    //clienteVO1
//			String loginName = "jgiraldez";
//		    String NIF = "77007401C";
//		    String pass = "635566043";
//		    String pila = "Jorge";
//		    String ap1 = "Giraldez";
//			String ap2 = "Gonzalez";
//			String email = "sdfg@gmail.com";
//			String dir = "Santa Liberata 7 1ºC";
//			int cod_postal = 36300;
//			int tlf = 986357199;
//			String poblacion = "Baiona";
//			String provincia = "Pontevedra";
//			String dir_facturacion = "Avenida Finisterre 27";
//			boolean VIP = false;
//			
//			ClienteVO clienteVO1 = new ClienteVO(loginName, NIF, pass, pila, 
//					ap1, ap2, email, dir, cod_postal, tlf, poblacion, 
//					provincia, dir_facturacion, VIP);
//		    clienteVO1 = dao.create(connection, clienteVO1);
//			
//		    //clienteVO2
//			String loginName2 = "nmartinez";
//		    String NIF2 = "77004549C";
//		    String pass2 = "609936820";
//		    String pila2 = "Natalia";
//		    String ap12 = "Martinez";
//			String ap22 = "Arias";
//			String email2 = "sdfgherg@hotmail.com";
//			String dir2 = "Glorieta Colon 2 3ºD";
//			int cod_postal2 = 36300;
//			int tlf2 = 986358159;
//			String poblacion2 = "Baiona";
//			String provincia2 = "Pontevedra";
//			String dir_facturacion2 = "Avenida Finisterre 45";
//			boolean VIP2 = true;
//			
//			ClienteVO clienteVO2 = new ClienteVO(loginName2, NIF2, pass2, 
//					pila2, ap12, ap22, email2, dir2, cod_postal2, tlf2, 
//					poblacion2, provincia2, dir_facturacion2, VIP2);
//		    clienteVO2 = dao.create(connection, clienteVO2);
//		   
//		    
//		    System.out.println("clienteVO1 created => " + clienteVO1);
//		    System.out.println("clienteVO2 created => " + clienteVO2);
//		    
//		    /* Test "SQLClienteDAO::exists". */
//		    System.out.println("Test for 'SQLClienteDAO::exists'");
//		    if (!dao.exists(connection, clienteVO1.getNIF())) {
//		        throw new Exception("Can not find cliente with NIF = " +
//		        		clienteVO1.getNIF());
//		    }
//		    
//		    /* Test "SQLClienteDAO::find". */
//		    System.out.println("Test for 'SQLClienteDAO::find'");
//		    System.out.println(dao.find(connection, clienteVO1.getNIF()));
//    		
//			/* Test "SQLClienteDAO::findByName". */
//    		System.out.println("Test for 'SQLClienteDAO::findByName'");
//    		System.out.println(dao.find(connection, clienteVO1.getPila()));
//		    
//		    /* Test "SQLClienteDAO::update". */
//		    System.out.println("Test for 'SQLClienteDAO::update'");
//		    clienteVO2.setPoblacion("Pontevedra");
//		    dao.update(connection, clienteVO2);
//		    System.out.println(dao.find(connection, clienteVO2.getNIF()));
//		        
//		    /* Test "SQLClienteDAO::remove". */
//		    System.out.println("Test for 'SQLClienteDAO::remove'");
//		    dao.remove(connection, clienteVO1.getNIF());
//		    dao.remove(connection, clienteVO2.getNIF());
//		    
//		    if (dao.exists(connection, clienteVO1.getNIF())) {
//		        throw new Exception("SQLClienteDAO::remove failed");
//		    }
//		    
//		    if (dao.exists(connection, clienteVO2.getNIF())) {
//		        throw new Exception("SQLClienteDAO::remove failed");
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
