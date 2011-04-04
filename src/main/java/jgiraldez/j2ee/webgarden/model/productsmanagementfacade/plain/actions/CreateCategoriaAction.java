/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAO;
import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAOFactory;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateCategoriaAction implements TransactionalPlainAction {

	private CategoriaVO categoriaVO;
	
	public CreateCategoriaAction(CategoriaVO categoriaVO) {
		
		this.categoriaVO = categoriaVO;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, DuplicateInstanceException {
	
		SQLCategoriaDAO categoriaDAO = SQLCategoriaDAOFactory.getDAO();
		
		return categoriaDAO.create(connection, categoriaVO);
	}
}
