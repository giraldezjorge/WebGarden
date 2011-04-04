/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import jgiraldez.j2ee.webgarden.util.struts.action.PropertyValidator;

/**
 * @author jorge
 *
 */
public class RegisterClientForm extends ActionForm {

    private String loginName;
    private String NIF;
    private String password;
    private String retypePassword;
    private String firstName;
    private String surname1;
    private String surname2;
    private String email;
    private String dir;
    private String cod_postal;
    private int cod_postalAsInt;
    private String tlf;
    private int tlfAsInt;
	private String poblacion;
	private String provincia;
	private String dir_facturacion;
	private boolean VIP;
    
    public RegisterClientForm() {
        reset();
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName.trim();
    }
    
    public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getCod_postal() {
		return cod_postal;
	}
    
    public int getCod_postalAsInt() {
    	return cod_postalAsInt;
    }

	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal.trim();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir.trim();
	}

	public String getDir_facturacion() {
		return dir_facturacion;
	}

	public void setDir_facturacion(String dir_facturacion) {
		this.dir_facturacion = dir_facturacion.trim();
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion.trim();
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia.trim();
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1.trim();
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2.trim();
	}

	public String getTlf() {
		return tlf;
	}
	
	public int getTlfAsInt() {
		return tlfAsInt;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf.trim();
	}

	public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password.trim();
    }
    
    public String getRetypePassword() {
        return retypePassword;
    }
    
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword.trim();
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }
    
    public boolean isVIP() {
		return VIP;
	}

	public void setVIP(boolean vIP) {
		VIP = vIP;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        PropertyValidator.validateMandatory(errors, "loginName", loginName);
        PropertyValidator.validateMandatory(errors, "NIF", NIF);
        boolean validatePassword = PropertyValidator.validateMandatory(errors, 
        		"password", password);
        validatePassword = validatePassword && PropertyValidator.
        		validateMandatory(errors, "retypePassword", retypePassword);
        if (validatePassword && !password.equals(retypePassword)) {
        	
        	errors.add("password", 
        		new ActionMessage("ErrorMessages.password.doNotMatch"));
        	
        }
        
        cod_postalAsInt = PropertyValidator.validateInt(errors, 
        		"cod_postal", cod_postal, true, 1, Integer.MAX_VALUE);
        tlfAsInt = PropertyValidator.validateInt(errors, 
        		"tlf", tlf, true, 1, Integer.MAX_VALUE);
        
        PropertyValidator.validateMandatory(errors, "firstName", firstName);
        PropertyValidator.validateMandatory(errors, "surname1", surname1);
        PropertyValidator.validateMandatory(errors, "surname2", surname2);
        PropertyValidator.validateMandatory(errors, "dir", dir);
        PropertyValidator.validateMandatory(errors, "poblacion", poblacion);
        PropertyValidator.validateMandatory(errors, "provincia", provincia);
        PropertyValidator.validateMandatory(errors, 
        		"dir_facturacion", dir_facturacion);
        PropertyValidator.validateEmailAddress(errors, "email", email);

        return errors;
        
    }

    private void reset() {
    	
        loginName = null;
        NIF = null;
        password = null;
        retypePassword = null;
        firstName = null;
        surname1 = null;
        surname2 = null;
        email = null;
        dir = null;
        cod_postal = null;
        cod_postalAsInt = 0;
        tlf = null;
        tlfAsInt = 0;
        poblacion = null;
        provincia = null;
        dir_facturacion = null;
        VIP = false;
    }
    
} // class
