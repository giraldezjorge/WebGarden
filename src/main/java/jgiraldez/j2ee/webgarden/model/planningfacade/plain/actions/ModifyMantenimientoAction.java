/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.SQLMantenimientoDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.
	SQLMantenimientoDAOFactory;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

import java.sql.Connection;
import java.sql.Timestamp;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyMantenimientoAction implements TransactionalPlainAction {

	private int idMantenimiento;
	private String lugar;
	private String descripcion;
	private Timestamp fecha;
	private String dia;
	
	public ModifyMantenimientoAction(int idMantenimiento, String lugar, 
			String descripcion, Timestamp fecha, String dia) {
		
		this.idMantenimiento = idMantenimiento;
		this.lugar = lugar;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.dia = dia;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLMantenimientoDAO mantenimientoDAO = 
			SQLMantenimientoDAOFactory.getDAO();
		
		MantenimientoVO mantenimiento = 
			mantenimientoDAO.find(connection, idMantenimiento);
		mantenimiento.setLugar(lugar);
		mantenimiento.setFecha(fecha);
		mantenimiento.setDescripcion(descripcion);
		mantenimiento.setDia(dia);
		
		mantenimientoDAO.update(connection, mantenimiento);
		return null;
	}
}
