/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;

/**
 * @author jorge
 *
 */
public abstract class DefaultActionForm extends ActionForm {

    protected Locale getLocale(HttpServletRequest request) {
    
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            return Locale.getDefault();
        }
        
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        
        if (locale == null) {
            return Locale.getDefault();
        } else {
            return locale;
        }
    
    }
}
