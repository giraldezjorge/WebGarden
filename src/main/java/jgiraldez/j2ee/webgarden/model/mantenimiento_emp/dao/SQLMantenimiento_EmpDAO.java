/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.vo.Mantenimiento_EmpVO;

/**
 * @author jorge
 *
 */
public interface SQLMantenimiento_EmpDAO {

	public Mantenimiento_EmpVO create(Connection connection, 
			Mantenimiento_EmpVO mantenimiento_emp) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, 
			int mantenimiento, String empleado) throws InternalErrorException;
	public Mantenimiento_EmpVO find(Connection connection, 
			int mantenimiento, String empleado) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<Mantenimiento_EmpVO> findByEmpleado(Connection connection, 
			String empleado, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, 
			Mantenimiento_EmpVO mantenimiento_emp) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, 
			int mantenimiento, String empleado) 
			throws InstanceNotFoundException, InternalErrorException;
	
}
