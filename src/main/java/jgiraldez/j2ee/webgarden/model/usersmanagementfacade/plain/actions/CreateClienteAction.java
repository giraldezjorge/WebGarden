/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class CreateClienteAction implements TransactionalPlainAction {

	private ClienteVO clienteVO;
	
	public CreateClienteAction(ClienteVO cliente) {
		
		this.clienteVO = cliente;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, DuplicateInstanceException {
		
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		
		return clienteDAO.create(connection, clienteVO);
	}
}
