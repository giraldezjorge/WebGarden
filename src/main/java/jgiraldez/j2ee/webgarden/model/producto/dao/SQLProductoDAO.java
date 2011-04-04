/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.producto.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

/**
 * @author jorge
 *
 */
public interface SQLProductoDAO {
	
	public ProductoVO create(Connection connection, ProductoVO producto) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, int idProducto) 
			throws InternalErrorException;
	public ProductoVO find(Connection connection, int idProducto) 
			throws InstanceNotFoundException, InternalErrorException; 
	public ProductoVO findByName(Connection connection, String nombre) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, ProductoVO producto) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, int idProducto) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<ProductoVO> findByCategoria(Connection connection, 
			int categoria, int startIndex, int count) 
			throws InternalErrorException;
	public List<ProductoVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException;
	
}
