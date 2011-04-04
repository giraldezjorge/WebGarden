/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.struts.action.PropertyValidator;

/**
 * @author jorge
 *
 */
public class FindForm extends ActionForm {

	public final static String CATEGORY_IDENTIFIER = "CAT_ID";
	public final static String PRODUCT_IDENTIFIER = "PRO_ID";
	public final static String MAINTENANCE_IDENTIFIER = "MAI_ID";
    public final static String CLIENT_IDENTIFIER = "CLI_ID";
    public final static String FATHER_IDENTIFIER = "FAT_ID";
    public final static String NIF_IDENTIFIER = "NIF_ID";
    public final static String NAME_IDENTIFIER = "NAM_ID";
    public final static String BILL_IDENTIFIER = "BIL_ID";
    
    private final static Collection IDENTIFIER_TYPES = Arrays.asList(
    					new String[] {CATEGORY_IDENTIFIER, PRODUCT_IDENTIFIER,
    								MAINTENANCE_IDENTIFIER, CLIENT_IDENTIFIER, 
    								FATHER_IDENTIFIER, NIF_IDENTIFIER, 
    								NAME_IDENTIFIER, BILL_IDENTIFIER});
    
    private final static Collection IDENTIFIER_STRING_TYPES = Arrays.asList(
    					new String[] {CLIENT_IDENTIFIER, 
    								NIF_IDENTIFIER, NAME_IDENTIFIER, 
    								FATHER_IDENTIFIER});
	
	private String identifier;
	private int identifierAsInt;
	private String identifierType;
	private int startIndex;
    private int count;
	
	public FindForm() {
		reset();
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier.trim();
	}
	
	public String getIdentifierType() {
        return identifierType;
    }
    
    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

	public int getIdentifierAsInt() {
		return identifierAsInt;
	}
	
	public int getStartIndex() {
        return startIndex;
    }
    
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

	public ActionErrors validate(ActionMapping mapping, 
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if (!IDENTIFIER_STRING_TYPES.contains(identifierType)) {
			
			identifierAsInt = PropertyValidator.validateInt(errors, 
					"identifier", identifier, true, 1, Integer.MAX_VALUE);
			
		}
		
		PropertyValidator.validateMandatory(errors, "identifier", identifier);
		PropertyValidator.validateString(errors, 
				"identifierType", identifierType, true, IDENTIFIER_TYPES);
		
		return errors;
	}
	
	private void reset() {    
		identifier = null;
		identifierAsInt = 0;
		identifierType = null;
		startIndex = 1;
        count = 10;
    }
	
}
