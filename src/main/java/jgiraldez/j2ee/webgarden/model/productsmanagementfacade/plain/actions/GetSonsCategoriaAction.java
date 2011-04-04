/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAO;
import jgiraldez.j2ee.webgarden.model.categoria.dao.SQLCategoriaDAOFactory;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	CategoriaChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class GetSonsCategoriaAction implements NonTransactionalPlainAction {

	private int padre;
	private int startIndex;
	private int count;
	
	public GetSonsCategoriaAction(int padre, int startIndex, int count) {
		
		this.padre = padre;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException {
	
		SQLCategoriaDAO categoriaDAO = SQLCategoriaDAOFactory.getDAO();
		List<CategoriaVO> categoriaVOs = categoriaDAO.getSons(
				connection, padre, startIndex, count + 1);
		boolean existMoreCategorias = categoriaVOs.size() == (count + 1);
		
		if (existMoreCategorias) {
			categoriaVOs.remove(categoriaVOs.size() - 1);
		}
		
		return new CategoriaChunkVO(categoriaVOs, existMoreCategorias);
	}
}
