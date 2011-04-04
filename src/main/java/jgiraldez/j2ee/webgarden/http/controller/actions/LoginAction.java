/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.http.view.actionforms.LoginForm;
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

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class LoginAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping,
	        ActionForm form, HttpServletRequest request,
	        HttpServletResponse response)
	        throws IOException, ServletException, InternalErrorException {
	        
	        /* Get data. */
	        LoginForm loginForm = (LoginForm) form;
	        String loginName = loginForm.getLoginName();
	        String password = loginForm.getPassword();
	        boolean rememberMyPassword = loginForm.getRememberMyPassword();
	        String loginBy = loginForm.getLoginBy();

	        /* Do login. */
	        ActionMessages errors = new ActionMessages();
	        
	        try {
	        	
	            SessionManager.login(request, response, loginName, password, 
	            					rememberMyPassword, loginBy);
	                
	        } catch (InstanceNotFoundException e) {
	        	
	            errors.add("loginName", 
	            		new ActionMessage("ErrorMessages.loginName.notFound"));
	            
	        } catch (IncorrectPasswordException e) {
	        	
	            errors.add("password", 
	            		new ActionMessage("ErrorMessages.password.incorrect"));
	            
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
