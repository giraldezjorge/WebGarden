/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.ClienteChunkVO;

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
public class FindClientsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
    	
    	/* Get data. */
    	FindForm findForm = (FindForm) form;
    	String identifierType = findForm.getIdentifierType();
    	String identifierAsString = findForm.getIdentifier();
		
    	if (!FindForm.NAME_IDENTIFIER.equals(identifierType)) {
    		
    		return doFindClientByNIF(mapping, identifierAsString, request);
    		
    	} else {
    		
    		return doFindClientByName(mapping, identifierAsString, 
    				findForm.getStartIndex(), findForm.getCount(), request);
    		
    	}
    	
    }

	private ActionForward doFindClientByNIF(ActionMapping mapping, 
			String identifierAsString, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            ClienteVO clienteVO = UsersManagementFacadeDelegateFactory.
            	getDelegate().findCliente(identifierAsString);
                    
            request.setAttribute("client", clienteVO);
            
            return mapping.findForward("FindClientByNIFSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifierAsString", 
            		new ActionMessage("ErrorMessages.client.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doFindClientByName(ActionMapping mapping, 
			String identifierAsString, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
		
        
        try {
        	
			ClienteChunkVO clienteChunkVO = 
				UsersManagementFacadeDelegateFactory.getDelegate().
					findClientesByName(identifierAsString, startIndex, count);
			
			if (clienteChunkVO.getClienteVOs().size() > 0) {
	        	
	            request.setAttribute("clients", clienteChunkVO.getClienteVOs());
	            
	        }
	        
	        /* Generate parameters for previous and next links. */
	        Map previousLinkParameters = getPreviousLinkParameters(
	        		identifierAsString, startIndex, count);
	        
	        if (previousLinkParameters != null) {
	        	
	            request.setAttribute("previous", previousLinkParameters);
	            
	        }
	                
	        if (clienteChunkVO.getExistMoreClientes()) {
	        	
	            Map nextLinkParameters = getNextLinkParameters(
	            		identifierAsString, startIndex, count);        
	            request.setAttribute("next", nextLinkParameters);
	            
	        }
	    
	        /* Return ActionForward. */    
	        return mapping.findForward("FindClientByNameSuccess");
			
		} catch (InstanceNotFoundException e) {
			
			ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.client.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
		}
      
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
		
		linkAttributes.put("identifierType", FindForm.NAME_IDENTIFIER);
		linkAttributes.put("identifier", identifierAsString);
		linkAttributes.put("count", new Integer(count));
		
		return linkAttributes;
		
	}
	
}
