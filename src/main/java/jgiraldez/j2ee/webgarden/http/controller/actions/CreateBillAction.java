/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.BillManager;
import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.administrationfacade.delegate.
	AdministrationFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class CreateBillAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		ActionMessages errors = new ActionMessages();
		
		String NIF = SessionManager.getNIF(request);
		
    	BillManager clientBill = BillManager.getInstance(NIF);
    	
    	try {
    		
    		Timestamp fecha = 
    			new Timestamp(Calendar.getInstance().getTimeInMillis());
    		boolean pagada = false;
    		String cliente = NIF;
    		
    		FacturaVO factura = new FacturaVO(0, fecha, pagada, cliente);
    		
    		AdministrationFacadeDelegate adminFacade =
    			AdministrationFacadeDelegateFactory.getDelegate();
    		factura = adminFacade.createFactura(factura);
    	
    		System.out.println(factura.getIdFactura());
    		Collection<Linea_FacturaVO> lines = clientBill.getLines();
//        	Iterator<Linea_FacturaVO> iter = lines.iterator();
//        	Linea_FacturaVO lf = null;
        	ArrayList<Linea_FacturaVO> al = 
        		new ArrayList<Linea_FacturaVO>(lines);
        		
        	for (int i = 0; i<al.size(); i++) {
        		
        		al.get(i).setFactura(factura.getIdFactura());
        		adminFacade.createLinea_Factura(al.get(i));
        		clientBill.deleteLine(al.get(i).getNum_linea());
        		
        	}
        	
//        	while (iter.hasNext()) {
//        		lf = iter.next();
//        		System.out.println(lf);
//        		lf.setFactura(factura.getIdFactura());
//        		
//        		
//        		adminFacade.createLinea_Factura(lf);
//        		clientBill.deleteLine(lf.getNum_linea());
//        	}        	        	
        	
    	} catch (DuplicateInstanceException e) {
    		errors.add("idFactura", 
    				new ActionMessage("ErrorMessages.id.alreadyExist"));
    	}
    	
    	return mapping.findForward("Success");
    }
}
