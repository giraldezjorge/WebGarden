/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.cliente.dao;

import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;
import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
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
public abstract class AbstractSQLClienteDAO implements SQLClienteDAO {

	protected AbstractSQLClienteDAO() {};
	
	public boolean exists(Connection connection, String NIF) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT NIF FROM Cliente WHERE NIF = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query*/
			resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // exists
	
	public ClienteVO find(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT loginName, pass, pila, ap1, ap2, " +
					"email, dir, cod_postal, tlf, poblacion, provincia, " +
					"dir_facturacion, VIP FROM Cliente WHERE NIF = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query*/
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						NIF, ClienteVO.class.getName());
			}
			
			/* get results */
			i = 1;
			String loginName = resultSet.getString(i++);
			String pass = resultSet.getString(i++);
			String pila = resultSet.getString(i++);
			String ap1 = resultSet.getString(i++);
			String ap2 = resultSet.getString(i++);
			String email = resultSet.getString(i++);
			String dir = resultSet.getString(i++);
			int cod_postal = resultSet.getInt(i++);
			int tlf = resultSet.getInt(i++);
			String poblacion = resultSet.getString(i++);
			String provincia = resultSet.getString(i++);
			String dir_facturacion = resultSet.getString(i++);
			boolean VIP = resultSet.getBoolean(i++);
			
			/* return the value object */
			return new ClienteVO(loginName, NIF, pass, pila, ap1, ap2, email, 
					dir, cod_postal, tlf, poblacion, 
					provincia, dir_facturacion, VIP);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // find
	
	public ClienteVO findByLoginName(Connection connection, String loginName) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT NIF, pass, pila, ap1, ap2, email, " +
					"dir, cod_postal, tlf, poblacion, provincia, " +
					"dir_facturacion, VIP FROM Cliente WHERE loginName = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, loginName);
			
			/* execute query*/
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						loginName, ClienteVO.class.getName());
			}
			
			/* get results */
			i = 1;
			String NIF = resultSet.getString(i++);
			String pass = resultSet.getString(i++);
			String pila = resultSet.getString(i++);
			String ap1 = resultSet.getString(i++);
			String ap2 = resultSet.getString(i++);
			String email = resultSet.getString(i++);
			String dir = resultSet.getString(i++);
			int cod_postal = resultSet.getInt(i++);
			int tlf = resultSet.getInt(i++);
			String poblacion = resultSet.getString(i++);
			String provincia = resultSet.getString(i++);
			String dir_facturacion = resultSet.getString(i++);
			boolean VIP = resultSet.getBoolean(i++);
			
			/* return the value object */
			return new ClienteVO(loginName, NIF, pass, pila, ap1, ap2, dir, 
					email, cod_postal, tlf, poblacion, 
					provincia, dir_facturacion, VIP);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // findByLoginName
	
	public void update(Connection connection, ClienteVO cliente) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Cliente SET pila = ?, ap1 = ?, " +
					"ap2 = ?, email = ?, dir = ?, cod_postal = ?, tlf = ?, " +
					"poblacion = ?, provincia = ?, dir_facturacion = ?," +
					" VIP = ? WHERE NIF = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;			
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
			preparedStatement.setString(i++, cliente.getNIF());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						cliente.getNIF(), ClienteVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" 
						+ cliente.getNIF() + "' in table 'Cliente'");
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void updatePass(Connection connection, ClienteVO cliente) 
			throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Cliente SET pass = ? WHERE NIF = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;			
			preparedStatement.setString(i++, cliente.getPass());
			preparedStatement.setString(i++, cliente.getNIF());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(
						cliente.getNIF(), ClienteVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" 
						+ cliente.getNIF() + "' in table 'Cliente'");
			}
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // updatePass
	
	public void remove(Connection connection, String NIF) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Cliente WHERE NIF = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query */
			int removedRows = preparedStatement.executeUpdate();
			
			if (removedRows == 0) {
				throw new InstanceNotFoundException(
						NIF, ClienteVO.class.getName());
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
			String queryString = "SELECT * FROM Mantenimiento " +
					"WHERE cliente = ?";
			
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
					
					mantenimientoVOs.add(new MantenimientoVO(idMantenimiento, 
							lugar, descripcion, fecha, dia, NIF));
					
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
	
	public List<FacturaVO> getFacturas(Connection connection, String NIF, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Factura WHERE cliente = ?";
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, NIF);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<FacturaVO> facturaVOs = new ArrayList<FacturaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					int idFactura = resultSet.getInt(i++);
					Timestamp fecha = resultSet.getTimestamp(i++);
					boolean pagada = resultSet.getBoolean(i++);
					
					facturaVOs.add(new FacturaVO(
							idFactura, fecha, pagada, NIF));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return facturaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // getFacturas
	
	public List<ClienteVO> findAll(Connection connection, int startIndex, 
			int count) throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Cliente ORDER BY pila";
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					int i = 1;
					String loginName = resultSet.getString(i++);
					String NIF = resultSet.getString(i++);
					String pass = resultSet.getString(i++);
					String pila = resultSet.getString(i++);
					String ap1 = resultSet.getString(i++);
					String ap2 = resultSet.getString(i++);
					String email = resultSet.getString(i++);
					String dir = resultSet.getString(i++);
					int cod_postal = resultSet.getInt(i++);
					int tlf = resultSet.getInt(i++);
					String poblacion = resultSet.getString(i++);
					String provincia = resultSet.getString(i++);
					String dir_facturacion = resultSet.getString(i++);
					boolean VIP = resultSet.getBoolean(i++);
					
					clienteVOs.add(new ClienteVO(loginName, NIF, pass, pila, 
							ap1, ap2, email, dir, cod_postal, tlf, poblacion, 
							provincia, dir_facturacion, VIP));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return clienteVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // findAll
	
	public List<ClienteVO> findByName(Connection connection, String name, 
			int startIndex, int count) 
			throws InternalErrorException, InstanceNotFoundException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT loginName, NIF, pass, ap1, ap2, " +
					"email, dir, cod_postal, tlf, poblacion, provincia, " +
					"dir_facturacion, VIP FROM Cliente WHERE pila = ? ";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, name);
			
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(
						name, ClienteVO.class.getName());
			}
			
			/* read objects */
			List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					String loginName = resultSet.getString(i++);
					String NIF = resultSet.getString(i++);
					String pass = resultSet.getString(i++);
					String ap1 = resultSet.getString(i++);
					String ap2 = resultSet.getString(i++);
					String email = resultSet.getString(i++);
					String dir = resultSet.getString(i++);
					int cod_postal = resultSet.getInt(i++);
					int tlf = resultSet.getInt(i++);
					String poblacion = resultSet.getString(i++);
					String provincia = resultSet.getString(i++);
					String dir_facturacion = resultSet.getString(i++);
					boolean VIP = resultSet.getBoolean(i++);
					
					clienteVOs.add(new ClienteVO(loginName, NIF, pass, name, 
							ap1, ap2, email, dir, cod_postal, tlf, poblacion, 
							provincia, dir_facturacion, VIP));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return clienteVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	}// findByName
	
} // class
