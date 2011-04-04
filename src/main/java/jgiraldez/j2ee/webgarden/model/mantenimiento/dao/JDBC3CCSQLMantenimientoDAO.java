/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento.dao;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

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
public class JDBC3CCSQLMantenimientoDAO extends AbstractSQLMantenimientoDAO{

	public MantenimientoVO create(Connection connection, 
			MantenimientoVO mantenimiento) 
			throws DuplicateInstanceException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* created "preparedStatement" */
			String queryString = "INSERT INTO Mantenimiento (lugar, " +
				"descripcion, fecha, dia, cliente) VALUES (?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(
						queryString, Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;		
			preparedStatement.setString(i++, mantenimiento.getLugar());
			preparedStatement.setString(i++, mantenimiento.getDescripcion());
			preparedStatement.setTimestamp(i++, mantenimiento.getFecha());
			preparedStatement.setString(i++, mantenimiento.getDia());
			preparedStatement.setString(i++, mantenimiento.getCliente());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException(
						"Can not add row to table 'Mantenimiento'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException(
						"Duplicate row in table 'Mantenimiento'");
			}
			
			/* get mantenimiento identifier */
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (!resultSet.next()) {
				throw new InternalErrorException(
						new SQLException(
								"JDBC driver did not return generated key."));
			}
			
			int idMantenimiento = resultSet.getInt(1);
			
			/* return the value object */
			return new MantenimientoVO(idMantenimiento, 
					mantenimiento.getLugar(), mantenimiento.getDescripcion(), 
					mantenimiento.getFecha(), mantenimiento.getDia(), 
					mantenimiento.getCliente());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // create

}
