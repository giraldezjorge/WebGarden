/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author jorge
 *
 */
public class MantenimientoVO implements Serializable {

	private int idMantenimiento;
	private String lugar;
	private String descripcion;
	private Timestamp fecha;
	private String dia;
	private String cliente;
	
	public MantenimientoVO(int idMantenimiento, String lugar, 
			String descripcion, Timestamp fecha, String dia, String cliente) {
		
		this.idMantenimiento = idMantenimiento;
		this.lugar = lugar;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.dia = dia;
		this.cliente = cliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(int idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}
	
}
