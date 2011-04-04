/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.linea_factura.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class Linea_FacturaVO implements Serializable {

	private int num_linea;
	private int factura;
	private int producto;
	private int cantidad;
	private double descuento;
	private double total;
	
	public Linea_FacturaVO(int num_linea, int factura, int producto, 
			int cantidad, double descuento, double total) {
		
		this.num_linea = num_linea;
		this.factura = factura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.descuento = descuento;
		this.total = total;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getNum_linea() {
		return num_linea;
	}

	public void setNum_linea(int num_linea) {
		this.num_linea = num_linea;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
