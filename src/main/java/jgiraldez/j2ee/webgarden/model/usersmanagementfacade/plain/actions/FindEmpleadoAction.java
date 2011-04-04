/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindEmpleadoAction implements NonTransactionalPlainAction {

	private String NIF;
	
	public FindEmpleadoAction(String NIF) {
		
		this.NIF = NIF;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		
		return empleadoDAO.find(connection, NIF);
	}
}
