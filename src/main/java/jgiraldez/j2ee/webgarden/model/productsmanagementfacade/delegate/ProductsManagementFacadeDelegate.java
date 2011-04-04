/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	CategoriaChunkVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	ProductoChunkVO;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;

/**
 * @author jorge
 *
 */
public interface ProductsManagementFacadeDelegate {

	// OPERACIONES CONN EL DAO DE CATEGORIA
	public CategoriaVO createCategoria(CategoriaVO categoria) 
			throws DuplicateInstanceException, InternalErrorException;
	public CategoriaVO findCategoria(int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException;
	public CategoriaVO findCategoriaByName(String nombre) 
			throws InstanceNotFoundException, InternalErrorException;
	public void removeCategoria(int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException;
	public CategoriaChunkVO getSons(int padre, int startIndex, int count) 
			throws InternalErrorException;
	public void modifyCategoria(int idCategoria, 
			String nuevoNombre, int nuevoPadre) 
			throws InstanceNotFoundException, InternalErrorException;
	public ProductoChunkVO getProductosCategoria(int idCategoria, 
			int startIndex, int count) throws InternalErrorException;
	public CategoriaChunkVO findAllCategorias(int startIndex, int count) 
			throws InternalErrorException;
	// OPERACIONES CON EL DAO DE PRODUCTO
	public ProductoVO createProducto(ProductoVO producto) 
			throws DuplicateInstanceException, InternalErrorException;
	public ProductoVO findProducto(int idProducto) 
			throws InstanceNotFoundException, InternalErrorException;
	public ProductoChunkVO findProductoByCategoria(int idCategoria, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException;
	public ProductoVO findProductoByName(String nombre) 
			throws InstanceNotFoundException, InternalErrorException;
	public void modifyProducto(int idProducto, String nombre, 
			String descripcion, double precio, int categoria) 
			throws InstanceNotFoundException, InternalErrorException;
	public void removeProducto(int idProducto) 
			throws InstanceNotFoundException, InternalErrorException;
	public ProductoChunkVO findAllProductos(int startIndex, int count) 
			throws InternalErrorException;
}
