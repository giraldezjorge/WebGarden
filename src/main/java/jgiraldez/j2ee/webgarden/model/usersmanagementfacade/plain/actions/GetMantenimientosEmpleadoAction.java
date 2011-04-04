/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;
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
public class GetMantenimientosEmpleadoAction 
		implements NonTransactionalPlainAction {

	private String NIF;
	private int startIndex;
	private int count;
	
	public GetMantenimientosEmpleadoAction(String NIF, int startIndex, 
			int count) {
		
		this.NIF = NIF;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
	
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		List<MantenimientoVO> mantenimientoVOs = empleadoDAO.getMantenimientos(
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
