/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.plain;

import java.sql.Timestamp;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.PlainActionProcessor;
import jgiraldez.j2ee.webgarden.util.sql.SimpleDataSource;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	CreateFacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	CreateLinea_FacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	DeleteLinea_FacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	FindAllFacturasAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	FindByClienteAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	FindFacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	GetLineasAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	GetNotPagadasAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	ModifyFacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.plain.actions.
	RemoveFacturaAction;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.
	Linea_FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

/**
 * @author jorge
 *
 */
public class PlainAdministrationFacadeDelegate 
	implements AdministrationFacadeDelegate {

	private DataSource dataSource;
	
	public PlainAdministrationFacadeDelegate() throws InternalErrorException {
	
		dataSource = new SimpleDataSource();
	}
	
	public FacturaVO createFactura(FacturaVO factura) 
				throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateFacturaAction action = new CreateFacturaAction(factura);
			
			return (FacturaVO) PlainActionProcessor.process(dataSource, action);
			
		} catch (InternalErrorException e) {
			
			throw e;
			
		} catch (Exception e) {
			
			throw new InternalErrorException(e);
			
		}
	} // createFactura
	
	public void modifyFactura(int idFactura, Timestamp fecha, 
			boolean pagada, String cliente) 
			throws InstanceNotFoundException, InternalErrorException {
	
		try {
			
			ModifyFacturaAction action = 
				new ModifyFacturaAction(idFactura, fecha, pagada, cliente);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyFactura
	
	public void removeFactura(int idFactura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			RemoveFacturaAction action = new RemoveFacturaAction(idFactura);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // removeFactura
	
	public FacturaChunkVO getNotPagadas(int startIndex, int count) 
			throws InternalErrorException {
		
		try {
			
			GetNotPagadasAction action = 
				new GetNotPagadasAction(startIndex, count);
			
			return (FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getNotPagadas
	
	public FacturaChunkVO findByCliente(String cliente, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindByClienteAction action = 
				new FindByClienteAction(cliente, startIndex, count);
			
			return (FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findByCliente
	
	public FacturaVO findFactura(int idFactura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindFacturaAction action = new FindFacturaAction(idFactura);
			
			return (FacturaVO) PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findFactura
	
	public FacturaChunkVO findAllFacturas(int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindAllFacturasAction action = 
				new FindAllFacturasAction(startIndex, count);
			
			return (FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllFacturas
	
	public Linea_FacturaVO createLinea_Factura(Linea_FacturaVO linea_Factura) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateLinea_FacturaAction action = 
				new CreateLinea_FacturaAction(linea_Factura);
			
			return (Linea_FacturaVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createLinea_Factura
	
	public void deleteLinea_Factura(int linea, int factura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			DeleteLinea_FacturaAction action = 
				new DeleteLinea_FacturaAction(linea, factura);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // deleteLinea_Factura
	
	public Linea_FacturaChunkVO getLineas(int idFactura, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			GetLineasAction action = 
				new GetLineasAction(idFactura, startIndex, count);
			
			return (Linea_FacturaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getLineas
	
} // class
