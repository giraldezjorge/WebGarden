/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.view.actionforms.RemoveForm;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
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
public class RemoveProductAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {    	

    	/* Get data. */
    	RemoveForm removeForm = (RemoveForm) form;
    	int identifier = removeForm.getIdentifierAsInt();
    	String identifierType = removeForm.getIdentifierType();
    	String identifierAsString = removeForm.getIdentifier();
    	
    	/* Do action. */
    	if (!RemoveForm.NAME_IDENTIFIER.equals(identifierType)) {
        
    		return doRemoveProduct(mapping, identifier, request);
    		
    	} else {
    		
    		return doRemoveProductByName(mapping, identifierAsString, request);
    		
    	}
           
    }

	private ActionForward doRemoveProduct(ActionMapping mapping, 
			int identifier, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            ProductsManagementFacadeDelegateFactory.getDelegate().
            	removeProducto(identifier);
            
            return mapping.findForward("RemoveSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.product.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
	private ActionForward doRemoveProductByName(ActionMapping mapping, 
			String identifierAsString, HttpServletRequest request) 
			throws InternalErrorException {
    	
    	try {        
            
            ProductoVO productoVO = 
            	ProductsManagementFacadeDelegateFactory.getDelegate().
            		findProductoByName(identifierAsString);
            int id = productoVO.getIdProducto();
            
            ProductsManagementFacadeDelegateFactory.getDelegate().
            	removeProducto(id);
            
            return mapping.findForward("RemoveSuccess");
                    
        } catch (InstanceNotFoundException e) {
            
            ActionMessages errors = new ActionMessages();
            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.product.notFound"));
            saveErrors(request, errors);
            
            return new ActionForward(mapping.getInput());
            
        }
    }
	
}// class
