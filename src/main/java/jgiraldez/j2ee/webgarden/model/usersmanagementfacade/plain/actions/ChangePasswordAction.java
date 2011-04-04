/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.util.
	PasswordEncrypter;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ChangePasswordAction implements TransactionalPlainAction {

	private String NIF;
    private String oldClearPassword;
    private String newClearPassword;

    public ChangePasswordAction(String NIF, String oldClearPassword,
        String newClearPassword) {
        
        this.NIF = NIF;
        this.oldClearPassword = oldClearPassword;
        this.newClearPassword = newClearPassword;
            
    }

    public Object execute(Connection connection) 
        throws InstanceNotFoundException, IncorrectPasswordException,
        InternalErrorException {
                
        SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
        ClienteVO clienteVO = clienteDAO.find(connection, NIF);
        String storedPassword = clienteVO.getPass();
                    
        if (!PasswordEncrypter.isClearPasswordCorrect(oldClearPassword,
        		storedPassword)) {
            throw new IncorrectPasswordException(NIF);
        }

        clienteVO.setPass(PasswordEncrypter.crypt(newClearPassword));
        clienteDAO.updatePass(connection, clienteVO);

        return null;            

    }

	
}
