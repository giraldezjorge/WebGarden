/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

/**
 * @author jorge
 *
 */
public interface SQLMantenimientoDAO {

	public MantenimientoVO create(Connection connection, 
			MantenimientoVO mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, int idMantenimiento) 
			throws InternalErrorException;
	public MantenimientoVO find(Connection connection, int idMantenimiento) 
			throws InternalErrorException, InstanceNotFoundException;
	public List<MantenimientoVO> findByCliente(Connection connection, 
			String cliente, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, MantenimientoVO mantenimiento) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<MantenimientoVO> findAll(Connection connection, 
			int startIndex, int count) throws InternalErrorException;
}
