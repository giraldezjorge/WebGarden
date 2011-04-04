/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.EditCategoryDetailsForm;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
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

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class EditCategoryDetailsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		// print data
		EditCategoryDetailsForm categoryForm = (EditCategoryDetailsForm) form;
				
		ActionMessages errors = new ActionMessages();
		
		CategoriaVO categoria;

		int idCategoria = Integer.parseInt(request.getParameter("id"));
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		try {
			
			categoria = productsFacade.findCategoria(idCategoria);
			String padre = 
				productsFacade.findCategoria(categoria.getPadre()).getNombre();
			categoryForm.setIdCategoria(Integer.toString(
					categoria.getIdCategoria()));
			categoryForm.setNombre(categoria.getNombre());
			categoryForm.setPadre(padre);
			
		} catch (InstanceNotFoundException e) {
			
			errors.add("idCategoria", 
					new ActionMessage("ErrorMessages.category.notFound"));
			
		}
		
		return mapping.findForward("UpdateCategoryDetailsForm");
        	
	}
}
