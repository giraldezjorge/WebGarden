/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.linea_factura.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

/**
 * @author jorge
 *
 */
public interface SQLLinea_FacturaDAO {
	
	public Linea_FacturaVO create(Connection connection, Linea_FacturaVO linea) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, int num_linea, int factura) 
			throws InternalErrorException;
	public Linea_FacturaVO find(Connection connection, 
			int num_line, int factura) 
			throws InternalErrorException, InstanceNotFoundException;
	public List<Linea_FacturaVO> findByFactura(Connection connection, 
			int factura, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, Linea_FacturaVO linea) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, int num_linea, int factura) 
			throws InstanceNotFoundException, InternalErrorException;

}
