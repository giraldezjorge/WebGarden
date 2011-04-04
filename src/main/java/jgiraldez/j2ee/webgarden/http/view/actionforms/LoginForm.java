/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.struts.action.PropertyValidator;

/**
 * @author jorge
 *
 */
public class LoginForm extends ActionForm {

	private String loginName;
    private String password;
    private boolean rememberMyPassword;
    private String loginBy;
    
    public LoginForm() {
        reset();
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName.trim();
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getRememberMyPassword() {
        return rememberMyPassword;
    }
    
    public void setRememberMyPassword(boolean rememberMyPassword) {
        this.rememberMyPassword = rememberMyPassword;
    }
    
    public String getLoginBy() {
		return loginBy;
	}

	public void setLoginBy(String loginBy) {
		this.loginBy = loginBy;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();

        PropertyValidator.validateMandatory(errors, "loginName", loginName);
        PropertyValidator.validateMandatory(errors, "password", password);
        PropertyValidator.validateMandatory(errors, "loginBy", loginBy);
        
        return errors;
        
    }
    
    private void reset() {
        loginName = null;
        password = null;
        rememberMyPassword = false;
    }
}
