/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.http.view.actionforms.ChangePasswordForm;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class ChangePasswordAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping,
	        ActionForm form, HttpServletRequest request,
	        HttpServletResponse response)
	        throws IOException, ServletException, InternalErrorException {
	        
	        /* Get data. */
	        ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;
	        String oldPassword = changePasswordForm.getOldPassword();
	        String newPassword = changePasswordForm.getNewPassword();

	        /* Do login. */
	        ActionMessages errors = new ActionMessages();        
	            
	        UsersManagementFacadeDelegate usersFacade = 
				UsersManagementFacadeDelegateFactory.getDelegate();
	        
	        String NIF = SessionManager.getNIF(request);
	        
	        try {
	        	
	            usersFacade.changePassword(NIF, oldPassword, newPassword);
	            
	        } catch (IncorrectPasswordException e) {
	            errors.add("oldPassword", new ActionMessage(
	                "ErrorMessages.password.incorrect"));
	        } 
	        
	        /* Return ActionForward. */
	        if (errors.isEmpty()) {
	            return mapping.findForward("Welcome");
	        } else {
	            saveErrors(request, errors);            
	            return new ActionForward(mapping.getInput());
	        }
	        
	    }
}
