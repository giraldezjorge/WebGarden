/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain;

import javax.sql.DataSource;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.PlainActionProcessor;
import jgiraldez.j2ee.webgarden.util.sql.SimpleDataSource;
import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	CreateCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	CreateProductoAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindAllCategoriasAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindAllProductosAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindCategoriaByNameAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindProductoAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindProductoByCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	FindProductoByNameAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	GetProductosCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	GetSonsCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	ModifyCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	ModifyProductoAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	RemoveCategoriaAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.plain.actions.
	RemoveProductoAction;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	CategoriaChunkVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo.
	ProductoChunkVO;

/**
 * @author jorge
 *
 */
public class PlainProductsManagementFacadeDelegate 
		implements ProductsManagementFacadeDelegate {

	private DataSource dataSource;
	
	public PlainProductsManagementFacadeDelegate() 
			throws InternalErrorException {
	
		dataSource = new SimpleDataSource();
	}
	
	/*
	 * OPERACIONES CON EL DAO DE CATEGORIA
	 */
	
	public CategoriaVO createCategoria(CategoriaVO categoria) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateCategoriaAction action = new CreateCategoriaAction(categoria);
			
			return (CategoriaVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createCategoria
	
	public CategoriaVO findCategoria(int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindCategoriaAction action = new FindCategoriaAction(idCategoria);
			
			return (CategoriaVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findCategoria
	
	public CategoriaVO findCategoriaByName(String nombre) 
			throws InstanceNotFoundException, InternalErrorException {
	
		try {
			
			FindCategoriaByNameAction action = 
				new FindCategoriaByNameAction(nombre);
			
			return (CategoriaVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findCategoriaByName
	
	public void removeCategoria(int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException {
	
		try {
			
			RemoveCategoriaAction action = 
				new RemoveCategoriaAction(idCategoria);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // removeCategoria
	
	public CategoriaChunkVO getSons(int padre, int startIndex, int count) 
			throws InternalErrorException {
	
		try {
			
			GetSonsCategoriaAction action = 
				new GetSonsCategoriaAction(padre, startIndex, count);
			
			return (CategoriaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getSons
	
	public CategoriaChunkVO findAllCategorias(int startIndex, int count) 
			throws InternalErrorException {
		
		try {
			
			FindAllCategoriasAction action = 
				new FindAllCategoriasAction(startIndex, count);
			
			return (CategoriaChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllCategorias
	
	public ProductoChunkVO getProductosCategoria(int idCategoria, 
			int startIndex, int count) throws InternalErrorException {
		
		try {
			
			GetProductosCategoriaAction action = 
				new GetProductosCategoriaAction(idCategoria, startIndex, count);
			
			return (ProductoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // getProductosCategoria
	
	public void modifyCategoria(int idCategoria, 
			String nuevoNombre, int nuevoPadre) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			ModifyCategoriaAction action = 
				new ModifyCategoriaAction(idCategoria, nuevoNombre, nuevoPadre);
			
			PlainActionProcessor.process(dataSource, action);
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyCategoria
	
	/*
	 * OPERACIONES CON EL DAO DE PRODUCTO
	 */
	
	public ProductoVO createProducto(ProductoVO producto) 
			throws DuplicateInstanceException, InternalErrorException {
		
		try {
			
			CreateProductoAction action = new CreateProductoAction(producto);
			
			return (ProductoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (DuplicateInstanceException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // createProducto
	
	public void modifyProducto(int idProducto, String nombre, 
			String descripcion, double precio, int categoria) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			ModifyProductoAction action = 
				new ModifyProductoAction(idProducto, nombre, descripcion, 
						precio, categoria);
			
			PlainActionProcessor.process(dataSource, action);
					
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // modifyProducto
	
	public void removeProducto(int idProducto) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			RemoveProductoAction action = new RemoveProductoAction(idProducto);
			
			PlainActionProcessor.process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // removeProducto
	
	public ProductoVO findProducto(int idProducto) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindProductoAction action = new FindProductoAction(idProducto);
			
			return (ProductoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findProducto
	
	public ProductoChunkVO findProductoByCategoria(int idCategoria, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindProductoByCategoriaAction action = 
				new FindProductoByCategoriaAction(idCategoria, 
						startIndex, count);
			
			return (ProductoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findProductoByCategoria
	
	public ProductoVO findProductoByName(String nombre) 
			throws InstanceNotFoundException, InternalErrorException {
		
		try {
			
			FindProductoByNameAction action = 
				new FindProductoByNameAction(nombre);
			
			return (ProductoVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findProductoByName
	
	public ProductoChunkVO findAllProductos(int startIndex, int count) 
			throws InternalErrorException {
		
		try {
			
			FindAllProductosAction action = 
				new FindAllProductosAction(startIndex, count);
			
			return (ProductoChunkVO) PlainActionProcessor.
				process(dataSource, action);
			
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException(e);
		}
	} // findAllProductos
	
}// class
