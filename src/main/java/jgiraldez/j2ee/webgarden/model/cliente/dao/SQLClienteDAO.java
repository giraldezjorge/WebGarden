/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.cliente.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

/**
 * @author jorge
 *
 */
public interface SQLClienteDAO {

	public ClienteVO create(Connection connection, ClienteVO cliente) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, String NIF) 
			throws InternalErrorException;
	public ClienteVO find(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public ClienteVO findByLoginName(Connection connection, String loginName) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, ClienteVO cliente) 
			throws InstanceNotFoundException, InternalErrorException;
	public void updatePass(Connection connection, ClienteVO cliente) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<MantenimientoVO> getMantenimientos(Connection connection, 
			String NIF, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<FacturaVO> getFacturas(Connection connection, String NIF, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<ClienteVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException;
	public List<ClienteVO> findByName(Connection connection, String name, 
			int startIndex, int count) 
			throws InternalErrorException, InstanceNotFoundException;
	
}
