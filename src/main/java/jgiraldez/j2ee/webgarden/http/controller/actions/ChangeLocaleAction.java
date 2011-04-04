/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class ChangeLocaleAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, InternalErrorException {
		
		String localeKey = request.getParameter("locale");
		HttpSession session = request.getSession(true);
		String[] a = localeKey.split("_");
		String languaje = "";
		String country = "";
		
		if (a.length > 0) {
			languaje = a[0];
			session.setAttribute("org.apache.struts.action.LOCALE",
					new Locale(languaje));
		}
		
		if (a.length > 1) {
			country = a[1];
			session.setAttribute("org.apache.struts.action.LOCALE",
					new Locale(languaje, country));
		}
		
		return mapping.findForward("Welcome");
	}

	
}
