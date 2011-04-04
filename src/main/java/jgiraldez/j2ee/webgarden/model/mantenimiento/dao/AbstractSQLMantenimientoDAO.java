/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.mantenimiento.dao;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.sql.GeneralOperations;

/**
 * @author jorge
 *
 */
public abstract class AbstractSQLMantenimientoDAO 
		implements SQLMantenimientoDAO {
	
	protected AbstractSQLMantenimientoDAO() {};
	
	public boolean exists(Connection connection, int idMantenimiento) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement"*/
			String queryString = "SELECT * FROM Mantenimiento " +
					"WHERE idMantenimiento = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idMantenimiento);
			
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
	
	public MantenimientoVO find(Connection connection, int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT lugar, descripcion, fecha, dia, " +
					"cliente FROM Mantenimiento WHERE idMantenimiento = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idMantenimiento);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						idMantenimiento, MantenimientoVO.class.getName());
			}
			
			/* get results */
			i = 1;
			String lugar = resultSet.getString(i++);
			String descripcion = resultSet.getString(i++);
			Timestamp fecha = resultSet.getTimestamp(i++);
			String dia = resultSet.getString(i++);
			String cliente = resultSet.getString(i++);
			
			return new MantenimientoVO(idMantenimiento, lugar, descripcion, 
					fecha, dia, cliente);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find
	
	public List<MantenimientoVO> findByCliente(Connection connection, 
			String cliente, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idMantenimiento, lugar, " +
					"descripcion, fecha, dia FROM Mantenimiento " +
					"WHERE cliente = ?";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, cliente);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<MantenimientoVO> mantenimientoVOs = 
				new ArrayList<MantenimientoVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					int idMantenimiento = resultSet.getInt(i++);
					String lugar = resultSet.getString(i++);
					String descripcion = resultSet.getString(i++);
					Timestamp fecha = resultSet.getTimestamp(i++);
					String dia = resultSet.getString(i++);
					
					mantenimientoVOs.add(new MantenimientoVO(idMantenimiento, 
							lugar, descripcion, fecha, dia, cliente));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return mantenimientoVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findByCliente
	
	public void update(Connection connection, MantenimientoVO mantenimiento) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Mantenimiento SET lugar = ?, " +
					"descripcion = ?, fecha = ?, dia = ? " +
					"WHERE idMantenimiento = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;			
			preparedStatement.setString(i++, mantenimiento.getLugar());
			preparedStatement.setString(i++, mantenimiento.getDescripcion());
			preparedStatement.setTimestamp(i++, mantenimiento.getFecha());
			preparedStatement.setString(i++, mantenimiento.getDia());
			preparedStatement.setInt(i++, mantenimiento.getIdMantenimiento());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						mantenimiento.getIdMantenimiento(), 
						MantenimientoVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" + 
						mantenimiento.getIdMantenimiento() +
						"' in table 'Mantenimiento'");
			}
			
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, int idMantenimiento) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Mantenimiento " +
					"WHERE idMantenimiento = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idMantenimiento);
			
			/* execute query */
			int removeRows = preparedStatement.executeUpdate();
			
			if (removeRows == 0) {
				throw new InstanceNotFoundException(
						idMantenimiento, MantenimientoVO.class.getName());
			}
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
	public List<MantenimientoVO> findAll(Connection connection, 
			int startIndex, int count) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Mantenimiento";
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<MantenimientoVO> mantenimientoVOs = 
				new ArrayList<MantenimientoVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					int i = 1;
					
					int idMantenimiento = resultSet.getInt(i++);
					String lugar = resultSet.getString(i++);
					String descripcion = resultSet.getString(i++);
					Timestamp fecha = resultSet.getTimestamp(i++);
					String dia = resultSet.getString(i++);
					String cliente = resultSet.getString(i++);
					
					mantenimientoVOs.add(new MantenimientoVO(idMantenimiento, 
							lugar, descripcion, fecha, dia, cliente));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return mantenimientoVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findAll

} // class
