/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	ChangePasswordAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	CreateClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	CreateEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindAllClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindAllEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindByLoginNameAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindClientesByNameAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	FindFacturaNotPagadasAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	GetFacturasClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	GetMantenimientosClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	GetMantenimientosEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	LoginAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	ModifyClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	ModifyEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	RemoveClienteAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions.
	RemoveEmpleadoAction;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.ClienteChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.EmpleadoChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.LoginResultVO;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.PlainActionProcessor;
import jgiraldez.j2ee.webgarden.util.sql.SimpleDataSource;

/**
 * @author jorge
 *
 */
public class PlainUsersManagementFacadeDelegate 
		implements UsersManagementFacadeDelegate {

	private DataSource dataSource;
	
	public PlainUsersManagementFacadeDelegate() throws InternalErrorException {
	
		dataSource = new SimpleDataSource();

	}
	
	/*
	 * OPERACIONES CON EL DAO DE CLIENTE
	 */
	
	public ClienteVO createCliente(ClienteVO cliente) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateClienteAction action = new CreateClienteAction(cliente);
			
			return (ClienteVO) PlainActionProcessor.process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createCliente
	
	public void removeCliente(String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			RemoveClienteAction action = new RemoveClienteAction(NIF);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
 	} // removeCliente
	
	public ClienteVO findCliente(String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindClienteAction action = new FindClienteAction(NIF);
			
			return (ClienteVO) PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findCliente
	
	public void modifyCliente(String NIF, String pila, String ap1, String ap2, 
			String email, String dir, int cod_postal, int tlf, String poblacion,
			String provincia, String dir_facturacion, boolean VIP) 
			throws InstanceNotFoundException, InternalErrorException {
	
		try {
			
			ModifyClienteAction action = new ModifyClienteAction(NIF, pila, 
					ap1, ap2, email, dir, cod_postal, tlf, poblacion, 
					provincia, dir_facturacion, VIP);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyCliente
	
	public MantenimientoChunkVO getMantenimientosCliente(String NIF, 
			int startIndex, int count) throws InternalErrorException {
		
		try {
			
			GetMantenimientosClienteAction action = 
					new GetMantenimientosClienteAction(NIF, startIndex, count);
			
			return (MantenimientoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getMantenimientosCliente
	
	public FacturaChunkVO getFacturasCliente(String NIF, int startIndex, 
			int count) throws InternalErrorException {
		
		try {
			
			GetFacturasClienteAction action = 
				new GetFacturasClienteAction(NIF, startIndex, count);
			
			return (FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getFacturasCliente
	
	public ClienteChunkVO findAllCliente(int startIndex, int count) 
			throws InternalErrorException, InstanceNotFoundException {
		
		try {
			
			FindAllClienteAction action = 
				new FindAllClienteAction(startIndex, count);
			
			return (ClienteChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllCliente
	
	public ClienteChunkVO findClientesByName(String name, 
			int startIndex, int count) 
			throws InternalErrorException, InstanceNotFoundException {
		
		try {
			
			FindClientesByNameAction action = 
				new FindClientesByNameAction(name, startIndex, count);
			
			return (ClienteChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findClientesByName
	
	public ClienteVO findByLoginName(String loginName) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindByLoginNameAction action = new FindByLoginNameAction(loginName);
			
			return (ClienteVO) PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findByLoginName
	
	public LoginResultVO login(String loginName, String password, 
			boolean passwordIsEncrypted, String loginBy)
	        throws InstanceNotFoundException, IncorrectPasswordException, 
	        InternalErrorException {
		
		try {
			
			LoginAction action = new LoginAction(loginName, password, 
					passwordIsEncrypted, loginBy);
	                
	        return (LoginResultVO) PlainActionProcessor.
	        	process(dataSource, action); 
	        
	    } catch (IncorrectPasswordException e) {
	    	throw e;
	    } catch (InstanceNotFoundException e) {
	    	throw e;
	    } catch (InternalErrorException e) {
	    	throw e;
	    } catch (Exception e) {
	    	throw new InternalErrorException(e);
	    }
	    
	}
	
	public void changePassword(String NIF, String oldClearPassword, 
			String newClearPassword) throws IncorrectPasswordException,
	        InternalErrorException {

	        try {
	        
	            ChangePasswordAction action = new ChangePasswordAction(
	            	NIF, oldClearPassword, newClearPassword);
	                
	            PlainActionProcessor.process(dataSource, action);
	            
	        } catch (IncorrectPasswordException e) {
	            throw e;
	        } catch (InstanceNotFoundException e) {
	            throw new InternalErrorException(e);
	        } catch (InternalErrorException e) {
	            throw e;
	        } catch (Exception e) {
	            throw new InternalErrorException(e);
	        }
	        
	    }
	
	/*
	 * OPERACIONES CON EL DAO DE EMPLEADO
	 */
	
	public EmpleadoVO createEmpleado(EmpleadoVO empleado) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateEmpleadoAction action = new CreateEmpleadoAction(empleado);
			
			return (EmpleadoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createEmpleado
	
	public void removeEmpleado(String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			RemoveEmpleadoAction action = new RemoveEmpleadoAction(NIF);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // removeEmpleado
	
	public EmpleadoVO findEmpleado(String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindEmpleadoAction action = new FindEmpleadoAction(NIF);
			
			return (EmpleadoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findEmpleado
	
	public void modifyEmpleado(String NIF, String pila, String ap1, String ap2, 
			String dir, int cod_postal, int tlf, String poblacion, 
			String provincia, long num_cuenta, boolean esAdmin) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			ModifyEmpleadoAction action = new ModifyEmpleadoAction(NIF, pila, 
					ap1, ap2, dir, cod_postal, tlf, poblacion, 
					provincia, num_cuenta, esAdmin);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyEmpleado
	
	public MantenimientoChunkVO getMantenimientosEmpleado(String NIF, 
			int startIndex, int count) throws InternalErrorException {
		
		try {
			
			GetMantenimientosEmpleadoAction action = 
				new GetMantenimientosEmpleadoAction(NIF, startIndex, count);
			
			return (MantenimientoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getMantenimientosEmpleado

	public EmpleadoChunkVO findAllEmpleado(int startIndex, int count) 
			throws InternalErrorException {
		
		try {
			
			FindAllEmpleadoAction action = 
				new FindAllEmpleadoAction(startIndex, count);
			
			return (EmpleadoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllEmpleado
	
	public FacturaChunkVO findFacturaNotPagadas(int startIndex, int count) 
			throws InternalErrorException {
		
		try {
			
			FindFacturaNotPagadasAction action = 
				new FindFacturaNotPagadasAction(startIndex, count);
			
			return (FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findFacturaNotPagadas
	
} // class
