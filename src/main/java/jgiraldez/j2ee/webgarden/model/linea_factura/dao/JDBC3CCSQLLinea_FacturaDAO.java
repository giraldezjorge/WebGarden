/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.linea_factura.dao;

import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

/**
 * @author jorge
 *
 */
public class JDBC3CCSQLLinea_FacturaDAO extends AbstractSQLLinea_FacturaDAO {

	public Linea_FacturaVO create(Connection connection, Linea_FacturaVO linea) 
			throws DuplicateInstanceException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* created "preparedStatement" */
			String queryString = "INSERT INTO Linea_Factura (num_linea, " +
					"factura, producto, cantidad, descuento, total) " +
					"VALUES (?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, linea.getNum_linea());
			preparedStatement.setInt(i++, linea.getFactura());
			preparedStatement.setInt(i++, linea.getProducto());
			preparedStatement.setInt(i++, linea.getCantidad());
			preparedStatement.setDouble(i++, linea.getDescuento());
			preparedStatement.setDouble(i++, linea.getTotal());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException(
						"Can not add row to table 'Linea_Factura'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException(
						"Duplicate row in table 'Linea_Factura'");
			}
			
			/* return the value object */
			return new Linea_FacturaVO(linea.getNum_linea(), 
					linea.getFactura(), linea.getProducto(), 
					linea.getCantidad(), linea.getDescuento(), 
					linea.getTotal());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // create

}
