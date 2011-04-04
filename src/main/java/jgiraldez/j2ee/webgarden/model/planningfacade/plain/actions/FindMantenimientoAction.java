package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.SQLMantenimientoDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.
	SQLMantenimientoDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

public class FindMantenimientoAction implements NonTransactionalPlainAction {

	private int idMantenimiento;
	
	public FindMantenimientoAction(int idMantenimiento) {
		
		this.idMantenimiento = idMantenimiento;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLMantenimientoDAO mantenimientoDAO = 
			SQLMantenimientoDAOFactory.getDAO();
		
		return mantenimientoDAO.find(connection, idMantenimiento);
	}
	
}
