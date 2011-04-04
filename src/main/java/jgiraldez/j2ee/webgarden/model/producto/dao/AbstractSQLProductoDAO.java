/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.producto.dao;

import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

/**
 * @author jorge
 *
 */
public abstract class AbstractSQLProductoDAO implements SQLProductoDAO {

	protected AbstractSQLProductoDAO() {}

	public boolean exists(Connection connection, int idProducto) 
			throws InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
		
			/* create "preparedStatement" */
			String queryString = "SELECT idProducto FROM Producto " +
					"WHERE idProducto = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // exists
	
	public ProductoVO find(Connection connection, int idProducto) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT nombre, descripcion, precio, " +
					"categoria FROM Producto WHERE idProducto = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				throw new InstanceNotFoundException(
						idProducto, ProductoVO.class.getName());
			}
			
			/* get results */
			i = 1;
			String nombre = resultSet.getString(i++);
			String descripcion = resultSet.getString(i++);
			double precio = resultSet.getDouble(i++);
			int categoria = resultSet.getInt(i++);
			
			/* return the value object */
			
			return new ProductoVO(idProducto, nombre, descripcion, 
					precio, categoria);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find

	public List<ProductoVO> findByCategoria(Connection connection, 
			int categoria, int startIndex, int count) 
			throws InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idProducto, nombre, descripcion, " +
					"precio FROM Producto WHERE categoria = ? " +
					"ORDER BY idProducto";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, categoria);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<ProductoVO> productoVOs = new ArrayList<ProductoVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					
					i = 1;
					int idProducto = resultSet.getInt(i++);
					String nombre = resultSet.getString(i++);
					String descripcion = resultSet.getString(i++);
					double precio = resultSet.getDouble(i++);
					
					productoVOs.add(new ProductoVO(idProducto, nombre, 
							descripcion, precio, categoria));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
				
			}
			
			return productoVOs;
			
		} catch(SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findByCategoria

	public List<ProductoVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idProducto, nombre, descripcion, " +
					"precio, categoria FROM Producto ORDER BY nombre";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<ProductoVO> productoVOs = new ArrayList<ProductoVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					
					int i = 1;
					int idProducto = resultSet.getInt(i++);
					String nombre = resultSet.getString(i++);
					String descripcion = resultSet.getString(i++);
					double precio = resultSet.getDouble(i++);
					int categoria = resultSet.getInt(i++);
					
					productoVOs.add(new ProductoVO(idProducto, nombre, 
							descripcion, precio, categoria));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
				
			}
			
			return productoVOs;
			
		} catch(SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findAll
	
	public ProductoVO findByName(Connection connection, String nombre) 
			throws InstanceNotFoundException, InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idProducto, descripcion, precio, " +
					"categoria FROM Producto WHERE nombre = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, nombre);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						nombre, ProductoVO.class.getName());
			}
			
			/* get results */
			i = 1;
			int idProducto = resultSet.getInt(i++);
			String descripcion = resultSet.getString(i++);
			double precio = resultSet.getDouble(i++);
			int categoria = resultSet.getInt(i++);
			
			/* return value object */
			return new ProductoVO(idProducto, nombre, 
					descripcion, precio, categoria);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findByName

	public void update(Connection connection, ProductoVO producto) 
			throws InstanceNotFoundException, InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Producto SET nombre = ?, " +
					"descripcion = ?, precio = ?, categoria = ? " +
					"WHERE idProducto = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, producto.getNombre());
			preparedStatement.setString(i++, producto.getDescripcion());
			preparedStatement.setDouble(i++, producto.getPrecio());
			preparedStatement.setInt(i++, producto.getCategoria());
			preparedStatement.setInt(i++, producto.getIdProducto());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						producto.getIdProducto(), ProductoVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" +
						producto.getIdProducto() + "' in table 'Producto'");
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, int idProducto) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Producto WHERE idProducto = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			/* execute query */
			int removedRows = preparedStatement.executeUpdate();
			
			if (removedRows == 0) {
				throw new InstanceNotFoundException(
						idProducto, ProductoVO.class.getName());
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
} // class
