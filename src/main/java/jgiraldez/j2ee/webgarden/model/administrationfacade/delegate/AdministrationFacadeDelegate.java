/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.delegate;

import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.
	Linea_FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.sql.Timestamp;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public interface AdministrationFacadeDelegate {

	/*
	 * OPERACIONES CON EL DAO DE FACTURA
	 */
	public FacturaVO createFactura(FacturaVO factura) 
			throws DuplicateInstanceException, InternalErrorException;
	public void modifyFactura(int idFactura, Timestamp fecha, 
			boolean pagada, String cliente) 
			throws InstanceNotFoundException, InternalErrorException;
	public FacturaChunkVO getNotPagadas(int startIndex, int count) 
			throws InternalErrorException;
	public FacturaChunkVO findByCliente(String cliente, int startIndex, 
			int count) throws InstanceNotFoundException, InternalErrorException;
	public void removeFactura(int idFactura) 
			throws InstanceNotFoundException, InternalErrorException;
	public FacturaVO findFactura(int idFactura) 
			throws InstanceNotFoundException, InternalErrorException;
	public FacturaChunkVO findAllFacturas(int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public Linea_FacturaChunkVO getLineas(int idFactura, int startIndex, 
			int count)throws InstanceNotFoundException, InternalErrorException;
	/*
	 * OPERACIONES CON EL DAO DE LINEA_FACTURA
	 */	
	public Linea_FacturaVO createLinea_Factura(Linea_FacturaVO linea_Factura) 
			throws DuplicateInstanceException, InternalErrorException;
	public void deleteLinea_Factura(int linea, int factura) 
			throws InstanceNotFoundException, InternalErrorException;
}
