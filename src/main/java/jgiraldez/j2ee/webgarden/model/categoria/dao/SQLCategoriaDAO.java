/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.categoria.dao;

import java.sql.Connection;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

/**
 * @author jorge
 *
 */
public interface SQLCategoriaDAO {
	
	public CategoriaVO create(Connection connection, CategoriaVO categoria) 
			throws DuplicateInstanceException, InternalErrorException;
	public boolean exists(Connection connection, int idCategoria) 
			throws InternalErrorException;
	public CategoriaVO find(Connection connection, int idCategoria) 
			throws InternalErrorException, InstanceNotFoundException;
	public CategoriaVO findByName(Connection connection, String name) 
			throws InstanceNotFoundException, InternalErrorException;
	public void update(Connection connection, CategoriaVO categoria) 
			throws InstanceNotFoundException, InternalErrorException;
	public void remove(Connection connection, int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<CategoriaVO> getSons(Connection connection, int padre, 
			int startIndex, int count) throws InternalErrorException;
	public List<ProductoVO> getProductos(Connection connection, 
			int idCategoria, int startIndex, int count ) 
			throws InstanceNotFoundException, InternalErrorException;
	public List<CategoriaVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException;
	
}
