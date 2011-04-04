/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.factura.dao;

import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
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
public class JDBC3CCSQLFacturaDAO extends AbstractSQLFacturaDAO {

	public FacturaVO create(Connection connection, FacturaVO factura) 
			throws DuplicateInstanceException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "INSERT INTO Factura (fecha, pagada, " +
					"cliente) VALUES (?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(
					queryString, Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;			
			preparedStatement.setTimestamp(i++, factura.getFecha());
			preparedStatement.setBoolean(i++, factura.getPagada());
			preparedStatement.setString(i++, factura.getCliente());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Factura'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException("Duplicate row in table 'Factura'");
			}
			
			/* get producto identifier */
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (!resultSet.next()) {
				throw new InternalErrorException(new SQLException(
								"JDBC driver did not return generated key."));
			}
			
			int idFactura = resultSet.getInt(1);
			
			/* return the value object */
			return new FacturaVO(idFactura, factura.getFecha(), 
					factura.getPagada(), factura.getCliente());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // create
	
}
