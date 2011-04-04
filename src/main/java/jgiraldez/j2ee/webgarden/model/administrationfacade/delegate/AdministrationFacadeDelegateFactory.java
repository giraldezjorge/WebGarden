/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.delegate;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class AdministrationFacadeDelegateFactory {

	private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "AdministrationFacadeDelegateFactory/delegateClassName";

    private final static Class delegateClass = getDelegateClass();
    
    private AdministrationFacadeDelegateFactory() {}
    
    private static Class getDelegateClass() {
    
        Class theClass = null;
    
        try {
        
            String delegateClassName = ConfigurationParametersManager.
            	getParameter(DELEGATE_CLASS_NAME_PARAMETER);
            theClass = Class.forName(delegateClassName);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }
        
        return theClass;
        
    }
    
    public static AdministrationFacadeDelegate getDelegate() 
    	throws InternalErrorException {
        
        try {
        	
            return (AdministrationFacadeDelegate) delegateClass.newInstance();
            
        } catch (Exception e) {
        	
            throw new InternalErrorException(e);
            
        }
    }
}
