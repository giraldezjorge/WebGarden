/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	CategoriaChunkVO;

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
public class FindCategoriesAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		/* Get data. */
    	FindForm findForm = (FindForm) form;
    	String identifierType = findForm.getIdentifierType();
    	String identifierAsString = findForm.getIdentifier();
    	
    	/* Do action. */
    	if (FindForm.NAME_IDENTIFIER.equals(identifierType)) {
    		
            return doFindCategoryByName(mapping, 
            		identifierAsString, request);
            
        } else {
        	
            return doGetSons(mapping, identifierAsString, 
            		findForm.getStartIndex(), findForm.getCount(), request);
        }
    	
    }

	private ActionForward doFindCategoryByName(
			ActionMapping mapping, String identifierAsString, 
			HttpServletRequest request) throws InternalErrorException {
    	
    	try {        
            
            CategoriaVO categoriaVO = ProductsManagementFacadeDelegateFactory.
            	getDelegate().findCategoriaByName(identifierAsString);
                    
            request.setAttribute("category", categoriaVO);
            
            return mapping.findForward(
            		"FindCategoryByCategoryIdentifierSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.category.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doGetSons(ActionMapping mapping, 
			String identifierAsString, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
		
		
		CategoriaVO categoriaVO;
		
		try {
			
			categoriaVO = ProductsManagementFacadeDelegateFactory.
				getDelegate().findCategoriaByName(identifierAsString);
			
		} catch (InstanceNotFoundException e) {
			
			ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.category.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
		}
		
		int identifier = categoriaVO.getIdCategoria();
        CategoriaChunkVO categoriaChunkVO = 
        	ProductsManagementFacadeDelegateFactory.getDelegate().
        		getSons(identifier, startIndex, count);    
        
        if (categoriaChunkVO.getCategoriaVOs().size() > 0) {
        	
            request.setAttribute("categorySons", 
            		categoriaChunkVO.getCategoriaVOs());
            
        }
        
        /* Generate parameters for previous and next links. */
        Map previousLinkParameters = getPreviousLinkParameters(
        		identifierAsString, startIndex, count);
        
        if (previousLinkParameters != null) {
        	
            request.setAttribute("previous", previousLinkParameters);
            
        }
                
        if (categoriaChunkVO.getExistMoreCategorias()) {
        	
            Map nextLinkParameters = getNextLinkParameters(identifierAsString, 
            		startIndex, count);        
            request.setAttribute("next", nextLinkParameters);
            
        }
    
        /* Return ActionForward. */    
        return mapping.findForward("FindCategoriesByFatherIdentifierSuccess");
        
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
		
		linkAttributes.put("identifierType", FindForm.FATHER_IDENTIFIER);
		linkAttributes.put("identifier", identifierAsString);
		linkAttributes.put("count", new Integer(count));
		
		return linkAttributes;
		
	}
	
}
