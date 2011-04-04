/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAO;
import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class RemoveProductoAction implements TransactionalPlainAction {

	private int idProducto;
	
	public RemoveProductoAction(int idProducto) {
		
		this.idProducto = idProducto;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
		
		productoDAO.remove(connection, idProducto);
		return null;
	}
}
