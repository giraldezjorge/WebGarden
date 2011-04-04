/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RemoveForm;
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
public class RemoveCategoryAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {    	

    	/* Get data. */
    	RemoveForm removeForm = (RemoveForm) form;
    	String identifier = removeForm.getIdentifier();
    	
        return doRemoveCategory(mapping, identifier, request);
           
    }

	private ActionForward doRemoveCategory(ActionMapping mapping, 
			String identifier, HttpServletRequest request) 
			throws InternalErrorException {
    	
		ProductsManagementFacadeDelegate productsFacade = 
			ProductsManagementFacadeDelegateFactory.getDelegate();
		
    	try {        
            
    		CategoriaVO categoriaVO = 
    			productsFacade.findCategoriaByName(identifier);
    		
            productsFacade.removeCategoria(categoriaVO.getIdCategoria());
            
            return mapping.findForward("RemoveSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.category.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
}
