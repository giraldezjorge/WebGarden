/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao;

import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.vo.Mantenimiento_EmpVO;

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
public class JDBC3CCSQLMantenimiento_EmpDAO 
		extends AbstractSQLMantenimiento_EmpDAO {

	public Mantenimiento_EmpVO create(Connection connection, 
			Mantenimiento_EmpVO mantenimiento_emp) 
			throws DuplicateInstanceException, InternalErrorException {
	
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* created "preparedStatement" */
			String queryString = "INSERT INTO Mantenimiento_Emp " +
					"(mantenimiento, empleado) VALUES (?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, mantenimiento_emp.getMantenimiento());
			preparedStatement.setString(i++, mantenimiento_emp.getEmpleado());
			
			/* execute query */
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException(
						"Can not add row to table 'Mantenimiento_Emp'");
			}
			
			if (insertedRows > 1) {
				throw new SQLException(
						"Duplicate row in table 'Mantenimiento_Emp'");
			}
			
			/* return the value object */
			return new Mantenimiento_EmpVO(
					mantenimiento_emp.getMantenimiento(), 
					mantenimiento_emp.getEmpleado());
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		} 
	} // create

}
