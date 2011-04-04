/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	AssignEmpleadoToMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	CreateMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	DeleteEmpleadoToMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	FindAllMantenimientosAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	FindMantenimientoByClienteAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	FindMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	ModifyMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions.
	RemoveMantenimientoAction;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;

import java.sql.Timestamp;

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
public class PlainPlanningFacadeDelegate implements PlanningFacadeDelegate {

	private DataSource dataSource;
	
	public PlainPlanningFacadeDelegate() throws InternalErrorException {
	
		dataSource = new SimpleDataSource();
	}
	
	public MantenimientoVO createMantenimiento(MantenimientoVO mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateMantenimientoAction action = 
				new CreateMantenimientoAction(mantenimiento);
			
			return (MantenimientoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createMantenimiento
	
	public void modifyMantenimiento(int idMantenimiento, String lugar, 
			String descripcion, Timestamp fecha, String dia) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			ModifyMantenimientoAction action = 
				new ModifyMantenimientoAction(idMantenimiento, lugar, 
						descripcion, fecha, dia);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyMantenimiento
	
	public void removeMantenimiento(int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			RemoveMantenimientoAction action = 
				new RemoveMantenimientoAction(idMantenimiento);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // removeMantenimiento
	
	public MantenimientoVO findMantenimiento(int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindMantenimientoAction action = 
				new FindMantenimientoAction(idMantenimiento);
			
			return (MantenimientoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	}  // findMantenimiento
	
	public MantenimientoChunkVO findMantenimientoByCliente(String cliente, 
			int startIndex, int count) throws InternalErrorException {
		
		try {
			
			FindMantenimientoByClienteAction action = 
				new FindMantenimientoByClienteAction(
						cliente, startIndex, count);
			
			return (MantenimientoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findMantenimientoByCliente
	
	public void assignEmpleadoToMantenimiento(
			String empleado, int mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			AssignEmpleadoToMantenimientoAction action = 
				new AssignEmpleadoToMantenimientoAction(
						empleado, mantenimiento);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // assignEmpleadoToMantenimiento
	
	public void deleteEmpleadoToMantenimiento(
			String empleado, int mantenimiento) throws InternalErrorException {
		
		try {
			
			DeleteEmpleadoToMantenimientoAction action = 
				new DeleteEmpleadoToMantenimientoAction(
						empleado, mantenimiento);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // deleteEmpleadoToMantenimiento
	
	public MantenimientoChunkVO findAllMantenimientos(int startIndex, 
			int count) throws InternalErrorException {
		
		try {
			
			FindAllMantenimientosAction action = 
				new FindAllMantenimientosAction(startIndex, count);
			
			return (MantenimientoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllMantenimiento
	
} // class
