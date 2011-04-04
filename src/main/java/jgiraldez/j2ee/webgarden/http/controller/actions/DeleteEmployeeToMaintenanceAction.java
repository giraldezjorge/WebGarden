/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EmployeeToMaintenanceForm;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegateFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class DeleteEmployeeToMaintenanceAction  extends DefaultAction{

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// get data
		EmployeeToMaintenanceForm employeeForm = 
			(EmployeeToMaintenanceForm) form;
		
		int idMantenimiento = employeeForm.getIdMantenimientoAsInt();
		String empleado = employeeForm.getEmpleado();
		
        /* Register category. */            
        ActionMessages errors = new ActionMessages();
          
		PlanningFacadeDelegate planningFacade = 
			PlanningFacadeDelegateFactory.getDelegate();
		
		planningFacade.deleteEmpleadoToMantenimiento(
				empleado, idMantenimiento);
		
		
        /* Return ActionForward. */
        if (errors.isEmpty()) {
        	
            return mapping.findForward("Success");
            
        } else {
        	
            saveErrors(request, errors);            
            return new ActionForward(mapping.getInput());
            
        }	
	}
}
