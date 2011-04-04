/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions;

import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAO;
import jgiraldez.j2ee.webgarden.model.producto.dao.SQLProductoDAOFactory;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.TransactionalPlainAction;

/**
 * @author jorge
 *
 */
public class ModifyProductoAction implements TransactionalPlainAction {

	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int categoria;
	
	public ModifyProductoAction(int idProducto, String nombre, 
			String descripcion, double precio, int categoria) {
		
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Object execute(Connection connection) 
			throws InstanceNotFoundException, InternalErrorException {
		
		SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
		
		ProductoVO producto = productoDAO.find(connection, idProducto);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setCategoria(categoria);
		
		productoDAO.update(connection, producto);
		return null;
		
	}
	
}
