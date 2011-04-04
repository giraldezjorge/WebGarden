/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RegisterEmployeeForm;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
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
public class RegisterEmployeeAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// get data
		RegisterEmployeeForm employeeForm = (RegisterEmployeeForm) form;
		String NIF = employeeForm.getNIF();
		String clearPassword = employeeForm.getPassword();
		String firstName = employeeForm.getFirstName();
		String surname1 = employeeForm.getSurname1();
		String surname2 = employeeForm.getSurname2();
		String dir = employeeForm.getDir();
		int tlf = employeeForm.getTlfAsInt();
		int cod_postal = employeeForm.getCod_postalAsInt();
		String poblacion = employeeForm.getPoblacion();
		String provincia = employeeForm.getProvincia();
		long num_cuenta = employeeForm.getNum_cuentaAsLong();
		boolean esAdmin = employeeForm.getEsAdmin();
		
		/* encriptamos el password*/
		String encryptedPassword;
		if (esAdmin) {
			encryptedPassword = clearPassword;
		}
		encryptedPassword = PasswordEncrypter.crypt(clearPassword);
		
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        EmpleadoVO employee = new EmpleadoVO(NIF, encryptedPassword, 
        		firstName, surname1, surname2, dir, cod_postal, tlf, 
        		poblacion, provincia, num_cuenta, esAdmin);
        
		UsersManagementFacadeDelegate usersFacade = 
			UsersManagementFacadeDelegateFactory.getDelegate();
		
		try {
			
			usersFacade.createEmpleado(employee);
			
        } catch (DuplicateInstanceException e) {
        	
            errors.add("NIF", 
            		new ActionMessage("ErrorMessages.NIF.alreadyExists"));
            
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
