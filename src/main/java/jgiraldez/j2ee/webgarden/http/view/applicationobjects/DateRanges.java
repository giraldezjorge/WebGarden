/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.applicationobjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author jorge
 *
 */
public class DateRanges {

	public final static int FIRST_DAY = 1;
    public final static int LAST_DAY = 31;
    public final static int FIRST_MONTH = 1;
    public final static int LAST_MONTH = 12;
    public final static int FIRST_YEAR = 1990;
    public final static int FIRST_HOUR = 00;
    public final static int LAST_HOUR = 23;
    public final static int FIRST_MINUTE = 00;
    public final static int LAST_MINUTE = 59;

    
    private List days;
    private List months;
    private List hours;
    private List minutes;
    private List nameDays;
    
    public DateRanges() {    
        days = getRange(FIRST_DAY, LAST_DAY);
        months = getRange(FIRST_MONTH, LAST_MONTH);
        hours = getRange(FIRST_HOUR, LAST_HOUR);
        minutes = getRange(FIRST_MINUTE, LAST_MINUTE);
        nameDays = getRange();
    }
    
    public List getDays() {
        return days;
    }
    
    public List getMonths() {
        return months;
    }
    
    public List getYears() {    
        return getRange(FIRST_YEAR, getLastYear());
    }
    
    public List getHours() {
    	return hours;
    }
    
    public List getMinutes() {
    	return minutes;
    }
    
    public List getNameDays() {
    	return nameDays;
    }
    
    public int getLastYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    private List getRange(int start, int end) {
    
        List range = new ArrayList();
        
        for (int i=start; i<=end; i++) {
            range.add(new Integer(i));
        }
        
        return range;
    
    }
    
    private List getRange() {
    	
    	List range = new ArrayList();
    	
    	range.add(new String("Lunes"));
    	range.add(new String("Martes"));
    	range.add(new String("Miercoles"));
    	range.add(new String("Jueves"));
    	range.add(new String("Viernes"));
    	
    	return range;
    }
    
}
