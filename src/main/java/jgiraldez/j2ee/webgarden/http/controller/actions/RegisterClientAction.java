/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RegisterClientForm;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.util.
	PasswordEncrypter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class RegisterClientAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// get data
		RegisterClientForm clientForm = (RegisterClientForm) form;
		String loginName = clientForm.getLoginName();
		String clearPassword = clientForm.getPassword();
		String firstName = clientForm.getFirstName();
		String surname1 = clientForm.getSurname1();
		String surname2 = clientForm.getSurname2();
		String NIF = clientForm.getNIF();
		String dir = clientForm.getDir();
		String email = clientForm.getEmail();
		int tlf = clientForm.getTlfAsInt();
		int cod_postal = clientForm.getCod_postalAsInt();
		String poblacion = clientForm.getPoblacion();
		String provincia = clientForm.getProvincia();
		String dir_facturacion = clientForm.getDir_facturacion();
		boolean VIP = clientForm.isVIP();
            
		/* encriptamos el password*/
		String encryptedPassword = PasswordEncrypter.crypt(clearPassword);
		
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        ClienteVO client = new ClienteVO(loginName, NIF, encryptedPassword, 
        				firstName, surname1, surname2, email, dir, cod_postal, 
        				tlf, poblacion, provincia, dir_facturacion, VIP);
        
		UsersManagementFacadeDelegate usersFacade = 
			UsersManagementFacadeDelegateFactory.getDelegate();
		
		try {
			
			usersFacade.createCliente(client);
			
        } catch (DuplicateInstanceException e) {
        	
            errors.add("loginName", 
            	new ActionMessage("ErrorMessages.loginName.alreadyExists"));
            
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
