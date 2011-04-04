/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao.
	SQLMantenimiento_EmpDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao.
	SQLMantenimiento_EmpDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class DeleteEmpleadoToMantenimientoAction 
		implements TransactionalPlainAction{

	private String empleado;
	private int mantenimiento;
	
	public DeleteEmpleadoToMantenimientoAction(String empleado, 
			int mantenimiento) {
		
		this.empleado = empleado;
		this.mantenimiento = mantenimiento;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLMantenimiento_EmpDAO mantenimiento_EmpDAO =
			SQLMantenimiento_EmpDAOFactory.getDAO();
		mantenimiento_EmpDAO.remove(connection, mantenimiento, empleado);
		
		return null;
	}
}
