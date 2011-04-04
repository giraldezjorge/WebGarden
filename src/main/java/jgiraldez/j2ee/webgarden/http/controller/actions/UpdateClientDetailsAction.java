/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditClientDetailsForm;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;

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
public class UpdateClientDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException, InternalErrorException {
	        
	        /* Get data. */
	        EditClientDetailsForm clientDetailsForm = 
	        	(EditClientDetailsForm) form;
	        	
	        ActionMessages errors = new ActionMessages();	       
	                            
	        try {	     
	        	
				UsersManagementFacadeDelegateFactory.getDelegate().
				modifyCliente(clientDetailsForm.getNIF(), 
						clientDetailsForm.getFirstName(), 
						clientDetailsForm.getSurname1(), 
						clientDetailsForm.getSurname2(), 
						clientDetailsForm.getEmail(), 
						clientDetailsForm.getDir(), 
						clientDetailsForm.getCod_postalAsInt(), 
						clientDetailsForm.getTlfAsInt(), 
						clientDetailsForm.getPoblacion(), 
						clientDetailsForm.getProvincia(), 
						clientDetailsForm.getDir_facturacion(), 
						clientDetailsForm.getVIP());
				
			} catch (InstanceNotFoundException e) {
				
				errors.add("NIF", 
						new ActionMessage("ErrorMessages.client.notFound"));
				
			}
	        
	        /* Return ActionForward. */
			if (errors.isEmpty()) {
				
	            return mapping.findForward("Success");
	            
	        } else {
	        	
	            saveErrors(request, errors);            
	            return new ActionForward(mapping.getInput());
	            
	        }
	        
	    }
}
