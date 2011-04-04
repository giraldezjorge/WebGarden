/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento_emp.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class Mantenimiento_EmpVO implements Serializable {

	private int mantenimiento;
	private String empleado;
	
	public Mantenimiento_EmpVO(int mantenimiento, String empleado) {
		
		this.mantenimiento = mantenimiento;
		this.empleado = empleado;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public int getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(int mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
}
