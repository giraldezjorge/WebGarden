/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditProductDetailsForm;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class EditProductDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// print data
		EditProductDetailsForm productForm = (EditProductDetailsForm) form;
				
		ActionMessages errors = new ActionMessages();
		
		ProductoVO producto;

		int idProducto = Integer.parseInt(request.getParameter("id"));	
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		try {
			
			producto = productsFacade.findProducto(idProducto);
			
			productForm.setIdProducto(Integer.toString(
					producto.getIdProducto()));
			productForm.setNombre(producto.getNombre());
			productForm.setDescripcion(producto.getDescripcion());
			
			Locale locale = request.getLocale();
			NumberFormat numberFormatter = NumberFormat.getInstance(locale);
			productForm.setPrecio(numberFormatter.format(producto.getPrecio()));
			String categoryName = productsFacade.findCategoria(
					producto.getCategoria()).getNombre();
			System.out.println(categoryName);
			productForm.setCategoria(categoryName);
			
		} catch (InstanceNotFoundException e) {
			
			errors.add("idProducto", 
					new ActionMessage("ErrorMessages.product.notFound"));
			
		}
		
		return mapping.findForward("UpdateProductDetailsForm");
        	
	}
}
