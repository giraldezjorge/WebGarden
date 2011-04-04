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
public class BuyProductForm extends ActionForm {

	private String idProducto;
	private int idProductoAsInt;
	private String quantity;
	private int quantityAsInt;
	
	public BuyProductForm() {
		reset();
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	
	public int getIdProductoAsInt() {
		return idProductoAsInt;
	}
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity.trim();
	}

	public int getQuantityAsInt() {
		return quantityAsInt;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

	public ActionErrors validate(ActionMapping mapping, 
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		idProductoAsInt = PropertyValidator.validateInt(errors, "idProducto", 
				idProducto, true, 1, Integer.MAX_VALUE);
		quantityAsInt = PropertyValidator.validateInt(errors, "quantity", 
				quantity, true, 1, Integer.MAX_VALUE);
		
		return errors;
	}
	
	private void reset() {
		idProducto = null;
		idProductoAsInt = 0;
		quantity = null;
		quantityAsInt = 0;
    }
}
