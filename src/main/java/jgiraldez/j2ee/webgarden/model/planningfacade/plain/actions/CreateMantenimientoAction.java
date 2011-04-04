/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.SQLMantenimientoDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.
	SQLMantenimientoDAOFactory;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateMantenimientoAction implements TransactionalPlainAction {

	private MantenimientoVO mantenimientoVO;
	
	public CreateMantenimientoAction(MantenimientoVO mantenimiento) {
		
		this.mantenimientoVO = mantenimiento;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, DuplicateInstanceException {
		
		SQLMantenimientoDAO mantenimientoDAO = 
			SQLMantenimientoDAOFactory.getDAO();
		
		return mantenimientoDAO.create(connection, mantenimientoVO);
	}
}
