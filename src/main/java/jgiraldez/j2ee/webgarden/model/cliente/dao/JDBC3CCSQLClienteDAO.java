/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.cliente.dao;

import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

/**
 * @author jorge
 *
 */
public class JDBC3CCSQLClienteDAO extends AbstractSQLClienteDAO {

	public ClienteVO create(Connection connection, ClienteVO cliente) 
			throws DuplicateInstanceException, InternalErrorException {
		
		/* Check if the user already exists. */
        if (exists(connection, cliente.getNIF())) {
            throw new DuplicateInstanceException(
            		cliente.getNIF(), ClienteVO.class.getName());
        }
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "INSERT INTO Cliente (loginName, NIF, " +
					"pass, pila, ap1, ap2, email, dir, cod_postal, tlf, " +
					"poblacion, provincia, dir_facturacion, VIP) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, cliente.getLoginName());
			preparedStatement.setString(i++, cliente.getNIF());
			preparedStatement.setString(i++, cliente.getPass());
			preparedStatement.setString(i++, cliente.getPila());
			preparedStatement.setString(i++, cliente.getAp1());
			preparedStatement.setString(i++, cliente.getAp2());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getDir());
			preparedStatement.setInt(i++, cliente.getCod_postal());
			preparedStatement.setInt(i++, cliente.getTlf());
			preparedStatement.setString(i++, cliente.getPoblacion());
			preparedStatement.setString(i++, cliente.getProvincia());
			preparedStatement.setString(i++, cliente.getDir_facturacion());
			preparedStatement.setBoolean(i++, cliente.getVIP());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Cliente'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException("Duplicate row in table 'Cliente'");
			}
			
			/* return the value object */
			return new ClienteVO(cliente.getLoginName(), cliente.getNIF(), 
					cliente.getPass(), cliente.getPila(), cliente.getAp1(), 
					cliente.getAp2(), cliente.getEmail(), cliente.getDir(), 
					cliente.getCod_postal(), cliente.getTlf(), 
					cliente.getPoblacion(), cliente.getProvincia(), 
					cliente.getDir_facturacion(), cliente.getVIP());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // create
	
}
