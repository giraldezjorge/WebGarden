/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class LoginResultVO implements Serializable {
	
	private String firstName;
    private String encryptedPassword;
    private String NIF;
    private String type;

    public LoginResultVO(String firstName, String encryptedPassword, 
    		String NIF, String type) {
        
    	this.NIF = NIF;
        this.firstName = firstName;
        this.encryptedPassword = encryptedPassword;
        this.type = type;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
    
    public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getType() {
        return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String toString() {
        return new String("firstName = " + firstName + " | " +
            "encryptedPassword = " + encryptedPassword + " | " +
            "NIF = " + NIF + " | " + "type = " + type);
    }
}
