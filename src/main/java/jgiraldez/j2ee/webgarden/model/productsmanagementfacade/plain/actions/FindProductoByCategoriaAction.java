/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAO;
import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAOFactory;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	ProductoChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindProductoByCategoriaAction 
		implements NonTransactionalPlainAction {

	private int idCategoria;
	private int startIndex;
	private int count;
	
	public FindProductoByCategoriaAction(int idCategoria, 
			int startIndex, int count) {
		
		this.idCategoria = idCategoria;
		this.startIndex = startIndex;
		this.count = count;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
		List<ProductoVO> productoVOs = productoDAO.findByCategoria(
				connection, idCategoria, startIndex, count + 1);
		boolean existMoreProductos = productoVOs.size() == (count + 1);
		
		if (existMoreProductos) {
			productoVOs.remove(productoVOs.size() - 1);
		}
		
		return new ProductoChunkVO(productoVOs, existMoreProductos);
	}
}
