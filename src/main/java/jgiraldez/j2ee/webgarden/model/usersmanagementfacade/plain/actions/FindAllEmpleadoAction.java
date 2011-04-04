/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.EmpleadoChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindAllEmpleadoAction implements NonTransactionalPlainAction {

	private int startIndex;
	private int count;
	
	public FindAllEmpleadoAction(int startIndex, int count) {
		
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException {
		
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		List<EmpleadoVO> empleadoVOs = empleadoDAO.findAll(
				connection, startIndex, count + 1);
		boolean existMoreEmpleados = empleadoVOs.size() == (count + 1);
		
		if (existMoreEmpleados) {
			empleadoVOs.remove(empleadoVOs.size() - 1);
		}
		
		return new EmpleadoChunkVO(empleadoVOs, existMoreEmpleados);
	}
}
