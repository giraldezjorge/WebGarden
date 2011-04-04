/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.struts.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public abstract class DefaultAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		try {
			
			return doExecute(mapping, form, request, response);
			
		} catch (Exception e) {
			return doOnException(mapping, form, request, response, e);
		}
	}
	
	protected abstract ActionForward doExecute(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException;
	
	protected ActionForward doOnException(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse response, Exception exception) 
			throws IOException, ServletException {
		
		ServletContext servletContext = servlet.getServletConfig().
			getServletContext();
		servletContext.log(exception.getMessage(), exception);
		
		return mapping.findForward("InternalError");
		
	}
	
}
