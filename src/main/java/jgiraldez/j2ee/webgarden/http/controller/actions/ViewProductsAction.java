/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.ViewForm;
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

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class ViewProductsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
    	
		/* Get data. */
    	ViewForm viewForm = (ViewForm) form;
		
    	/* Do action. */
    	return doFindAllProducts(mapping, viewForm.getStartIndex(), 
    			viewForm.getCount(), request);
    }
    
    private ActionForward doFindAllProducts(ActionMapping mapping, 
    		int startIndex, int count, HttpServletRequest request) 
    		throws InternalErrorException {
    	
    	/* Find products. */
    	ProductoChunkVO productoChunkVO = 
    		ProductsManagementFacadeDelegateFactory.getDelegate().
    			findAllProductos(startIndex, count);
    	
    	if (productoChunkVO.getProductoVOs().size() > 0) {
    		request.setAttribute("productos", productoChunkVO.getProductoVOs());
    	}
    	
    	/* Generate parameters for previous and next links. */
    	Map previousLinkParameters = 
    		getPreviousLinkParameters(startIndex, count);
    	if (previousLinkParameters != null) {
    		request.setAttribute("previous", previousLinkParameters);
    	}
    	
    	if (productoChunkVO.getExistMoreProductos()) {
    		Map nextLinkParameters = getNextLinkParameters(startIndex, count);        
    		request.setAttribute("next", nextLinkParameters);
    	}
    	
    	/* Return ActionForward. */
    	return mapping.findForward("ViewProducts");
    }
    
    private Map getPreviousLinkParameters(int startIndex, int count) {        
    	
    	Map linkAttributes = null;
    	
    	if ( (startIndex-count) > 0 ) {
    		linkAttributes = 
    			getCommonPreviousNextLinkParameters(startIndex, count);
    		linkAttributes.put("startIndex", new Integer(startIndex-count));
    	}
    	
    	return linkAttributes;
    }
    
    private Map getNextLinkParameters(int startIndex, int count) {        
    	
    	Map linkAttributes = 
    		getCommonPreviousNextLinkParameters(startIndex, count);
    	
    	linkAttributes.put("startIndex", new Integer(startIndex+count));
    	
    	return linkAttributes;
    }
    
    private Map getCommonPreviousNextLinkParameters(int startIndex, int count) {
    	
    	Map linkAttributes = new HashMap();
    	
    	linkAttributes.put("count", new Integer(count));
    	
    	return linkAttributes;
    }
}
