/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate;

import jgiraldez.j2ee.webgarden.util.configuration.
	ConfigurationParametersManager;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public final class ProductsManagementFacadeDelegateFactory {

	private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "ProductsManagementFacadeDelegateFactory/delegateClassName";
	
    private final static Class delegateClass = getDelegateClass();
    
    private ProductsManagementFacadeDelegateFactory() {}
    
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
    
    public static ProductsManagementFacadeDelegate getDelegate() 
    		throws InternalErrorException {
        
        try {
        	
            return (ProductsManagementFacadeDelegate) delegateClass.
            		newInstance();
            
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
