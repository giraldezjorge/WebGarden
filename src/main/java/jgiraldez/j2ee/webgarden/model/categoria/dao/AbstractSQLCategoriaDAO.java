/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.categoria.dao;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
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
public abstract class AbstractSQLCategoriaDAO implements SQLCategoriaDAO {

	protected AbstractSQLCategoriaDAO() {}
	
	public boolean exists(Connection connection, int idCategoria) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			/* create "preparedStatement"*/
			String queryString = 
				"SELECT * FROM Categoria WHERE idCategoria = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idCategoria);
			
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
	
	public CategoriaVO find(Connection connection, int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = 
				"SELECT * FROM Categoria WHERE idCategoria = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idCategoria);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						idCategoria, CategoriaVO.class.getName());
			}
			
			/* get results */
			i = 2; //dos por que si empezasemos en uno cogemos idCategoria
			String nombre = resultSet.getString(i++);
			int padre = resultSet.getInt(i++);
			
			return new CategoriaVO(idCategoria, nombre, padre);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find
	
	public void update(Connection connection, CategoriaVO categoria) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Categoria SET nombre = ?, padre = ? " +
					"WHERE idCategoria = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, categoria.getNombre());
			preparedStatement.setInt(i++, categoria.getPadre());
			preparedStatement.setInt(i++, categoria.getIdCategoria());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(categoria.getIdCategoria(),
						CategoriaVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" +
						categoria.getIdCategoria() + "' in table 'Categoria'");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, int idCategoria) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Categoria WHERE idCategoria = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idCategoria);
			
			/* execute query */
			int removeRows = preparedStatement.executeUpdate();
			
			if (removeRows == 0) {
				throw new InstanceNotFoundException(idCategoria, 
						CategoriaVO.class.getName());
			}
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
	public CategoriaVO findByName(Connection connection, String name) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idCategoria, padre FROM Categoria " +
					"WHERE nombre LIKE ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, "%" + name + "%");
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(name, 
						CategoriaVO.class.getName());
			}
			
			/* get results */
			i = 1;
			int idCategoria = resultSet.getInt(i++);
			int padre = resultSet.getInt(i++);
			
			return new CategoriaVO(idCategoria, name, padre);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findByName

	public List<CategoriaVO> getSons(Connection connection, int padre, 
			int startIndex, int count) throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idCategoria, nombre FROM Categoria " +
					"WHERE padre = ? ORDER BY nombre";
			
			preparedStatement = connection.prepareStatement(queryString, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, padre);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<CategoriaVO> categoriaVOs = new ArrayList<CategoriaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					int idCategoria = resultSet.getInt(i++);
					String nombre = resultSet.getString(i++);
					
					categoriaVOs.add(new CategoriaVO(
							idCategoria, nombre, padre));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return categoriaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // getSons
	
	public List<CategoriaVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idCategoria, nombre, padre " +
					"FROM Categoria ORDER BY nombre";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<CategoriaVO> categoriaVOs = new ArrayList<CategoriaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					int i = 1;
					int idCategoria = resultSet.getInt(i++);
					String nombre = resultSet.getString(i++);
					int padre = resultSet.getInt(i++);
					
					categoriaVOs.add(new CategoriaVO(
							idCategoria, nombre, padre));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return categoriaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findAll
	
	public List<ProductoVO> getProductos(Connection connection, 
			int idCategoria, int startIndex, int count ) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Producto WHERE categoria = ?";
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idCategoria);
			
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
					int precio = resultSet.getInt(i++);
					
					productoVOs.add(new ProductoVO(idProducto, nombre, 
							descripcion, precio, idCategoria));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return productoVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // getProductos
	
} // class
