package jgiraldez.j2ee.webgarden.model.categoria.vo;


import java.io.Serializable;
/**
 * @author jorge
 *
 */
public class CategoriaVO implements Serializable {
	
	private int idCategoria;
	private String nombre;
	private int padre;
	
	public CategoriaVO(int id, String nombre, int padre) {
		this.idCategoria = id;
		this.nombre = nombre;
		this.padre = padre;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPadre() {
		return padre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPadre(int nuevoPadre) {
		this.padre = nuevoPadre;
	}
	
	public String toString() {
		return "Categoria: " + this.nombre + " Codigo: " +
				this.idCategoria + " Padre: " + this.padre;
	}
}
