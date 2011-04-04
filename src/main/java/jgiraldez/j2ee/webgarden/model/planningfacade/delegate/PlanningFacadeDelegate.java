/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.delegate;

import java.sql.Timestamp;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public interface PlanningFacadeDelegate {

	/*
	 * OPERACIONES CON EL DAO DE MANTENIMIENTO
	 */
	public MantenimientoVO createMantenimiento(MantenimientoVO mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException;
	public void modifyMantenimiento(int idMantenimiento, String lugar, 
			String descripcion, Timestamp fecha, String dia) 
			throws InstanceNotFoundException, InternalErrorException;
	public void removeMantenimiento(int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException;
	public MantenimientoVO findMantenimiento(int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException;
	public MantenimientoChunkVO findMantenimientoByCliente(String cliente, 
			int startIndex, int count) throws InternalErrorException;
	public MantenimientoChunkVO findAllMantenimientos(int startIndex, int count) 
			throws InternalErrorException;
	/*
	 * OPERACIONES CON EL DAO DE MANTENIMIENTO_EMP
	 */
	public void assignEmpleadoToMantenimiento(
			String empleado, int mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException;
	public void deleteEmpleadoToMantenimiento(String empleado, 
			int mantenimiento) throws InternalErrorException;
	
}
