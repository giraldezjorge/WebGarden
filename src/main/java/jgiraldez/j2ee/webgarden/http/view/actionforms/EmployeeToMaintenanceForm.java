/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.struts.action.PropertyValidator;

/**
 * @author jorge
 *
 */
public class EmployeeToMaintenanceForm extends ActionForm {

	private String idMantenimiento;
	private int idMantenimientoAsInt;
	private String empleado;
	
	public EmployeeToMaintenanceForm() {
		reset();
	}

	public String getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(String idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public int getIdMantenimientoAsInt() {
		return idMantenimientoAsInt;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

	public ActionErrors validate(ActionMapping mapping, 
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		idMantenimientoAsInt = PropertyValidator.validateInt(errors, 
				"idMantenimiento", idMantenimiento, true, 1, Integer.MAX_VALUE);
		
		PropertyValidator.validateMandatory(errors, "empleado", empleado);
		
		return errors;
	}
	
	private void reset() {    
		idMantenimiento = null;
		idMantenimientoAsInt = 0;
		empleado = null;
	}
	
}
