/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.categoria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;

/**
 * @author jorge
 *
 */
public class JDBC3CCSQLCategoriaDAO extends AbstractSQLCategoriaDAO {

	public CategoriaVO create(Connection connection, CategoriaVO categoria) 
				throws DuplicateInstanceException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* created "preparedStatement" */
			String queryString = "INSERT INTO Categoria " +
					"(nombre, padre) VALUES (?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, categoria.getNombre());
			preparedStatement.setInt(i++, categoria.getPadre());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Categoria'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException("Duplicate row in table 'Categoria'");
			}
			
			/* get categoria identifier */
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (!resultSet.next()) {
				throw new InternalErrorException(
				new SQLException("JDBC driver did not return generated key."));
			}
			
			int idCategoria = resultSet.getInt(1);
			
			/* return the value object */
			return new CategoriaVO(idCategoria, categoria.getNombre(), 
					categoria.getPadre());
			
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // create
}
