/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.actions;

import jgiraldez.j2ee.webgarden.http.controller.session.BillManager;
import jgiraldez.j2ee.webgarden.http.controller.session.SessionManager;
import jgiraldez.j2ee.webgarden.http.view.actionforms.BuyProductForm;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.UsersManagementFacadeDelegateFactory;

import java.io.IOException;

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
public class BuyProductAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, InternalErrorException {
		
		BuyProductForm buyForm = (BuyProductForm) form;
		
		ActionMessages errors = new ActionMessages();
		
		String NIF = SessionManager.getNIF(request);
		
		BillManager clientBill = BillManager.getInstance(NIF);

		int idProducto = buyForm.getIdProductoAsInt();
			
		ClienteVO cliente;
		boolean VIP = false;
		
		try {
			
			cliente = UsersManagementFacadeDelegateFactory.getDelegate().
													findCliente(NIF);
			
			VIP = cliente.getVIP();
			
		} catch (InstanceNotFoundException e) {
			errors.add("NIF", 
					new ActionMessage("ErrorMessages.client.notFound"));
		}  
		
		int product = 0;
		double precio = 0;
		ProductoVO producto;
		
		try {
			
			producto = ProductsManagementFacadeDelegateFactory.getDelegate().
													findProducto(idProducto);
			
			product = producto.getIdProducto();
			precio = producto.getPrecio();
			
		} catch (InstanceNotFoundException e) {
			errors.add("idProducto", 
					new ActionMessage("ErrorMessages.product.notFound"));
		}
		
		int cantidad = buyForm.getQuantityAsInt();
		
		double descuento = 0;
		double total;
		
		if (VIP) {
			descuento = 0.2;
			total = (cantidad * precio) -  (cantidad * precio * descuento);
		} else {
			total = cantidad * precio;
		}
		
		int num_linea;
		
		if (clientBill.getLines() == null) {
			num_linea = 1;
		} else {
			num_linea = clientBill.getLines().size() + 1;
		}
		
		Linea_FacturaVO line = new Linea_FacturaVO(num_linea, 0, product,
				cantidad, descuento, total);
		
		clientBill.addLine(line);
		
		return mapping.findForward("AddToShoppingCartSuccess");
        	
	}
}
