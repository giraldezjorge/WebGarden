/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAO;
import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindCategoriaAction implements NonTransactionalPlainAction {

	private int idCategoria;
	
	public FindCategoriaAction(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLCategoriaDAO categoriaDAO = SQLCategoriaDAOFactory.getDAO();
		
		return categoriaDAO.find(connection, idCategoria);
	}
}
