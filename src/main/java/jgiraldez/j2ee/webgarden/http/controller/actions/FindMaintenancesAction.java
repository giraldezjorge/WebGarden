/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.FindForm;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.planningfacade.vo.MantenimientoChunkVO;

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
public class FindMaintenancesAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
    	
    	/* Get data. */
    	FindForm findForm = (FindForm) form;
    	String identifierType = findForm.getIdentifierType();
    	int identifier = findForm.getIdentifierAsInt();
    	String identifierAsString = findForm.getIdentifier();

    	
    	/* Do action. */
    	if (FindForm.MAINTENANCE_IDENTIFIER.equals(identifierType)) {
    		
            return doFindMaintenanceByMaintenanceIdentifier(
            		mapping, identifier, request);
            
        } else {
        	
            return doFindMaintenancesByClientIdentifier(mapping, 
            		identifierAsString, findForm.getStartIndex(), 
            		findForm.getCount(), request);
        }
    	
    }

	private ActionForward doFindMaintenanceByMaintenanceIdentifier(
			ActionMapping mapping, int identifier, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            MantenimientoVO mantenimientoVO = PlanningFacadeDelegateFactory.
            	getDelegate().findMantenimiento(identifier);
                    
            request.setAttribute("maintenance", mantenimientoVO);
            
            return mapping.findForward(
            		"FindMaintenanceByMaintenanceIdentifierSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.maintenance.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doFindMaintenancesByClientIdentifier(
			ActionMapping mapping, String identifierAsString, 
			int startIndex, int count, HttpServletRequest request) 
			throws InternalErrorException {
				                    
        MantenimientoChunkVO mantenimientoChunkVO = 
        	PlanningFacadeDelegateFactory.getDelegate().
        	findMantenimientoByCliente(identifierAsString, startIndex, count);    
        
        if (mantenimientoChunkVO.getMantenimientoVOs().size() > 0) {
        	
            request.setAttribute("maintenances", 
            		mantenimientoChunkVO.getMantenimientoVOs());
            
        }
        
        /* Generate parameters for previous and next links. */
        Map previousLinkParameters = 
        	getPreviousLinkParameters(identifierAsString, startIndex, count);
        
        if (previousLinkParameters != null) {
        	
            request.setAttribute("previous", previousLinkParameters);
            
        }
                
        if (mantenimientoChunkVO.getExistMoreMantenimientos()) {
        	
            Map nextLinkParameters = 
            	getNextLinkParameters(identifierAsString, startIndex, count);        
            request.setAttribute("next", nextLinkParameters);
            
        }
    
        /* Return ActionForward. */    
        return mapping.findForward("FindMaintenancesByClientIdentifierSuccess");
        
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
