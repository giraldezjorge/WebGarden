/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions;

import jgiraldez.j2ee.webgarden.util.exceptions.ModelException;

/**
 * @author jorge
 *
 */
public class IncorrectPasswordException extends ModelException {

	private String loginName;
    
    public IncorrectPasswordException(String loginName) {
        super("Incorrect password exception => loginName = " + loginName);
        this.loginName = loginName;
    }
    
    public String getLoginName() {
        return loginName;
    }
}
