/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.factura.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author jorge
 *
 */
public class FacturaVO implements Serializable {
	
	private int idFactura;
	private Timestamp fecha;
	private boolean pagada;
	private String cliente;
	
	public FacturaVO(int idFactura, Timestamp fecha, 
			boolean pagada, String cliente) {
		
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.pagada = pagada;
		this.cliente = cliente;
	}
	
	public boolean getPagada() {
		return pagada;
	}
	
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

}
