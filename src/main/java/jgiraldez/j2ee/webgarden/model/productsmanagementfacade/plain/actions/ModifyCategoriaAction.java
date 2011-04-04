/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAO;
import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAOFactory;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyCategoriaAction implements TransactionalPlainAction {

	private int idCategoria;
	private String nombre;
	private int padre;
	
	public ModifyCategoriaAction(int idCategoria, String nombre, int padre) {
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.padre = padre;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLCategoriaDAO categoriaDAO = SQLCategoriaDAOFactory.getDAO();
		
		CategoriaVO categoria = categoriaDAO.find(connection, idCategoria);
		categoria.setNombre(nombre);
		categoria.setPadre(padre);
		categoriaDAO.update(connection, categoria);
		return null;
	}
}
