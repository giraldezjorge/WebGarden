/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAOFactory;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;

import java.sql.Connection;
import java.sql.Timestamp;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyFacturaAction implements TransactionalPlainAction {

	private int idFactura;
	private Timestamp fecha;
	private boolean pagada;
	private String cliente;
	
	public ModifyFacturaAction(int idFactura, Timestamp fecha, 
			boolean pagada, String cliente) {
		
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.pagada = pagada;
		this.cliente = cliente;
	}
	
	public Object execute(Connection connection) 
		throws InstanceNotFoundException, InternalErrorException {
		
		SQLFacturaDAO facturaDAO = SQLFacturaDAOFactory.getDAO();
		
		FacturaVO factura = facturaDAO.find(connection, idFactura);
		factura.setFecha(fecha);
		factura.setPagada(pagada);
		factura.setCliente(cliente);
		
		facturaDAO.update(connection, factura);
		return null;
	}
}
