/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAO;
import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindProductoByNameAction implements NonTransactionalPlainAction {

	private String name;
	
	public FindProductoByNameAction(String nombre) {
		
		this.name = nombre;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
		
		return productoDAO.findByName(connection, name);
	}
	
	
}
