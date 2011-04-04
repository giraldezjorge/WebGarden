/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.SQLMantenimientoDAO;
import jgiraldez.j2ee.webgarden.model.mantenimiento.dao.
	SQLMantenimientoDAOFactory;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.NonTransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class FindMantenimientoByClienteAction 
		implements NonTransactionalPlainAction {

	private String cliente;
	private int startIndex;
	private int count;
	
	public FindMantenimientoByClienteAction(String cliente, 
			int startIndex, int count) {
		
		this.cliente = cliente;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public Object execute(Connection connection) 
			throws InternalErrorException, InstanceNotFoundException {
	
		SQLMantenimientoDAO mantenimientoDAO = 
			SQLMantenimientoDAOFactory.getDAO();
		List<MantenimientoVO> mantenimientoVOs = 
			mantenimientoDAO.findByCliente(connection, 
					cliente, startIndex, count + 1);
		boolean existMoreMantenimientos = 
			mantenimientoVOs.size() == (count + 1);
		
		if (existMoreMantenimientos) {
			mantenimientoVOs.remove(mantenimientoVOs.size() - 1);
		}
		
		return new MantenimientoChunkVO(mantenimientoVOs, 
				existMoreMantenimientos);
	}
}
