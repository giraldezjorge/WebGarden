/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.producto.dao;

import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAO;
import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAOFactory;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

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
public final class SQLProductoDAOFactory {

	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLProductoDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLProductoDAOFactory() {}
	
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
	
	public static SQLProductoDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLProductoDAO) daoClass.newInstance();
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
//		    SQLProductoDAO dao2 = SQLProductoDAOFactory.getDAO();
//		    SQLCategoriaDAO dao = SQLCategoriaDAOFactory.getDAO();
//		    
//		    //categoriaVO1
//		    String name = "/";
//		    CategoriaVO categoriaVO1 = new CategoriaVO(1, name, 1);
//		    categoriaVO1 = dao.create(connection, categoriaVO1);
//		    //categoriaVO2
//		    String nombre2 = "/Tierra";
//		    CategoriaVO categoriaVO2 = new CategoriaVO(3, nombre2, 1);
//		    categoriaVO2 = dao.create(connection, categoriaVO2);
//		    
//		    /* Test "SQLProductoDAO::create". */
//		    int idProducto = 123;
//		    String nombre = "Abono";
//		    String descripcion = "Ramón, o Fertimón";
//		    double precio = 50;
//		    int categoria = 1;
//		    
//		    System.out.println("Test for 'SQLProductoDAO::create'");
//		    //productoVO1
//		    ProductoVO productoVO1 = new ProductoVO(idProducto, nombre, 
//		    		descripcion,precio, categoria);
//		    productoVO1 = dao2.create(connection, productoVO1); 
//		    
//		    System.out.println("categoriaVO1 created => " + categoriaVO1);
//		    System.out.println("categoriaVO2 created => " + categoriaVO2);
//		    
//		    System.out.println("prodcutoVO1 created => " + productoVO1);
//    
//		    /* Test "SQLProductoDAO::exists". */
//		    System.out.println("Test for 'SQLProductoDAO::exists'");
//		    if (!dao2.exists(connection, productoVO1.getIdProducto())) {
//		        throw new Exception("Can not find producto with idProducto = " 
//		        		+ productoVO1.getIdProducto());
//		    }
//		    
//		    /* Test "SQLProductoDAO::find". */
//		    System.out.println("Test for 'SQLProductoDAO::find'");
//		    System.out.println(dao2.find(
//		    		connection, productoVO1.getIdProducto()));
//		    
//		    /* Test "SQLProductoDAO::findByName". */
//		    System.out.println("Test for 'SQLProductoDAO::findByName'");
//		    System.out.println(dao2.findByName(
//		    		connection, productoVO1.getNombre()));
//		          
//		    /* Test "SQLProductoDAO::update". */
//		    System.out.println("Test for 'SQLProductoDAO::update'");
//		    productoVO1.setDescripcion("hola mundo");
//		    dao2.update(connection, productoVO1);
//		    System.out.println(dao2.find(
//		    		connection, productoVO1.getIdProducto()));
//        
//		    /* Test "SQLProductoDAO::remove". */
//		    System.out.println("Test for 'SQLProductoDAO::remove'");
//		    dao2.remove(connection, productoVO1.getIdProducto());
//		    dao.remove(connection, categoriaVO2.getIdCategoria());
//		    dao.remove(connection, categoriaVO1.getIdCategoria());
//		    
//		    if (dao2.exists(connection, productoVO1.getIdProducto())) {
//		        throw new Exception("SQLProductoDAO::remove failed");
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
//	  }
//          
//	} // main  
	
}
