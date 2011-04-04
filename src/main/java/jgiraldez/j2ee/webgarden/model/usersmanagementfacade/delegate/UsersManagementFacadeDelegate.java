/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate;

import java.io.Serializable;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.ClienteChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.EmpleadoChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.LoginResultVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public interface UsersManagementFacadeDelegate extends Serializable {

	/*
	 * OPERACIONES CON EL DAO DE CLIENTE
	 */
	public ClienteVO createCliente(ClienteVO cliente) 
			throws DuplicateInstanceException, InternalErrorException;
	public void removeCliente(String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public ClienteVO findCliente(String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public void modifyCliente(String NIF, String pila, String ap1, String ap2, 
			String email, String dir, int cod_postal, int tlf, String poblacion,
			String provincia, String dir_facturacion, boolean VIP) 
			throws InstanceNotFoundException, InternalErrorException;
	public MantenimientoChunkVO getMantenimientosCliente(String NIF, 
			int startIndex, int count) throws InternalErrorException;
	public FacturaChunkVO getFacturasCliente(String NIF, int startIndex, 
			int count) throws InternalErrorException;
	public ClienteChunkVO findAllCliente(int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public ClienteChunkVO findClientesByName(String name, int startIndex, 
			int count) throws InstanceNotFoundException, InternalErrorException;
	public ClienteVO findByLoginName(String loginName) 
			throws InstanceNotFoundException, InternalErrorException;
	public LoginResultVO login(String loginName, String password, 
			boolean passwordIsEncrypted, String loginBy) 
			throws InstanceNotFoundException, IncorrectPasswordException, 
			InternalErrorException;
	public void changePassword(String NIF, String oldClearPassword, 
			String newClearPassword) throws IncorrectPasswordException,
	        InternalErrorException;
	/*
	 * OPERACIONES CON EL DAO DE EMPLEADO
	 */
	public EmpleadoVO createEmpleado(EmpleadoVO empleado) 
			throws DuplicateInstanceException, InternalErrorException;
	public void removeEmpleado(String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public EmpleadoVO findEmpleado(String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public void modifyEmpleado(String NIF, String pila, String ap1, String ap2, 
			String dir, int cod_postal, int tlf, String poblacion, 
			String provincia, long num_cuenta, boolean esAdmin) 
			throws InstanceNotFoundException, InternalErrorException;
	public MantenimientoChunkVO getMantenimientosEmpleado(String NIF, 
			int startIndex, int count) throws InternalErrorException;
	public EmpleadoChunkVO findAllEmpleado(int startIndex, int count) 
			throws InternalErrorException;
	/*
	 * OPERACIONES CON EL DAO DE FACTURA
	 */
	public FacturaChunkVO findFacturaNotPagadas(int startIndex, int count) 
			throws InternalErrorException;
}
