/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.producto.dao;

import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

/**
 * @author jorge
 *
 */
public class JDBC3CCSQLProductoDAO extends AbstractSQLProductoDAO {

	public ProductoVO create(Connection connection, ProductoVO producto) 
			throws DuplicateInstanceException, InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "INSERT INTO Producto (idProducto, nombre, " +
					"descripcion, precio, categoria) VALUES (?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, producto.getIdProducto());
			preparedStatement.setString(i++, producto.getNombre());
			preparedStatement.setString(i++, producto.getDescripcion());
			preparedStatement.setDouble(i++, producto.getPrecio());
			preparedStatement.setInt(i++, producto.getCategoria());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Producto'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException("Duplicate row in table 'Producto'");
			}
			
			/* get producto identifier */
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (!resultSet.next()) {
				throw new InternalErrorException(new SQLException(
						"JDBC driver did not return generated key."));
			}
			
			int idProducto = resultSet.getInt(1);
			
			/* return the value object */
			return new ProductoVO(idProducto, producto.getNombre(), 
					producto.getDescripcion(), producto.getPrecio(), 
					producto.getCategoria());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // create

}
