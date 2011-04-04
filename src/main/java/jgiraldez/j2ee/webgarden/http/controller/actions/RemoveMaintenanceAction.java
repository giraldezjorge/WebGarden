/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RemoveForm;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegateFactory;

import java.io.IOException;

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
public class RemoveMaintenanceAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {    	

    	/* Get data. */
    	RemoveForm removeForm = (RemoveForm) form;
    	int identifier = removeForm.getIdentifierAsInt();
    	
        return doRemoveMaintenance(mapping, identifier, request);
    
	}

	private ActionForward doRemoveMaintenance(ActionMapping mapping, 
			int identifier, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            PlanningFacadeDelegateFactory.getDelegate().
            	removeMantenimiento(identifier);
            
            return mapping.findForward("RemoveSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.maintenance.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
}
