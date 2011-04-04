/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.linea_factura.dao.SQLLinea_FacturaDAO;
import jgiraldez.j2ee.webgarden.model.linea_factura.dao.
	SQLLinea_FacturaDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class DeleteLinea_FacturaAction implements TransactionalPlainAction {

	private int num_linea;
	private int factura;
	
	public DeleteLinea_FacturaAction(int num_linea, int factura) {
		
		this.num_linea = num_linea;
		this.factura = factura;
	}
	
	public Object execute(Connection connection) 
		throws InstanceNotFoundException, InternalErrorException {
		
		SQLLinea_FacturaDAO linea_FacturaDAO = 
			SQLLinea_FacturaDAOFactory.getDAO();
		
		linea_FacturaDAO.remove(connection, num_linea, factura);
		return null;
	}
}
