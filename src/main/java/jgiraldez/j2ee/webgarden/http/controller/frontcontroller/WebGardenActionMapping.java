/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.frontcontroller;

import org.apache.struts.action.ActionMapping;

/**
 * @author jorge
 *
 */
public class WebGardenActionMapping extends ActionMapping {

	private boolean authenticationRequired;
	private boolean employeeRequired;
	private boolean administrationRequired;

    public WebGardenActionMapping() {
    	
        authenticationRequired = false;
        employeeRequired = false;
        administrationRequired = false;
        
    }
    
    public boolean getAuthenticationRequired() {
        return authenticationRequired;
    }
    
    public void setAuthenticationRequired(boolean authenticationRequired) {
        this.authenticationRequired = authenticationRequired;
    }
    
    public boolean getAdministrationRequired() {
		return administrationRequired;
	}

	public void setAdministrationRequired(boolean administrationRequired) {
		this.administrationRequired = administrationRequired;
	}
    
	public boolean getEmployeeRequired() {
		return employeeRequired;
	}

	public void setEmployeeRequired(boolean employeeRequired) {
		this.employeeRequired = employeeRequired;
	}

}
