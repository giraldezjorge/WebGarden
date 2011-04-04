/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	ProductoChunkVO;

import java.io.IOException;
import java.util.HashMap;
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
public class FindProductsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
    	
		/* Get data. */
    	FindForm findForm = (FindForm) form;
    	String identifierType = findForm.getIdentifierType();
    	String identifier = findForm.getIdentifier();
    	
    	/* Do action. */
    	if (FindForm.NAME_IDENTIFIER.equals(identifierType)) {
    		
            return doFindProductByName(
            		mapping, identifier, request);
            
        } else {
        	
            return doFindProductsByCategoryName(mapping, identifier, 
            		findForm.getStartIndex(), findForm.getCount(), request);
            
        }
    	
    }

	private ActionForward doFindProductByName(ActionMapping mapping, 
			String identifier, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            ProductoVO productoVO = ProductsManagementFacadeDelegateFactory.
            	getDelegate().findProductoByName(identifier);
                    
            request.setAttribute("product", productoVO);
            
            return mapping.findForward("FindProductByNameSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.product.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doFindProductsByCategoryName(
			ActionMapping mapping, String identifier, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		CategoriaVO categoriaVO = null;
		
		try {
			categoriaVO = productsFacade.findCategoriaByName(identifier);
		} catch (InstanceNotFoundException e) {
			
			ActionMessages errors = new ActionMessages();     
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.product.notFound"));
            saveErrors(request, errors);
		}
		
        ProductoChunkVO productoChunkVO = 
        	productsFacade.getProductosCategoria(
        			categoriaVO.getIdCategoria(), startIndex, count);    
        
        if (productoChunkVO.getProductoVOs().size() > 0) {
        	
            request.setAttribute("products", productoChunkVO.getProductoVOs());
            
        }
        
        /* Generate parameters for previous and next links. */
        Map previousLinkParameters = 
        	getPreviousLinkParameters(identifier, startIndex, count);
        
        if (previousLinkParameters != null) {
        	
            request.setAttribute("previous", previousLinkParameters);
            
        }
                
        if (productoChunkVO.getExistMoreProductos()) {
        	
            Map nextLinkParameters = 
            	getNextLinkParameters(identifier, startIndex, count);        
            request.setAttribute("next", nextLinkParameters);
            
        }
    
        /* Return ActionForward. */    
        return mapping.findForward("FindProductsByCategoryNameSuccess");
        
	}
    
	private Map getPreviousLinkParameters(String identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = null;
	                
	    if ( (startIndex-count) > 0 ) {
	    	
	    	linkAttributes = getCommonPreviousNextLinkParameters(identifier, 
	    			startIndex, count);
	    	linkAttributes.put("startIndex", new Integer(startIndex-count));
	    	
	    }
	    
	    return linkAttributes;
	    
	}
	
	private Map getNextLinkParameters(String identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = getCommonPreviousNextLinkParameters(identifier, 
				startIndex, count);
		linkAttributes.put("startIndex", new Integer(startIndex+count));
		
		return linkAttributes;
		
	}
	
	private Map getCommonPreviousNextLinkParameters(String identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = new HashMap();
		
		linkAttributes.put("identifierType", FindForm.NAME_IDENTIFIER);
		linkAttributes.put("identifier", identifier);
		linkAttributes.put("count", new Integer(count));
		
		return linkAttributes;
		
	}
	
}// class
