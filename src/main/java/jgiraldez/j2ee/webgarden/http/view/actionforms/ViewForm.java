/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author jorge
 *
 */
public class ViewForm extends ActionForm {

	private int startIndex;
	private int count;
	
	public ViewForm() {
		reset();
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
	
	private void reset() {
		startIndex = 1;
		count = 10;
	}
}
