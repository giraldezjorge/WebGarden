/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateEmpleadoAction implements TransactionalPlainAction {

	private EmpleadoVO empleadoVO;
	
	public CreateEmpleadoAction(EmpleadoVO empleado) {
		
		this.empleadoVO = empleado;
	}
	
	public Object execute(Connection connection) 
			throws DuplicateInstanceException, InternalErrorException {
		
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		
		return empleadoDAO.create(connection, empleadoVO);
	}
}
