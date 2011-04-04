/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento_emp.dao;

import jgiraldez.j2ee.webgarden.model.mantenimiento_emp.vo.Mantenimiento_EmpVO;

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
public abstract class AbstractSQLMantenimiento_EmpDAO 
		implements SQLMantenimiento_EmpDAO {

	protected AbstractSQLMantenimiento_EmpDAO() {}
	
	public boolean exists(Connection connection, int mantenimiento, 
			String empleado) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			/* create "preparedStatement"*/
			String queryString = "SELECT * FROM Mantenimiento_Emp " +
					"WHERE mantenimiento = ? and empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, mantenimiento);
			preparedStatement.setString(i++, empleado);
			
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
	
	public Mantenimiento_EmpVO find(Connection connection, 
			int mantenimiento, String empleado) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Mantenimiento_Emp " +
					"WHERE mantenimiento = ? and empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, mantenimiento);
			preparedStatement.setString(i++, empleado);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						mantenimiento, Mantenimiento_EmpVO.class.getName());
			}
			
			/* get results */
			return new Mantenimiento_EmpVO(mantenimiento, empleado);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find
	
	public List<Mantenimiento_EmpVO> findByEmpleado(Connection connection, 
			String empleado, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT mantenimiento " +
					"FROM Mantenimiento_Emp WHERE empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, empleado);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<Mantenimiento_EmpVO> mantenimiento_empVOs = 
				new ArrayList<Mantenimiento_EmpVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					int mantenimiento = resultSet.getInt(i++);
					
					mantenimiento_empVOs.add(new Mantenimiento_EmpVO(
							mantenimiento, empleado));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return mantenimiento_empVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findByEmpleado
	
	public void update(Connection connection, 
			Mantenimiento_EmpVO mantenimiento_emp) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Mantenimiento_Emp " +
					"SET mantenimiento = ?, empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, mantenimiento_emp.getMantenimiento());
			preparedStatement.setString(i++, mantenimiento_emp.getEmpleado());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						mantenimiento_emp.getMantenimiento(), 
						Mantenimiento_EmpVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException(
						"Duplicate row for identifier : mantenimiento = '" +
						mantenimiento_emp.getMantenimiento() +
						"' empleado = '" + mantenimiento_emp.getEmpleado() +
						"' in table 'Mantenimiento_Emp'");
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, 
			int mantenimiento, String empleado) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Mantenimiento_Emp " +
					"WHERE mantenimiento = ? and empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, mantenimiento);
			preparedStatement.setString(i++, empleado);
			
			/* execute query */
			int removeRows = preparedStatement.executeUpdate();
			
			if (removeRows == 0) {
				throw new InstanceNotFoundException(
						mantenimiento, Mantenimiento_EmpVO.class.getName());
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
} // class
