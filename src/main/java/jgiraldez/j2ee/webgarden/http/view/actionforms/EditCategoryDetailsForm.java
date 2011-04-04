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
public class EditCategoryDetailsForm extends ActionForm {

	private String idCategoria;
	private int idCategoriaAsInt;
	private String nombre;
	private String padre;
//	private int padreAsInt;
	
    public EditCategoryDetailsForm() {
        reset();
    }
        
    public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria.trim();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre.trim();
	}

	public int getIdCategoriaAsInt() {
		return idCategoriaAsInt;
	}
//
//	public int getPadreAsInt() {
//		return padreAsInt;
//	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

	public ActionErrors validate(ActionMapping mapping, 
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		idCategoriaAsInt = PropertyValidator.validateInt(errors, 
				"idCategoria", idCategoria, true, 1, Integer.MAX_VALUE);
//		padreAsInt = PropertyValidator.validateInt(errors, 
//				"padre", padre, false, 0, Integer.MAX_VALUE);
		PropertyValidator.validateMandatory(errors, "nombre", nombre);
		PropertyValidator.validateMandatory(errors, "padre", padre);
		
		return errors;
	}
	
	private void reset() {    
		idCategoria = null;
		idCategoriaAsInt = 0;
		nombre = null;
		padre =null;
//		padreAsInt = 0;
    }
}
