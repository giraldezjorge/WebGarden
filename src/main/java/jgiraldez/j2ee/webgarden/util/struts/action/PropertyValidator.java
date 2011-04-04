/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.struts.action;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * @author jorge
 *
 */
public final class PropertyValidator {

	private final static String INCORRECT_VALUE = 
		"ErrorMessages.incorrectValue";
	private final static String MANDATORY_FIELD = 
		"ErrorMessages.mandatoryField";
	private final static String INCORRECT_EMAIL_ADDRESS = 
		"ErrorMessages.emailAddress.incorrect";
	
	private PropertyValidator() {}

    public final static void validateEmailAddress(ActionErrors errors,
        String propertyName, String propertyValue) {

        if (PropertyValidator.validateMandatory(errors, 
        		propertyName, propertyValue)) {
            /**
             * Check that "propertyValue" contains the sign "@" surrounded by
             * at least one character.
             */
            int atSignIndex = propertyValue.indexOf("@");
            
            if ( (atSignIndex < 1) || 
                 (atSignIndex == propertyValue.length() - 1) ) {
                 errors.add(propertyName, 
                     new ActionMessage(INCORRECT_EMAIL_ADDRESS));
            }
            
        }
        
    }
	
	public final static int validateInt(ActionErrors errors, 
			String propertyName, String propertyValue, boolean mandatory, 
			int lowerValidLimit, int upperValidLimit) {
		
		int propertyValueAsInt = 0;
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			try {
				
				propertyValueAsInt = new Integer(propertyValue).intValue();
				if ( (propertyValueAsInt < lowerValidLimit) || 
					(propertyValueAsInt > upperValidLimit) ) {
					propertyValueIsCorrect = false;
				}
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
				errors.add(propertyName, new ActionMessage(INCORRECT_VALUE));
			}
		}
		
		return propertyValueAsInt;
	}
	
	public final static long validateLong(ActionErrors errors, 
			String propertyName, String propertyValue, boolean mandatory, 
			long lowerValidLimit, long upperValidLimit) {
		
		long propertyValueAsLong = 0;
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			
			try {
				
				propertyValueAsLong = new Long(propertyValue).longValue();
				
				if ( (propertyValueAsLong < lowerValidLimit) || 
					(propertyValueAsLong > upperValidLimit) ) {
					
					propertyValueIsCorrect = false;
					
				}
				
			} catch (NumberFormatException e) {
				propertyValueIsCorrect = false;				
			}
			
			if (!propertyValueIsCorrect) {
				errors.add(propertyName, new ActionMessage(INCORRECT_VALUE));
			}
			
		}
		
		return propertyValueAsLong;
		
	}
	
	public final static boolean validateMandatory(ActionErrors errors, 
			String propertyName, String propertyValue) {
		
		if ( (propertyValue == null) || (propertyValue.length() == 0) ) {
			errors.add(propertyName, new ActionMessage(MANDATORY_FIELD));
			return false;
		} else {
			return true;
		}
	}
	
	private static boolean validateMandatory(ActionErrors errors, 
			String propertyName, String propertyValue, boolean mandatory) {

		if (mandatory) {
			return validateMandatory(errors, propertyName, propertyValue);
		} else {
			return true;
		}
	}
	
	public final static double validateDouble(ActionErrors errors, 
			String propertyName, String propertyValue, boolean mandatory, 
			double lowerValidLimit, double upperValidLimit, Locale locale) {
		
		double propertyValueAsDouble = 0;
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			boolean propertyValueIsCorrect = true;
			
			try {
				
				NumberFormat numberFormatter = 
					NumberFormat.getNumberInstance(locale);
				propertyValueAsDouble = 
					numberFormatter.parse(propertyValue).doubleValue();
				if ( (propertyValueAsDouble < lowerValidLimit) || 
					(propertyValueAsDouble > upperValidLimit) ) {
					
					propertyValueIsCorrect = false;
					
				}
				
			} catch (ParseException e) {
				propertyValueIsCorrect = false;
			}
			
			if (!propertyValueIsCorrect) {
			
				errors.add(propertyName, new ActionMessage(INCORRECT_VALUE));
				
			}
			
		}
		
		return propertyValueAsDouble;
		
	}
	
	public final static void validateString(ActionErrors errors, 
			String propertyName, String propertyValue,
			boolean mandatory, Collection validValues) {
		
		if (validateMandatory(errors, propertyName, propertyValue, mandatory)) {
			
			if (!validValues.contains(propertyValue)) {
				
				errors.add(propertyName, new ActionMessage(INCORRECT_VALUE));
			}
		}
	    
	}
	
}
