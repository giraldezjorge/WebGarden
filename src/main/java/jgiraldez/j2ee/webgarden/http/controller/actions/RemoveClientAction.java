/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RemoveForm;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.ClienteChunkVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class RemoveClientAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {    	

    	/* Get data. */
    	RemoveForm removeForm = (RemoveForm) form;
    	String identifierType = removeForm.getIdentifierType();
    	String identifierAsString = removeForm.getIdentifier();
    	
    	/* Do action. */
    	if (!RemoveForm.NAME_IDENTIFIER.equals(identifierType)) {
        
    		return doRemoveClient(mapping, identifierAsString, request);
    		
    	} else {
    		
    		return doRemoveClientByName(mapping, identifierAsString, 
    				removeForm.getStartIndex(), removeForm.getCount(), request);
    		
    	}
           
    }

	private ActionForward doRemoveClient(ActionMapping mapping, 
			String identifierAsString, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            UsersManagementFacadeDelegateFactory.getDelegate().
            	removeCliente(identifierAsString);
            
            HttpSession session = request.getSession(false);
            
            if (session.getAttribute("type").equals("client")) {
            	
            	return mapping.findForward("Logout");
            	
            } else {
            	
            	return mapping.findForward("RemoveSuccess");
            	
            }
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.client.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doRemoveClientByName(ActionMapping mapping, 
			String identifierAsString, int startIndex, int count, 
			HttpServletRequest request) throws InternalErrorException {
    	
		/* Find clients by name. */
		
    	try {
    		
    		ClienteChunkVO clienteChunkVO = 
        		UsersManagementFacadeDelegateFactory.getDelegate().
        			findClientesByName(identifierAsString, startIndex, count);

    		if (clienteChunkVO.getClienteVOs().size() > 0) {
        		
        		request.setAttribute(
        				"clientes", clienteChunkVO.getClienteVOs());
        		
        	}
        	
        	/* Generate parameters for previous and next links. */
        	Map previousLinkParameters = 
        		getPreviousLinkParameters(startIndex, count);
        	if (previousLinkParameters != null) {
        		
        		request.setAttribute("previous", previousLinkParameters);
        		
        	}
        	
        	if (clienteChunkVO.getExistMoreClientes()) {
        		
        		Map nextLinkParameters = 
        			getNextLinkParameters(startIndex, count);
        		
        		request.setAttribute("next", nextLinkParameters);
        		
        	}
        	
        	/* Return ActionForward. */
        	return mapping.findForward("ViewClientsToRemove");
        	
    	} catch (InstanceNotFoundException e) {

    		ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.client.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
    	}
		
    	
    	
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
