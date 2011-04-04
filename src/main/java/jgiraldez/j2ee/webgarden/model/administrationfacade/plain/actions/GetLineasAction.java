/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.
	Linea_FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAO;
import jgiraldez.j2ee.webgarden.model.factura.dao.SQLFacturaDAOFactory;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class GetLineasAction implements NonTransactionalPlainAction {

	private int idFactura;
	private int startIndex;
	private int count;
	
	public GetLineasAction(int idFactura, int startIndex, int count) {
		
		this.idFactura = idFactura;
		this.startIndex = startIndex;
		this.count = count;
	}

	public Object execute(Connection connection) 
		throws InstanceNotFoundException, InternalErrorException {
		
		SQLFacturaDAO facturaDAO = SQLFacturaDAOFactory.getDAO();
		List<Linea_FacturaVO> lineaVOs = 
			facturaDAO.getLineas(connection, idFactura, startIndex, count + 1);
		boolean existMoreLineas = lineaVOs.size() == (count + 1);
		
		if (existMoreLineas) {
			lineaVOs.remove(lineaVOs.size() - 1);
		}
		
		return new Linea_FacturaChunkVO(lineaVOs, existMoreLineas);
	}
	
}
