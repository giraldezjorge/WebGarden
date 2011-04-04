/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditEmployeeDetailsForm;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.
	delegate.UsersManagementFacadeDelegateFactory;

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
public class EditEmployeeDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// print data
		EditEmployeeDetailsForm employeeDetailsForm = (EditEmployeeDetailsForm) form;
				
		ActionMessages errors = new ActionMessages();
		
		EmpleadoVO empleadoVO;

		String NIF = request.getParameter("id");
		
		try {
			
			empleadoVO = UsersManagementFacadeDelegateFactory.getDelegate().
				findEmpleado(NIF);

			employeeDetailsForm.setFirstName(empleadoVO.getPila());
			employeeDetailsForm.setSurname1(empleadoVO.getAp1());
			employeeDetailsForm.setSurname2(empleadoVO.getAp2());
			employeeDetailsForm.setNIF(empleadoVO.getNIF());
			employeeDetailsForm.setDir(empleadoVO.getDir());
			employeeDetailsForm.setCod_postal(Integer.toString(
					empleadoVO.getCod_postal()));
			employeeDetailsForm.setTlf(Integer.toString(empleadoVO.getTlf()));
			employeeDetailsForm.setPoblacion(empleadoVO.getPoblacion());
			employeeDetailsForm.setProvincia(empleadoVO.getProvincia());
			employeeDetailsForm.setNum_cuenta(Long.toString(
					empleadoVO.getNum_cuenta()));					
			employeeDetailsForm.setEsAdmin(empleadoVO.getEsAdmin());
			
		} catch (InstanceNotFoundException e) {
			
			errors.add("identifier", 
					new ActionMessage("ErrorMessages.employee.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
		}
		
		return mapping.findForward("UpdateEmployeeDetailsForm");
        	
	}
}
