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
public class RegisterProductForm extends ActionForm {

//	private String idProducto;
//	private int idProductoAsInt;
	private String nombre;
	private String descripcion;
	private String precio;
	private double precioAsDouble;
	private String categoria;
	
	public RegisterProductForm() {
		reset();
	}

//	public String getIdProducto() {
//		return idProducto;
//	}
//
//	public void setIdProducto(String idProducto) {
//		this.idProducto = idProducto;
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

//	public int getIdProductoAsInt() {
//		return idProductoAsInt;
//	}

	public double getPrecioAsDouble() {
		return precioAsDouble;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

	public ActionErrors validate(ActionMapping mapping, 
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
//		idProductoAsInt = PropertyValidator.validateInt(errors, 
//				"idProducto", idProducto, true, 1, Integer.MAX_VALUE);
		precioAsDouble = PropertyValidator.validateDouble(errors, 
				"precio", precio, true, 0, 
				Integer.MAX_VALUE, request.getLocale());
		
		PropertyValidator.validateMandatory(errors, "nombre", nombre);
		PropertyValidator.validateMandatory(errors, "descripcion", descripcion);
		
		return errors;
	}
	
	private void reset() {    
//		idProducto = null;
//		idProductoAsInt = 0;
		nombre = null;
		descripcion = null;
		precio =null;
		precioAsDouble = 0;
		categoria = null;	
    }
	
} // class
