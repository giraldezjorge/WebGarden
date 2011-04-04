/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindClienteAction implements NonTransactionalPlainAction {

	private String NIF;
	
	public FindClienteAction(String NIF) {
		
		this.NIF = NIF;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		
		return clienteDAO.find(connection, NIF);
	}
}
