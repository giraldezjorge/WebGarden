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
public class EditEmployeeDetailsForm extends ActionForm {

    private String NIF;
    private String firstName;
    private String surname1;
    private String surname2;
    private String dir;
    private String cod_postal;
    private int cod_postalAsInt;
    private String tlf;
    private int tlfAsInt;
	private String poblacion;
	private String provincia;
	private String num_cuenta;
	private long num_cuentaAsLong;
	private boolean esAdmin;
    
    public EditEmployeeDetailsForm() {
        reset();
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir.trim();
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

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }
    
    public boolean getEsAdmin() {
		return this.esAdmin;
	}

	public void setEsAdmin(boolean admin) {
		this.esAdmin = admin;
	}	
	
	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String numCuenta) {
		num_cuenta = numCuenta;
	}
	
	public long getNum_cuentaAsLong() {
	    	return num_cuentaAsLong;
	    }

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        PropertyValidator.validateMandatory(errors, "NIF", NIF);
        
        cod_postalAsInt = PropertyValidator.validateInt(errors, 
        		"cod_postal", cod_postal, true, 1, Integer.MAX_VALUE);
        tlfAsInt = PropertyValidator.validateInt(errors, 
        		"tlf", tlf, true, 1, Integer.MAX_VALUE);
        num_cuentaAsLong = PropertyValidator.validateLong(errors, 
        		"num_cuenta", num_cuenta, true, 1, Integer.MAX_VALUE);
        
        PropertyValidator.validateMandatory(errors, "firstName", firstName);
        PropertyValidator.validateMandatory(errors, "surname1", surname1);
        PropertyValidator.validateMandatory(errors, "surname2", surname2);
        PropertyValidator.validateMandatory(errors, "dir", dir);
        PropertyValidator.validateMandatory(errors, "poblacion", poblacion);
        PropertyValidator.validateMandatory(errors, "provincia", provincia);       

        return errors;
        
    }

    private void reset() {
    	
        NIF = null;
        firstName = null;
        surname1 = null;
        surname2 = null;        
        dir = null;
        cod_postal = null;
        cod_postalAsInt = 0;
        tlf = null;
        tlfAsInt = 0;
        poblacion = null;
        provincia = null;
        num_cuenta = null;
        num_cuentaAsLong = 0;
        esAdmin = false;
    }
}
