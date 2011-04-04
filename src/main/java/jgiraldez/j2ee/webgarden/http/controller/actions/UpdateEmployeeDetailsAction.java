/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditEmployeeDetailsForm;
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
public class UpdateEmployeeDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException, InternalErrorException {
	        
	        /* Get data. */
	        EditEmployeeDetailsForm employeeDetailsForm = 
	        	(EditEmployeeDetailsForm) form;
	        	
	        ActionMessages errors = new ActionMessages();	       
	                            
	        try {	     
	        	
				UsersManagementFacadeDelegateFactory.getDelegate().
				modifyEmpleado(employeeDetailsForm.getNIF(), 
							employeeDetailsForm.getFirstName(), 
							employeeDetailsForm.getSurname1(), 
							employeeDetailsForm.getSurname2(), 
							employeeDetailsForm.getDir(), 
							employeeDetailsForm.getCod_postalAsInt(), 
							employeeDetailsForm.getTlfAsInt(), 
							employeeDetailsForm.getPoblacion(), 
							employeeDetailsForm.getProvincia(), 
							employeeDetailsForm.getNum_cuentaAsLong(), 
							employeeDetailsForm.getEsAdmin());
				
			} catch (InstanceNotFoundException e) {
				
				errors.add("NIF", 
						new ActionMessage("ErrorMessages.employee.notFound"));
				
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
