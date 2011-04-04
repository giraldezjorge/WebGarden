package jgiraldez.j2ee.webgarden.model.categoria.dao;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;

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
public final class SQLCategoriaDAOFactory {
	
	private final static String DAO_CLASS_NAME_PARAMETER = 
		"SQLCategoriaDAOFactory/daoClassName";
	
	private final static Class daoClass = getDAOClass();
	
	private SQLCategoriaDAOFactory() {}
	
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
	
	public static SQLCategoriaDAO getDAO() throws InternalErrorException {
		
		try {
			return (SQLCategoriaDAO) daoClass.newInstance();
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
//		    SQLCategoriaDAO dao = SQLCategoriaDAOFactory.getDAO();
//		
//		    /* Test "SQLCategoriaDAO::create". */
//		    String nombre = "/";
//		    System.out.println("Test for 'SQLCategoriaDAO::create'");
//		    //categoriaVO1
//		    CategoriaVO categoriaVO1 = new CategoriaVO(1, nombre);
//		    categoriaVO1 = dao.create(connection, categoriaVO1);
//		    // categoriaVO2
//		    String nombre1 = "/Plantas";
//		    CategoriaVO categoriaVO2 = new CategoriaVO(2, nombre1, 1);
//		    categoriaVO2 = dao.create(connection, categoriaVO2);
//		    //categoriaVO3
//		    String nombre2 = "/Tierra";
//		    CategoriaVO categoriaVO3 = new CategoriaVO(3, nombre2, 1);
//		    categoriaVO3 = dao.create(connection, categoriaVO3);
//		    
//		    System.out.println("categoriaVO1 created => " + categoriaVO1);
//		    System.out.println("categoriaVO2 created => " + categoriaVO2);
//		    System.out.println("categoriaVO3 created => " + categoriaVO3);
//		    
//		    /* Test "SQLCategoriaDAO::exists". */
//		    System.out.println("Test for 'SQLCategoriaDAO::exists'");
//		    if (!dao.exists(connection, categoriaVO1.getIdCategoria())) {
//		        throw new Exception("Can not find categoria with idCategoria = "
//					+ categoriaVO1.getIdCategoria());
//		    }
//		    
//		    /* Test "SQLCategoriaDAO::find". */
//		    System.out.println("Test for 'SQLCategoriaDAO::find'");
//		    System.out.println(dao.find(connection, 
//					categoriaVO1.getIdCategoria()));
//		    
//		    /* Test "SQLCategoriaDAO::findByName". */
//		    System.out.println("Test for 'SQLCategoriaDAO::findByName'");
//		    System.out.println(dao.findByName(connection, "ant"));
//			      
//		    /* Test "SQLCategoriaDAO::update". */
//		    System.out.println("Test for 'SQLCategoriaDAO::update'");
//		    categoriaVO3.setPadre(2);
//		    dao.update(connection, categoriaVO3);
//		    System.out.println(dao.find(connection, 
//					categoriaVO3.getIdCategoria()));
//		        
//		    /* Test "SQLCategoriaDAO::remove". */
//		    System.out.println("Test for 'SQLCategoriaDAO::remove'");
//		    dao.remove(connection, categoriaVO3.getIdCategoria());
//		    dao.remove(connection, categoriaVO2.getIdCategoria());
//		    dao.remove(connection, categoriaVO1.getIdCategoria());
//		    
//		    if (dao.exists(connection, categoriaVO3.getIdCategoria())) {
//		        throw new Exception("SQLCategoriaDAO::remove failed");
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
