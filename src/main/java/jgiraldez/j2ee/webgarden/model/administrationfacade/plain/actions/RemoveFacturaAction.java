/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class RemoveFacturaAction implements TransactionalPlainAction {

	private int idFactura;
	
	public RemoveFacturaAction(int idFactura) {
		
		this.idFactura = idFactura;
	}
	
	public Object execute(Connection connection) 
		throws InstanceNotFoundException, InternalErrorException {
		
		SQLFacturaDAO facturaDAO = SQLFacturaDAOFactory.getDAO();
		
		facturaDAO.remove(connection, idFactura);
		return null;
	}
}
