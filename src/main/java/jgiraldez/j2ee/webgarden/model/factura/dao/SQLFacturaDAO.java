/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.factura.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

/**
 * @author jorge
 *
 */
public interface SQLFacturaDAO {

	public FacturaVO create(Connection connection, FacturaVO factura) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, int idFactura) 
			throws InternalErrorException;
	public FacturaVO find(Connection connection, int idFactura) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<FacturaVO> findByClient(Connection connection, 
			String cliente, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, FacturaVO factura) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, int idFactura) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<FacturaVO> findNotPagadas(Connection connection, 
			int startIndex, int count) throws InternalErrorException;
	public List<FacturaVO> findAll(Connection connection, 
			int startIndex, int count) throws InternalErrorException;
	public List<Linea_FacturaVO> getLineas(Connection connection, 
			int idFactura, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
}
