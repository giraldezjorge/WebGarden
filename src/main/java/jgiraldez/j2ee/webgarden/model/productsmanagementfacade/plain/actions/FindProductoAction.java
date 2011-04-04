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
public class FindProductoAction implements NonTransactionalPlainAction {

	private int idProducto;
	
	public FindProductoAction(int idProducto) {
		
		this.idProducto = idProducto;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
		
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
				
		return productoDAO.find(connection, idProducto);
	}
}
