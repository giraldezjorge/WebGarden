/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAOFactory;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateFacturaAction implements TransactionalPlainAction {

	private FacturaVO facturaVO;
	
	public CreateFacturaAction(FacturaVO factura) {
		
		this.facturaVO = factura;
	}
	
	public Object execute(Connection connection) 
		throws InternalErrorException, DuplicateInstanceException {
		
		SQLFacturaDAO facturaDAO = SQLFacturaDAOFactory.getDAO();
		
		return facturaDAO.create(connection, facturaVO);
	}
}
