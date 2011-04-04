/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao.
	SQLMantenimiento_EmpDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao.
	SQLMantenimiento_EmpDAOFactory;
import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.vo.Mantenimiento_EmpVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class AssignEmpleadoToMantenimientoAction 
		implements TransactionalPlainAction {

	private String empleado;
	private int mantenimiento;
	
	public AssignEmpleadoToMantenimientoAction(String empleado, 
			int mantenimiento) {
		
		this.empleado = empleado;
		this.mantenimiento = mantenimiento;
	}

	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException, 
			DuplicateInstanceException {
		
		SQLMantenimiento_EmpDAO mantenimiento_EmpDAO = 
			SQLMantenimiento_EmpDAOFactory.getDAO();
		Mantenimiento_EmpVO mantenimiento_emp = 
			new Mantenimiento_EmpVO(mantenimiento, empleado);
		mantenimiento_EmpDAO.create(connection, mantenimiento_emp);
		
		return null;
	}
	
	
}
