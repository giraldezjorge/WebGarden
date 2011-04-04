/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAO;
import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAOFactory;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateProductoAction implements TransactionalPlainAction {

	private ProductoVO productoVO;
	
	public CreateProductoAction(ProductoVO producto) {
		
		this.productoVO = producto;
	}

	public Object execute(Connection connection) 
			throws InternalErrorException, DuplicateInstanceException {
		
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
				
		return productoDAO.create(connection, productoVO);
	}
}
