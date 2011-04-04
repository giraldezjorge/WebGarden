/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.frontcontroller;

import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;

import java.io.IOException;

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
public class SessionPreProcessingFilter extends PreProcessingFilter {

	public SessionPreProcessingFilter(PreProcessingFilter nextFilter) {
        super(nextFilter);
    }

    protected ActionForward doProcess(HttpServletRequest request, 
    		HttpServletResponse response, Action action, 
    		ActionForm form, ActionMapping mapping) 
    		throws IOException, ServletException, InternalErrorException {
            
        SessionManager.touchSession(request);

        return null;
    }
    
}
