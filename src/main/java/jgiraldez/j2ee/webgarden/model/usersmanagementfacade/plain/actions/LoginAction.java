/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.util.
	PasswordEncrypter;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.LoginResultVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class LoginAction implements NonTransactionalPlainAction {

	private String loginName;
    private String password;
    private boolean passwordIsEncrypted;
    private String loginBy;

    public LoginAction(String loginName, String password, 
    		boolean passwordIsEncrypted, String loginBy) {    
        
        this.loginName = loginName;
        this.password = password;
        this.passwordIsEncrypted = passwordIsEncrypted;
        this.loginBy = loginBy;
    }
    
    /**
     *
     * @return a <code>LoginResultVO</code>
     */
    public Object execute(Connection connection) 
			throws IncorrectPasswordException, InstanceNotFoundException, 
            InternalErrorException {
    	
    	String storedPassword;
    	String pila;
    	String NIF;
    	String type;
    	
    	if (loginBy.equals("CLI_ID")) {
    		
	    	SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
	    	ClienteVO clienteVO = 
	    		clienteDAO.findByLoginName(connection, loginName);
	    	storedPassword = clienteVO.getPass();
	    	pila = clienteVO.getPila();
	    	NIF = clienteVO.getNIF();
	    	type = "client";
	    	
    	} else {
    		
    		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
    		EmpleadoVO empleadoVO = empleadoDAO.find(connection, loginName);
    		storedPassword = empleadoVO.getPass();
    		NIF = empleadoVO.getNIF();
    		pila = empleadoVO.getPila();
    		
    		if (!empleadoVO.getEsAdmin()) {
    			type = "employee";
    		} else {
    			type = "admin";
    		}
    	}
    	
    	if (!type.equals("admin")) {
	        if (passwordIsEncrypted) {
	            if (!password.equals(storedPassword)) {
	                throw new IncorrectPasswordException(loginName);
	            }
	        } else {
	            if (!PasswordEncrypter.isClearPasswordCorrect(password, 
	            		storedPassword)) {
	                throw new IncorrectPasswordException(loginName);
	            }
	        }
    	}
    	
        return new LoginResultVO(pila, storedPassword, NIF, type);

    }
}
