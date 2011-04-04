/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class UsersManagementFacadeDelegateFactory {

	private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "UsersManagementFacadeDelegateFactory/delegateClassName";

    private final static Class delegateClass = getDelegateClass();
    
    private UsersManagementFacadeDelegateFactory() {}
    
    private static Class getDelegateClass() {
    
        Class theClass = null;
    
        try {
        
            String delegateClassName = ConfigurationParametersManager.
            	getParameter(DELEGATE_CLASS_NAME_PARAMETER);
            System.out.println(delegateClassName);
            theClass = Class.forName(delegateClassName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return theClass;
    }
    
    public static UsersManagementFacadeDelegate getDelegate() 
    		throws InternalErrorException {
        
        try {
        	if (delegateClass == null) {
        		System.out.println("delegateClass nulo");
        		System.out.flush();
        	}
            return (UsersManagementFacadeDelegate) delegateClass.newInstance();
            
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
