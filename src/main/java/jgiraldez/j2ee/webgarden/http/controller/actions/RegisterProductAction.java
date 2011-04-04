/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RegisterProductForm;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class RegisterProductAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
	    ActionMessages errors = new ActionMessages();
		
		// get data
		RegisterProductForm productForm = (RegisterProductForm) form;
//		int idProducto = productForm.getIdProductoAsInt();
		String nombre = productForm.getNombre();
		String descripcion = productForm.getDescripcion();
		double precio = productForm.getPrecioAsDouble();
		String categoria = productForm.getCategoria();
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		CategoriaVO categoriaVO = null;
		try {
			
			categoriaVO = productsFacade.findCategoriaByName(categoria);
			
		} catch (InstanceNotFoundException e1) {
			errors.add("identifier", 
					new ActionMessage("ErrorMessage.category.notFound"));
		}
		
          
        ProductoVO product = new ProductoVO(0, nombre, descripcion, 
        		precio, categoriaVO.getIdCategoria());		
		
		try {
		
			productsFacade.createProducto(product);
		
		} catch (DuplicateInstanceException e) {
			
			errors.add("idProducto", 
					new ActionMessage("ErrorMessages.id.alreadyExists"));
			
		}
		
        /* Return ActionForward. */
        if (errors.isEmpty()) {
        	
            return mapping.findForward("Success");
            
        } else {
        	
            saveErrors(request, errors);            
            return new ActionForward(mapping.getInput());
            
        }	
	}
}
