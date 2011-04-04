/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class GetMantenimientosClienteAction 
		implements NonTransactionalPlainAction {

	private String NIF;
	private int startIndex;
	private int count;
	
	public GetMantenimientosClienteAction(String NIF, int startIndex, 
			int count) {
		
		this.NIF = NIF;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
	
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		List<MantenimientoVO> mantenimientoVOs = clienteDAO.getMantenimientos(
				connection, NIF, startIndex, count + 1);
		boolean existMoreMantenimientos = mantenimientoVOs.size() == 
					(count + 1);
		
		if (existMoreMantenimientos) {
			mantenimientoVOs.remove(mantenimientoVOs.size() - 1);
		}
		
		return new MantenimientoChunkVO(mantenimientoVOs, 
				existMoreMantenimientos);
	}
}
