/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.producto.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class ProductoVO implements Serializable {

	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int categoria;
	
	public ProductoVO(int idProducto, String nombre, String descripcion, 
			double precio, int categoria) {
		
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
