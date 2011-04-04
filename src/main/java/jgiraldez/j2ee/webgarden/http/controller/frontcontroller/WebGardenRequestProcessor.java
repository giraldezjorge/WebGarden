/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;

/**
 * @author jorge
 *
 */
public class WebGardenRequestProcessor extends TilesRequestProcessor {

	PreProcessingFilter firstPreProcessingFilter;

    public WebGardenRequestProcessor() {
    
        firstPreProcessingFilter = 
        	new SessionPreProcessingFilter(
        	new AuthenticationPreProcessingFilter(
        	new EmployeePreProcessingFilter(
        	new AdministrationPreProcessingFilter(null))));
            
    }

    protected ActionForward processActionPerform(HttpServletRequest request, 
    		HttpServletResponse response, Action action, ActionForm form, 
    		ActionMapping mapping) throws IOException, ServletException {
        
        ActionForward actionForward = firstPreProcessingFilter.process(
            request, response, action, form, mapping);
            
        if (actionForward == null) {
            return super.processActionPerform(request, response, action,
                form, mapping);
        } else {
            return actionForward;
        }
                
    }
}
