/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.BillManager;
import jgiraldez.j2ee.webgarden.http.controller.session.PrintManager;
import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;

import java.io.IOException;
import java.util.ArrayList;
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

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.struts.action.DefaultAction;

/**
 * @author jorge
 *
 */
public class ShoppingCartAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		ActionMessages errors = new ActionMessages();
		
		String NIF = SessionManager.getNIF(request);
		
		BillManager clientBill = BillManager.getInstance(NIF);
		Collection<Linea_FacturaVO> lines = clientBill.getLines();
		
		
		ArrayList<Linea_FacturaVO> al = new ArrayList<Linea_FacturaVO>(lines);
    	ArrayList<PrintManager> collection = new ArrayList<PrintManager>();
    	
    	for (int i = 0; i<al.size(); i++) {
    		
    		PrintManager pm = new PrintManager();
    		
    		pm.setNum_linea(al.get(i).getNum_linea());
    		pm.setFactura(al.get(i).getFactura());
    		pm.setCantidad(al.get(i).getCantidad());
    		pm.setDescuento(al.get(i).getDescuento());
    		pm.setTotal(al.get(i).getTotal());
    		
    		try {
    			
				String name = 
					ProductsManagementFacadeDelegateFactory.getDelegate().
					findProducto(al.get(i).getProducto()).getNombre();
				pm.setProductName(name);
				collection.add(pm);
				
			} catch (InstanceNotFoundException e) {
				errors.add("identifier", 
						new ActionMessage("ErrorMessage.id.notFound"));
			}
    	}     
		
    	request.setAttribute("printManager", collection);
    	
		return mapping.findForward("ShoppingCart");
        	
	}
}
