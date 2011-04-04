/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RegisterMaintenanceForm;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegateFactory;

import java.io.IOException;
import java.sql.Timestamp;

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
public class RegisterMaintenanceAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// get data
		RegisterMaintenanceForm maintenanceForm = 
			(RegisterMaintenanceForm) form;
		
		String lugar = maintenanceForm.getLugar();
		String descripcion = maintenanceForm.getDescripcion();
		Timestamp fecha = maintenanceForm.getTimestamp();
		String dia = maintenanceForm.getNameDay();
		String cliente = maintenanceForm.getCliente();
		String empleado = maintenanceForm.getEmpleado();
		
        /* Register maintenance. */            
        ActionMessages errors = new ActionMessages();
          
        MantenimientoVO mantenimientoVO = new MantenimientoVO(0, lugar, 
        			descripcion, fecha, dia, cliente);
        
		PlanningFacadeDelegate planningFacade = 
			PlanningFacadeDelegateFactory.getDelegate();
		
		try {
			
			mantenimientoVO = 
				planningFacade.createMantenimiento(mantenimientoVO);
			planningFacade.assignEmpleadoToMantenimiento(empleado, 
					mantenimientoVO.getIdMantenimiento());
			
		} catch (DuplicateInstanceException e) {
			errors.add("idMantenimiento", 
					new ActionMessage("ErrorMessages.id.alreadyExists"));
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
