/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.http.view.actionforms.ViewForm;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.administrationfacade.vo.FacturaChunkVO;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
		UsersManagementFacadeDelegateFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class ViewBillsAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
    		
		ViewForm viewForm = (ViewForm) form;
		
		/* Do action. */
    	return doGetBills(mapping, viewForm.getStartIndex(), 
    			viewForm.getCount(), request);
    }
    
    private ActionForward doGetBills(ActionMapping mapping, 
    		int startIndex, int count, HttpServletRequest request) 
    		throws InternalErrorException {
    	
    	/* Find bills. */
    	FacturaChunkVO facturaChunkVO = null;
		try {
			facturaChunkVO = AdministrationFacadeDelegateFactory.getDelegate().
    			findAllFacturas(startIndex, count);
		} catch (InstanceNotFoundException e) {
			
			ActionMessages errors = new ActionMessages();            
            errors.add("identifier", 
            		new ActionMessage("ErrorMessages.bill.notFound"));
            saveErrors(request, errors);
		}  					
    	
    	if (facturaChunkVO.getFacturaVOs().size() > 0) {
    		request.setAttribute("facturas", 
    				facturaChunkVO.getFacturaVOs());
    	}
    	
    	/* Generate parameters for previous and next links. */
    	Map previousLinkParameters = 
    		getPreviousLinkParameters(startIndex, count);
    	if (previousLinkParameters != null) {
    		request.setAttribute("previous", previousLinkParameters);
    	}
    	
    	if (facturaChunkVO.getExistMoreFacturas()) {
    		Map nextLinkParameters = getNextLinkParameters(startIndex, count);        
    		request.setAttribute("next", nextLinkParameters);
    	}
    	
    	/* Return ActionForward. */
    	return mapping.findForward("ViewBills");
    }
    
    private Map getPreviousLinkParameters(int startIndex, int count) {        
    	
    	Map linkAttributes = null;
    	
    	if ( (startIndex-count) > 0 ) {
    		linkAttributes = 
    			getCommonPreviousNextLinkParameters(startIndex, count);
    		linkAttributes.put("startIndex", new Integer(startIndex-count));
    	}
    	
    	return linkAttributes;
    }
    
    private Map getNextLinkParameters(int startIndex, int count) {        
    	
    	Map linkAttributes = 
    		getCommonPreviousNextLinkParameters(startIndex, count);
    	
    	linkAttributes.put("startIndex", new Integer(startIndex+count));
    	
    	return linkAttributes;
    }
    
    private Map getCommonPreviousNextLinkParameters(int startIndex, int count) {
    	
    	Map linkAttributes = new HashMap();
    	
    	linkAttributes.put("count", new Integer(count));
    	
    	return linkAttributes;
    }
}
