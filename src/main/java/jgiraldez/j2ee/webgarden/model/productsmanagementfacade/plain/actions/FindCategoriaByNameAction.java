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
public class FindCategoriaByNameAction implements NonTransactionalPlainAction {

	private String nombre;
	
	public FindCategoriaByNameAction(String nombre) {
		this.nombre = nombre;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLCategoriaDAO categoriaDAO = SQLCategoriaDAOFactory.getDAO();
		
		return categoriaDAO.findByName(connection, nombre);
	}
}
