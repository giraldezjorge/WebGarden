/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.empleado.dao;

import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public interface SQLEmpleadoDAO {

	public EmpleadoVO create(Connection connection, EmpleadoVO empleado) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, String NIF) 
			throws InternalErrorException;
	public EmpleadoVO find(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, EmpleadoVO empleado) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<MantenimientoVO> getMantenimientos(Connection connection, 
			String NIF, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<EmpleadoVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException;
	
}
