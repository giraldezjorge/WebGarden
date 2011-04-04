/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class GetFacturasClienteAction implements NonTransactionalPlainAction {

	private String NIF;
	private int startIndex;
	private int count;
	
	public GetFacturasClienteAction(String NIF, int startIndex, int count) {
		
		this.NIF = NIF;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
	
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		List<FacturaVO> facturaVOs = clienteDAO.getFacturas(
				connection, NIF, startIndex, count + 1);
		boolean existMoreFacturas = facturaVOs.size() == (count + 1);
		
		if (existMoreFacturas) {
			facturaVOs.remove(facturaVOs.size() - 1);
		}
		
		return new FacturaChunkVO(facturaVOs, existMoreFacturas);
	}
}