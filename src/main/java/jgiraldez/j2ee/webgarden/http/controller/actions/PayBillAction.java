/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
		AdministrationFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
		AdministrationFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;

import java.io.IOException;
import java.sql.Timestamp;

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
public class PayBillAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		ActionMessages errors = new ActionMessages();						

		int idFactura = Integer.parseInt(request.getParameter("id"));
		
		FacturaVO factura;
		
		AdministrationFacadeDelegate adminFacade = 
			AdministrationFacadeDelegateFactory.getDelegate();
	
		boolean pagada = true;
		
		try {
			
			factura = adminFacade.findFactura(idFactura);
			
			Timestamp fecha = factura.getFecha();
			
			adminFacade.modifyFactura(idFactura, fecha, pagada, 
					SessionManager.getNIF(request));
			
		} catch (InstanceNotFoundException e) {
			errors.add("idFactura", 
					new ActionMessage("ErrorMessages.bill.notFound"));
		}  
		
		return mapping.findForward("Success");
        	
	}
}
