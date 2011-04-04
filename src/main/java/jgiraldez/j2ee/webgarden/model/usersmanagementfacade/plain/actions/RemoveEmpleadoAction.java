/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class RemoveEmpleadoAction implements TransactionalPlainAction {

	private String NIF;
	
	public RemoveEmpleadoAction(String NIF) {
		
		this.NIF = NIF;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		
		empleadoDAO.remove(connection, NIF);
		return null;
	}
	
}
