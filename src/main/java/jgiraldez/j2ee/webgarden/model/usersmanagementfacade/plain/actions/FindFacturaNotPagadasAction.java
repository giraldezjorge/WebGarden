/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAOFactory;
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
public class FindFacturaNotPagadasAction 
		implements NonTransactionalPlainAction {

	private int startIndex;
	private int count;
	
	public FindFacturaNotPagadasAction(int startIndex, int count) {
		
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
	
		SQLFacturaDAO facturaDAO = SQLFacturaDAOFactory.getDAO();
		List<FacturaVO> facturaVOs = facturaDAO.findNotPagadas(
				connection, startIndex, count + 1);
		boolean existMoreFacturas = facturaVOs.size() == (count + 1);
		
		if (existMoreFacturas) {
			facturaVOs.remove(facturaVOs.size() - 1);
		}
		
		return new FacturaChunkVO(facturaVOs, existMoreFacturas);
	}
}
