/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.linea_factura.dao.SQLLinea_FacturaDAO;
import jgiraldez.j2ee.webgarden.model.linea_factura.dao.
	SQLLinea_FacturaDAOFactory;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateLinea_FacturaAction implements TransactionalPlainAction {

	private Linea_FacturaVO linea_FacturaVO;
	
	public CreateLinea_FacturaAction(Linea_FacturaVO linea_Factura) {
		
		this.linea_FacturaVO= linea_Factura;
	}
	
	public Object execute(Connection connection) 
		throws DuplicateInstanceException, InternalErrorException {
		
		SQLLinea_FacturaDAO linea_FacturaDAO = 
			SQLLinea_FacturaDAOFactory.getDAO();
		
		return linea_FacturaDAO.create(connection, linea_FacturaVO);
	}
}
