/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAO;
import jgiraldez.j2ee.webgarden.model.cliente.dao.SQLClienteDAOFactory;
import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyClienteAction implements TransactionalPlainAction {

	private String NIF;
	private String pila;
	private String ap1;
	private String ap2;
	private String email;
	private String dir;
	private int cod_postal;
	private int tlf;
	private String poblacion;
	private String provincia;
	private String dir_facturacion;
	private boolean VIP;
	
	public ModifyClienteAction(String NIF, String pila, String ap1, String ap2,
			String email, String dir, int cod_postal, int tlf, String poblacion,
			String provincia, String dir_facturacion, boolean VIP) {
	
		this.NIF = NIF;
		this.pila = pila;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.email = email;
		this.dir = dir;
		this.cod_postal = cod_postal;
		this.tlf = tlf;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.dir_facturacion = dir_facturacion;
		this.VIP = VIP;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLClienteDAO clienteDAO = SQLClienteDAOFactory.getDAO();
		
		ClienteVO cliente = clienteDAO.find(connection, NIF);
		cliente.setPila(pila);
		cliente.setAp1(ap1);
		cliente.setAp2(ap2);
		cliente.setEmail(email);
		cliente.setDir(dir);
		cliente.setCod_postal(cod_postal);
		cliente.setTlf(tlf);
		cliente.setPoblacion(poblacion);
		cliente.setProvincia(provincia);
		cliente.setDir_facturacion(dir_facturacion);
		cliente.setVIP(VIP);
		
		clienteDAO.update(connection, cliente);
		return null;
	}
}
