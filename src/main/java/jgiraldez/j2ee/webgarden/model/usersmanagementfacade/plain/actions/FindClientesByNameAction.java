/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.ClienteChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindClientesByNameAction implements NonTransactionalPlainAction {

	private String name;
	private int startIndex;
	private int count;
	
	public FindClientesByNameAction(String name, int startIndex, int count) {
		
		this.name = name;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
	
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		List<ClienteVO> clienteVOs = clienteDAO.findByName(
				connection, name, startIndex, count + 1);
		boolean existMoreClientes = clienteVOs.size() == (count + 1);
		
		if (existMoreClientes) {
			clienteVOs.remove(clienteVOs.size() - 1);
		}
		
		return new ClienteChunkVO(clienteVOs, existMoreClientes);
	}
}
