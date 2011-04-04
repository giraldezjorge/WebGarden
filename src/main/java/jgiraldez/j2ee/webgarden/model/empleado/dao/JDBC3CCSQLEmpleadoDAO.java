/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.empleado.dao;

import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;

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
public class JDBC3CCSQLEmpleadoDAO extends AbstractSQLEmpleadoDAO {

	public EmpleadoVO create(Connection connection, EmpleadoVO empleado) 
				throws DuplicateInstanceException, InternalErrorException {
		
		/* Check if the employee already exists. */
        if (exists(connection, empleado.getNIF())) {
            throw new DuplicateInstanceException(
            		empleado.getNIF(), EmpleadoVO.class.getName());
        }
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "INSERT INTO Empleado (NIF, pass, pila, " +
					"ap1, ap2, dir, cod_postal, tlf, poblacion, provincia, " +
					"num_cuenta, esAdmin) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, empleado.getNIF());
			preparedStatement.setString(i++, empleado.getPass());
			preparedStatement.setString(i++, empleado.getPila());
			preparedStatement.setString(i++, empleado.getAp1());
			preparedStatement.setString(i++, empleado.getAp2());
			preparedStatement.setString(i++, empleado.getDir());
			preparedStatement.setInt(i++, empleado.getCod_postal());
			preparedStatement.setInt(i++, empleado.getTlf());
			preparedStatement.setString(i++, empleado.getPoblacion());
			preparedStatement.setString(i++, empleado.getProvincia());
			preparedStatement.setLong(i++, empleado.getNum_cuenta());
			preparedStatement.setBoolean(i++, empleado.getEsAdmin());
			
			/* exectute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Empleado'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException("Duplicate row in table 'Empleado'");
			}
			
			/* return the value object */
			return new EmpleadoVO(empleado.getNIF(), empleado.getPass(), 
					empleado.getPila(), empleado.getAp1(), empleado.getAp2(), 
					empleado.getDir(), empleado.getCod_postal(), 
					empleado.getTlf(), empleado.getPoblacion(), 
					empleado.getProvincia(), empleado.getNum_cuenta(), 
					empleado.getEsAdmin());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // create

}
