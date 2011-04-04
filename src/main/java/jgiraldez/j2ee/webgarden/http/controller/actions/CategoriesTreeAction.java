/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	CategoriaChunkVO;
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
public class CategoriesTreeAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {				
		
		/* Get data. */
//    	FindForm findForm = (FindForm) form;    
//    	String identifierAsString = findForm.getIdentifier();
    	int identifier = Integer.parseInt(request.getParameter("identifier"));
    	
    	/* Do action. */	
    	return doFindCategoryProductsAndSons(mapping, identifier, 
    			1, 10, request);       
    	
    }

	private ActionForward doFindCategoryProductsAndSons(
			ActionMapping mapping, int identifier, int startIndex,
			int count, HttpServletRequest request) 
			throws InternalErrorException {
    	
		ActionMessages errors = new ActionMessages();
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		CategoriaVO categoriaVO = null;
		
		try {
			
			categoriaVO = 
				productsFacade.findCategoria(identifier);
			
		} catch (InstanceNotFoundException e) {						
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.category.notFound"));
            saveErrors(request, errors);
		}
		
		request.setAttribute("id", categoriaVO.getPadre());
		
		request.setAttribute("categoryName", 
        		categoriaVO.getNombre());			
		
        CategoriaChunkVO categoriaChunkVO = 
        	productsFacade.getSons(identifier, startIndex, count);    
        
        if (categoriaChunkVO.getCategoriaVOs().size() > 0) {
        	
            request.setAttribute("categorySons", 
            		categoriaChunkVO.getCategoriaVOs());
            
        }
                
        ProductoChunkVO productoChunkVO =
        	productsFacade.getProductosCategoria(identifier, startIndex, count);
        
        if (productoChunkVO.getProductoVOs().size() > 0) {
        	
        	request.setAttribute("categoryProducts", 
            		productoChunkVO.getProductoVOs());
        }
        
        /* Generate parameters for previous and next links. */
        Map previousLinkParameters = getPreviousLinkParameters(
        		identifier, startIndex, count);
        
        if (previousLinkParameters != null) {
        	
            request.setAttribute("previous", previousLinkParameters);
            
        }
                
        if (categoriaChunkVO.getExistMoreCategorias()) {
        	
            Map nextLinkParameters = getNextLinkParameters(identifier, 
            		startIndex, count);        
            request.setAttribute("next", nextLinkParameters);
            
        }
    
        /* Return ActionForward. */    
        return mapping.findForward("FindCategoryProductsAndSonsSuccess");
    }
    
	private Map getPreviousLinkParameters(int identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = null;
	                
	    if ( (startIndex-count) > 0 ) {
	    	
	    	linkAttributes = getCommonPreviousNextLinkParameters(
	    			identifier, startIndex, count);
	    	linkAttributes.put("startIndex", new Integer(startIndex-count));
	    	
	    }
	    
	    return linkAttributes;
	    
	}
	
	private Map getNextLinkParameters(int identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = getCommonPreviousNextLinkParameters(
				identifier, startIndex, count);
		linkAttributes.put("startIndex", new Integer(startIndex+count));
		
		return linkAttributes;
		
	}
	
	private Map getCommonPreviousNextLinkParameters(int identifier, 
			int startIndex, int count) {
		
		Map linkAttributes = new HashMap();
		
		linkAttributes.put("identifierType", FindForm.FATHER_IDENTIFIER);
		linkAttributes.put("identifier", identifier);
		linkAttributes.put("count", new Integer(count));
		
		return linkAttributes;
		
	}
}
