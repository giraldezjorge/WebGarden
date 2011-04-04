/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditClientDetailsForm;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
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
public class EditClientDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// print data
		EditClientDetailsForm clientDetailsForm = (EditClientDetailsForm) form;
				
		ActionMessages errors = new ActionMessages();
		
		ClienteVO clienteVO;

		String NIF = request.getParameter("id");
		
		try {
			
			clienteVO = UsersManagementFacadeDelegateFactory.getDelegate().
				findCliente(NIF);

			clientDetailsForm.setFirstName(clienteVO.getPila());
			clientDetailsForm.setSurname1(clienteVO.getAp1());
			clientDetailsForm.setSurname2(clienteVO.getAp2());
			clientDetailsForm.setNIF(clienteVO.getNIF());
			clientDetailsForm.setDir(clienteVO.getDir());
			clientDetailsForm.setCod_postal(Integer.toString(
					clienteVO.getCod_postal()));
			clientDetailsForm.setTlf(Integer.toString(clienteVO.getTlf()));
			clientDetailsForm.setEmail(clienteVO.getEmail());
			clientDetailsForm.setPoblacion(clienteVO.getPoblacion());
			clientDetailsForm.setProvincia(clienteVO.getProvincia());
			clientDetailsForm.setDir_facturacion(
					clienteVO.getDir_facturacion());
			clientDetailsForm.setVIP(clienteVO.getVIP());
			
		} catch (InstanceNotFoundException e) {
			
			errors.add("identifier", 
					new ActionMessage("ErrorMessages.client.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
		}
		
		return mapping.findForward("UpdateClientDetailsForm");
        	
	}
}
