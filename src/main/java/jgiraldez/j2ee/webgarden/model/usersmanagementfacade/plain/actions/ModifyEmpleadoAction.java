/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAO;
import jgiraldez.j2ee.webgarden.model.empleado.dao.SQLEmpleadoDAOFactory;
import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyEmpleadoAction implements TransactionalPlainAction {

	private String NIF;
	private String pila;
	private String ap1;
	private String ap2;
	private String dir;
	private int cod_postal;
	private int tlf;
	private String poblacion;
	private String provincia;
	private long num_cuenta;
	private boolean esAdmin;
	
	public ModifyEmpleadoAction(String NIF, String pila, String ap1, String ap2, 
			String dir, int cod_postal, int tlf, String poblacion, 
			String provincia, long num_cuenta, boolean esAdmin) {
	
		this.NIF = NIF;
		this.pila = pila;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.dir = dir;
		this.cod_postal = cod_postal;
		this.tlf = tlf;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.num_cuenta = num_cuenta;
		this.esAdmin = esAdmin;
	}
	
	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLEmpleadoDAO empleadoDAO = SQLEmpleadoDAOFactory.getDAO();
		
		EmpleadoVO empleado = empleadoDAO.find(connection, NIF);
		empleado.setPila(pila);
		empleado.setAp1(ap1);
		empleado.setAp2(ap2);
		empleado.setDir(dir);
		empleado.setCod_postal(cod_postal);
		empleado.setTlf(tlf);
		empleado.setPoblacion(poblacion);
		empleado.setProvincia(provincia);
		empleado.setNum_cuenta(num_cuenta);
		empleado.setEsAdmin(esAdmin);
		
		empleadoDAO.update(connection, empleado);
		return null;
	}
	
}
