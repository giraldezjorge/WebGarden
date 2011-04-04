/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.delegate;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class PlanningFacadeDelegateFactory {

	private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "PlanningFacadeDelegateFactory/delegateClassName";

    private final static Class delegateClass = getDelegateClass();
    
    private PlanningFacadeDelegateFactory() {}
    
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
    
    public static PlanningFacadeDelegate getDelegate() 
    		throws InternalErrorException {
        
        try {
        	
            return (PlanningFacadeDelegate) delegateClass.newInstance();
            
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
