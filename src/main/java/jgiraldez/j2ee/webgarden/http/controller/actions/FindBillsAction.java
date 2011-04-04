/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.PrintManager;
import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.
	Linea_FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class FindBillsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		/* Get data. */
    	FindForm findForm = (FindForm) form;
    	String identifierType = findForm.getIdentifierType();
    	int identifier = findForm.getIdentifierAsInt();
    	String identifierAsString = findForm.getIdentifier();
    	
    	/* Do action. */
    	if (FindForm.BILL_IDENTIFIER.equals(identifierType)) {
    		
            return doFindBillByBillIdentifier(
            		mapping, identifier, findForm.getStartIndex(), 
            		findForm.getCount(), request);
            
        } else {
        	
            return doFindBillsByClient(mapping, identifierAsString, 
            		findForm.getStartIndex(), findForm.getCount(), request);
        }
    	
    }

	private ActionForward doFindBillByBillIdentifier(ActionMapping mapping, 
			int identifier, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
    	
    	try {        
            
    		AdministrationFacadeDelegate adminFacade = 
    			AdministrationFacadeDelegateFactory.getDelegate(); 
    		
            FacturaVO facturaVO = adminFacade.findFactura(identifier);

            ClienteVO clienteVO = 
            	UsersManagementFacadeDelegateFactory.getDelegate().
            	findCliente(facturaVO.getCliente());
            
            PrintManager pm = new PrintManager();
            pm.setFirstName(clienteVO.getPila());
            pm.setSurname1(clienteVO.getAp1());
            pm.setSurname2(clienteVO.getAp2());
            pm.setDir(clienteVO.getDir_facturacion());
            pm.setNIF(clienteVO.getNIF());
            pm.setTlf(clienteVO.getTlf());
            pm.setEmail(clienteVO.getEmail());
            pm.setPoblacion(clienteVO.getPoblacion());
            pm.setProvincia(clienteVO.getProvincia());
            pm.setIdFactura(facturaVO.getIdFactura());
            pm.setFecha(facturaVO.getFecha());
            pm.setPagada(facturaVO.getPagada());                      
            
            Linea_FacturaChunkVO lineaChunkVO = adminFacade.getLineas(
            		facturaVO.getIdFactura(), startIndex, count);    
            
            if (lineaChunkVO.getLineaVOs().size() > 0) {
            	
            	ArrayList<PrintManager> collection = 
            		new ArrayList<PrintManager>();
            	
            	List<Linea_FacturaVO> lines = lineaChunkVO.getLineaVOs();
            	ArrayList<Linea_FacturaVO> al = 
            		new ArrayList<Linea_FacturaVO>(lines);

            	double totalFactura = 0;
            	
            	for (int i = 0; i<al.size(); i++) {
            	
            		PrintManager pm2 = new PrintManager();
            		
            		try {
            			
        				String name = ProductsManagementFacadeDelegateFactory.
        					getDelegate().findProducto(al.get(i).
        							getProducto()).getNombre();
        				pm2.setProductName(name);
        				
        			} catch (InstanceNotFoundException e) {
        				
        				ActionMessages errors = new ActionMessages();
        				errors.add("identifier", 
        						new ActionMessage("ErrorMessage.id.notFound"));
        			}
        			
            		pm2.setNum_linea(al.get(i).getNum_linea());
            		pm2.setFactura(al.get(i).getFactura());            	
            		pm2.setCantidad(al.get(i).getCantidad());
            		pm2.setDescuento(al.get(i).getDescuento());
            		pm2.setTotal(al.get(i).getTotal());
            		
            		collection.add(pm2);
            		
            		totalFactura = totalFactura + pm2.getTotal();
            		
            	}            	     
            	
            	pm.setTotalFactura(totalFactura);
            	
            	request.setAttribute("printManager", pm);
                request.setAttribute("lines", collection);
                
            }
            
            return mapping.findForward(
            		"FindBillByBillIdentifierSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.bill.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doFindBillsByClient(ActionMapping mapping, 
			String identifierAsString, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
		
        FacturaChunkVO facturaChunkVO = null;
        
		try {
			
			facturaChunkVO = AdministrationFacadeDelegateFactory.getDelegate().
				findByCliente(identifierAsString, startIndex, count);
			
		} catch (InstanceNotFoundException e) {
			
			ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.bill.notFound"));
            saveErrors(request, errors);          
		}    
        
        if (facturaChunkVO.getFacturaVOs().size() > 0) {
        	
            request.setAttribute("bills", facturaChunkVO.getFacturaVOs());
            
        }
        
        /* Generate parameters for previous and next links. */
        Map previousLinkParameters = 
        	getPreviousLinkParameters(identifierAsString, startIndex, count);
        
        if (previousLinkParameters != null) {
        	
            request.setAttribute("previous", previousLinkParameters);
            
        }
                
        if (facturaChunkVO.getExistMoreFacturas()) {
        	
            Map nextLinkParameters = 
            	getNextLinkParameters(identifierAsString, startIndex, count);        
            request.setAttribute("next", nextLinkParameters);
            
        }
    
        /* Return ActionForward. */    
        return mapping.findForward("FindBillsByClientSuccess");
        
	}
    
	private Map getPreviousLinkParameters(String identifierAsString, 
			int startIndex, int count) {
		
		Map linkAttributes = null;
	                
	    if ( (startIndex-count) > 0 ) {
	    	
	    	linkAttributes = getCommonPreviousNextLinkParameters(
	    			identifierAsString, startIndex, count);
	    	linkAttributes.put("startIndex", new Integer(startIndex-count));
	    	
	    }
	    
	    return linkAttributes;
	    
	}
	
	private Map getNextLinkParameters(String identifierAsString, 
			int startIndex, int count) {
		
		Map linkAttributes = getCommonPreviousNextLinkParameters(
				identifierAsString, startIndex, count);
		linkAttributes.put("startIndex", new Integer(startIndex+count));
		
		return linkAttributes;
		
	}
	
	private Map getCommonPreviousNextLinkParameters(String identifierAsString, 
			int startIndex, int count) {
		
		Map linkAttributes = new HashMap();
		
		linkAttributes.put("identifierType", FindForm.CLIENT_IDENTIFIER);
		linkAttributes.put("identifier", identifierAsString);
		linkAttributes.put("count", new Integer(count));
		
		return linkAttributes;
		
	}
}
