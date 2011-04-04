/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RegisterCategoryForm;
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

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class RegisterCategoryAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		ActionMessages errors = new ActionMessages();
		
		// get data
		RegisterCategoryForm categoryForm = (RegisterCategoryForm) form;
//		int idCategoria = categoryForm.getIdCategoriaAsInt();
		String nombre = categoryForm.getNombre();
//		int padre = categoryForm.getPadreAsInt();
		String padre = categoryForm.getPadre();
		
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
		
		int idPadre = 0;
		
		try {
			
			CategoriaVO categoria = productsFacade.findCategoriaByName(padre);
			idPadre = categoria.getIdCategoria();
			
		} catch (InstanceNotFoundException e) {
			errors.add("idCategoria", 
					new ActionMessage("ErrorMessages.category.notFound"));
		}
		
        /* Register category. */                    
        
        CategoriaVO category = new CategoriaVO(0, nombre, idPadre); 
		
		try {
			
			productsFacade.createCategoria(category);
			
		} catch (DuplicateInstanceException e) {
			
			errors.add("idCategoria", 
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
