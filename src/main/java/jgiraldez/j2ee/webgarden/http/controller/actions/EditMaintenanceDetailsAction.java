/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.
	EditMaintenanceDetailsForm;
import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;
import jgiraldez.j2ee.webgarden.model.planningfacade.delegate.
	PlanningFacadeDelegateFactory;

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
public class EditMaintenanceDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// print data
		EditMaintenanceDetailsForm maintenanceDetailsForm = 
			(EditMaintenanceDetailsForm) form;
				
		ActionMessages errors = new ActionMessages();
		
		MantenimientoVO mantenimientoVO;

		String aux = request.getParameter("id");
		System.out.println("PRINT"+ aux);
		int idMantenimiento = Integer.parseInt(request.getParameter("id"));
		
		try {
			
			mantenimientoVO = PlanningFacadeDelegateFactory.getDelegate().
				findMantenimiento(idMantenimiento);
			
			String fecha = mantenimientoVO.getFecha().toString();
			
			String year = fecha.substring(0, 4);
			
			String month;
			if (fecha.substring(5, 6).equals("0")) {
				month = fecha.substring(6, 7);
			} else {
			 month = fecha.substring(5, 7);
			}
			
			String day;
			if (fecha.substring(8, 9).equals("0")) {
				day = fecha.substring(9, 10);
			} else {
				day = fecha.substring(8, 10);
			}
			
			String hour;
			if (fecha.substring(11, 12).equals("0")) {
				hour = fecha.substring(12, 13);
			} else {
				hour = fecha.substring(11, 13);
			}
			
			String minutes;
			if (fecha.substring(14, 15).equals("0")) {
				minutes = fecha.substring(15, 16);
			} else {
				minutes = fecha.substring(14, 16);
			}			
			
			maintenanceDetailsForm.setIdMantenimiento(aux);
			maintenanceDetailsForm.setDay(day);
			maintenanceDetailsForm.setMonth(month);
			maintenanceDetailsForm.setYear(year);
			maintenanceDetailsForm.setHour(hour);
			maintenanceDetailsForm.setMinutes(minutes);
			maintenanceDetailsForm.setNameDay(mantenimientoVO.getDia());
			maintenanceDetailsForm.setLugar(mantenimientoVO.getLugar());
			maintenanceDetailsForm.setDescripcion(
					mantenimientoVO.getDescripcion());
				     
		} catch (InstanceNotFoundException e) {
			
			errors.add("identifier", 
					new ActionMessage("ErrorMessages.maintenance.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
		}
		
		return mapping.findForward("UpdateMaintenanceDetailsForm");
        	
	}
}
