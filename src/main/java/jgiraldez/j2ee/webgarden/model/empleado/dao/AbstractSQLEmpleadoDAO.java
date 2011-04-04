/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.empleado.dao;

import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;
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
public abstract class AbstractSQLEmpleadoDAO implements SQLEmpleadoDAO {

	protected AbstractSQLEmpleadoDAO() {};
	
	public boolean exists(Connection connection, String NIF) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT NIF FROM Empleado WHERE NIF = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
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
	
	public EmpleadoVO find(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Empleado WHERE NIF = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query*/
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						NIF, EmpleadoVO.class.getName());
			}
			
			/* get results */
			i = 2; // porque si empiezo en uno cojo también el NIF
			String pass = resultSet.getString(i++);
			String pila = resultSet.getString(i++);
			String ap1 = resultSet.getString(i++);
			String ap2 = resultSet.getString(i++);
			String dir = resultSet.getString(i++);
			int cod_postal = resultSet.getInt(i++);
			int tlf = resultSet.getInt(i++);
			String poblacion = resultSet.getString(i++);
			String provincia = resultSet.getString(i++);
			long num_cuenta = resultSet.getLong(i++);
			boolean esAdmin = resultSet.getBoolean(i++);
			
			/* return the value object */
			return new EmpleadoVO(NIF, pass, pila, ap1, ap2, dir, cod_postal, 
					tlf, poblacion, provincia, num_cuenta, esAdmin);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find
	
	public void update(Connection connection, EmpleadoVO empleado) 
				throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Empleado SET pass = ?, pila = ?, " +
					"ap1 = ?, ap2 = ?, dir = ?, cod_postal = ?, tlf = ?, " +
					"poblacion = ?, provincia = ?, num_cuenta = ?, " +
					"esAdmin = ? WHERE NIF = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
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
			preparedStatement.setString(i++, empleado.getNIF());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						empleado.getNIF(), EmpleadoVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" + 
						empleado.getNIF() + "' in table 'Empleado'");
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Empleado WHERE NIF = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query */
			int removedRows = preparedStatement.executeUpdate();
			
			if (removedRows == 0) {
				throw new InstanceNotFoundException(
						NIF, EmpleadoVO.class.getName());
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
	public List<MantenimientoVO> getMantenimientos(Connection connection, 
			String NIF, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT a.idMantenimiento, a.lugar, " +
					"a.descripcion, a.fecha, a.dia, a.cliente " +
					"FROM Mantenimiento a INNER JOIN Mantenimiento_Emp b " +
					"ON a.idMantenimiento = b.mantenimiento " +
					"WHERE b.empleado = ?";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
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
	} // getMantenimientos
	
	public List<EmpleadoVO> findAll(Connection connection, 
			int startIndex, int count) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Empleado";
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<EmpleadoVO> empleadoVOs = new ArrayList<EmpleadoVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					int i = 1;
					String NIF = resultSet.getString(i++);
					String pass = resultSet.getString(i++);
					String pila = resultSet.getString(i++);
					String ap1 = resultSet.getString(i++);
					String ap2 = resultSet.getString(i++);
					String dir = resultSet.getString(i++);
					int cod_postal = resultSet.getInt(i++);
					int tlf = resultSet.getInt(i++);
					String poblacion = resultSet.getString(i++);
					String provincia = resultSet.getString(i++);
					long num_cuenta = resultSet.getLong(i++);
					boolean esAdmin = resultSet.getBoolean(i++);
					
					empleadoVOs.add(new EmpleadoVO(NIF, pass, pila, ap1, ap2, 
							dir, cod_postal, tlf, poblacion, provincia, 
							num_cuenta, esAdmin));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return empleadoVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findAll
	
} // class
