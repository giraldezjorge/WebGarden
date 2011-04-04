/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import jgiraldez.j2ee.webgarden.http.view.applicationobjects.DateRanges;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jgiraldez.j2ee.webgarden.util.struts.action.PropertyValidator;

/**
 * @author jorge
 *
 */
public class EditMaintenanceDetailsForm extends ActionForm {

	private String idMantenimiento;
	private int idMantenimientoAsInt;
	private String lugar;
	private String descripcion;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minutes;
	private String nameDay;
	
	public EditMaintenanceDetailsForm() {
		reset();
	}
	
	public String getIdMantenimiento() {
		return this.idMantenimiento;
	}
	
	public int getIdMantenimientoAsInt() {
		return this.idMantenimientoAsInt;
	}
	
	public void setIdMantenimiento(String idMantenimiento) {
		this.idMantenimiento = idMantenimiento.trim();
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public String getHour() {
		return this.hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getMinutes() {
		return this.minutes;
	}
	
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getNameDay() {
		return nameDay;
	}

	public void setNameDay(String nameDay) {
		this.nameDay = nameDay;
	}
	
	public Timestamp getTimestamp() {
		
		if (this.month.length() == 1) {
			this.month = "0" + this.month;
		}
		
		if (this.day.length() == 1) {
			this.day = "0" + this.day; 
		}
		
		if (this.hour.length() == 1) {
			this.hour = "0" + this.hour;
		}
		
		if (this.minutes.length() == 1) {
			this.minutes = "0" + this.minutes;
		}
		
		return Timestamp.valueOf(this.year + "-" + this.month + "-" +
				this.day + " " + this.hour + ":" + this.minutes+ ":00");
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping, 
    		HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        DateRanges dateRanges = (DateRanges) getServlet().getServletConfig().
        	getServletContext().getAttribute("dateRanges");

        idMantenimientoAsInt = 
        	new Integer(PropertyValidator.validateInt(errors, 
        	"idMantenimiento", idMantenimiento, true, 0, Integer.MAX_VALUE));
        
        PropertyValidator.validateInt(errors, 
        		"day", day, true, DateRanges.FIRST_DAY, DateRanges.LAST_DAY);
        PropertyValidator.validateInt(errors, 
        		"month", month, true, 
        		DateRanges.FIRST_MONTH, DateRanges.LAST_MONTH);
        PropertyValidator.validateInt(errors, 
        		"year", year, true, 
        		DateRanges.FIRST_YEAR, dateRanges.getLastYear());
        PropertyValidator.validateInt(errors, 
        		"hour", hour, true, 
        		DateRanges.FIRST_HOUR, DateRanges.LAST_HOUR);
        PropertyValidator.validateInt(errors, 
        		"minutes", minutes, true, 
        		DateRanges.FIRST_MINUTE, DateRanges.LAST_MINUTE);
        PropertyValidator.validateMandatory(errors, "nameDay", nameDay);
        PropertyValidator.validateMandatory(errors, "lugar", lugar);
        PropertyValidator.validateMandatory(errors, "descripcion", descripcion);
        
        return errors;
        
    }

    private void reset() {
    
    	/* Get current date. */
        Calendar calendar = Calendar.getInstance();
    	
        /* Set default values. */
        idMantenimiento = null;
        idMantenimientoAsInt = 0;
        day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        month = Integer.toString(
        		calendar.get(Calendar.MONTH) - Calendar.JANUARY + 1);
        year = Integer.toString(calendar.get(Calendar.YEAR));
        hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        minutes = Integer.toString(calendar.get(Calendar.MINUTE));
        nameDay = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK));
        lugar = null;
        descripcion = null;
                    
    }
}
