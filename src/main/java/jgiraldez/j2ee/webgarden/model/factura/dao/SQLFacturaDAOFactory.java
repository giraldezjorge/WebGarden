/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.factura.dao;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;

import java.sql.Connection;
import java.sql.Timestamp;
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
public final class SQLFacturaDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLFacturaDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLFacturaDAOFactory() {}
	
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
	
	public static SQLFacturaDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLFacturaDAO) daoClass.newInstance();
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
//		    SQLFacturaDAO dao = SQLFacturaDAOFactory.getDAO();
//		    SQLClienteDAO dao2 = SQLClienteDAOFactory.getDAO();
//		    
//		    //clienteVO1
//		    String loginName = "jgiraldez";
//		    String NIF = "77007401C";
//		    String pass = "635566043";
//		    String pila = "Jorge";
//		    String ap1 = "Giraldez";
//			String ap2 = "Gonzalez";
//			String email = "dsfaf@hotmail.com";
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
//		    clienteVO1 = dao2.create(connection, clienteVO1);
//		
//		    //clienteVO2
//		    String loginName2 = "nmartinez";
//		    String NIF2 = "77004549C";
//		    String pass2 = "609936820";
//		    String pila2 = "Natalia";
//		    String ap12 = "Martinez";
//			String ap22 = "Arias";
//			String email2 = "erty@gmail.com";
//			String dir2 = "Glorieta Colon 2 3ºD";
//			int cod_postal2 = 36300;
//			int tlf2 = 986358159;
//			String poblacion2 = "Baiona";
//			String provincia2 = "Pontevedra";
//			String dir_facturacion2 = "Avenida Finisterre 45";
//			boolean VIP2 = false;
//			
//			ClienteVO clienteVO2 = new ClienteVO(loginName2, NIF2, pass2, 
//					pila2,  ap12, ap22, email2, dir2, cod_postal2, tlf2, 
//					poblacion2, provincia2, dir_facturacion2, VIP2);
//		    clienteVO2 = dao2.create(connection, clienteVO2);
//		   
//		    
//		    System.out.println("clienteVO1 created => " + clienteVO1);
//		    System.out.println("clienteVO2 created => " + clienteVO2);
//		    
//		    /* Test "SQLFacturaDAO::create". */
//		    System.out.println("Test for 'SQLFacturaDAO::create'");
//		    //facturaVO1
//		    int idFactura = 123;
//		    Timestamp fecha = Timestamp.valueOf("2007-01-01 15:30:45");
//		    String cliente = clienteVO1.getNIF();
//		    boolean pagada = false;
//			
//			FacturaVO facturaVO1 = new FacturaVO(idFactura, fecha, 
//					pagada, cliente);
//		    facturaVO1 = dao.create(connection, facturaVO1);
//			 
//		    System.out.println("facturaVO1 created => " + facturaVO1);
//		    
//		    /* Test "SQLFacturaDAO::exists". */
//		    System.out.println("Test for 'SQLFacturaDAO::exists'");
//		    if (!dao.exists(connection, facturaVO1.getIdFactura())) {
//		        throw new Exception("Can not find cliente with NIF = " +
//		        		facturaVO1.getIdFactura());
//		    }
//		    
//		    /* Test "SQLFacturaDAO::find". */
//		    System.out.println("Test for 'SQLFacturaDAO::find'");
//		    FacturaVO aux = dao.find(connection, facturaVO1.getIdFactura());
//		    System.out.println("Factura -> " + aux);
//		    System.out.println("NIF cliente facturaVO1 -> " + aux.getCliente());
//		    
//		    /* Test "SQLFacturaDAO::update". */
//		    System.out.println("Test for 'SQLFacturaDAO::update'");
//		    facturaVO1.setCliente(clienteVO2.getNIF());
//		    facturaVO1.setPagada(true);
//		    dao.update(connection, facturaVO1);
//		    FacturaVO aux2 = dao.find(connection, facturaVO1.getIdFactura());
//		    System.out.println("Factura -> " + aux2);
//		    System.out.println("NIF cliente facturaVO2 -> " +
//		    		aux2.getCliente());
//		        
//		    /* Test "SQLFacturaDAO::remove". */
//		    System.out.println("Test for 'SQLFacturaDAO::remove'");
//		    dao.remove(connection, facturaVO1.getIdFactura());
//		    dao2.remove(connection, clienteVO1.getNIF());
//		    dao2.remove(connection, clienteVO2.getNIF());
//		    
//		    if (dao.exists(connection, facturaVO1.getIdFactura())) {
//		        throw new Exception("SQLFacturaDAO::remove failed");
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
