/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.BillManager;
import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class DeleteBillLineAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		String NIF = SessionManager.getNIF(request);
		
		BillManager clientBill = BillManager.getInstance(NIF);

		int num = Integer.parseInt(request.getParameter("num"));
		
		clientBill.deleteLine(num);
		
		return mapping.findForward("ShoppingCart");
        	
	}
}
