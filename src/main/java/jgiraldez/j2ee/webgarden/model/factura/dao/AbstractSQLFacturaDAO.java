/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.factura.dao;

import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;
import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

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
public abstract class AbstractSQLFacturaDAO implements SQLFacturaDAO {
	
	protected AbstractSQLFacturaDAO() {};
	
	public boolean exists(Connection connection, int idFactura) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Factura WHERE idFactura = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idFactura);
			
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
	
	public FacturaVO find(Connection connection, int idFactura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = 
			"SELECT fecha, pagada, cliente FROM Factura WHERE idFactura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idFactura);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(idFactura, 
						FacturaVO.class.getName());
			}
			
			/* get results */
			i = 1;
			Timestamp fecha = resultSet.getTimestamp(i++);
			boolean pagada = resultSet.getBoolean(i++);
			String cliente = resultSet.getString(i++);
			
			/* return the value object */
			return new FacturaVO(idFactura, fecha, pagada, cliente);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // find
	
	public List<FacturaVO> findByClient(Connection connection, String cliente, 
			int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = 
			"SELECT idFactura, fecha, pagada FROM Factura WHERE cliente = ? " +
			"ORDER BY fecha";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setString(i++, cliente);
			
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
					
					facturaVOs.add(new FacturaVO(idFactura, fecha, 
							pagada, cliente));
					
					currentCount++;
					
				} while(resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return facturaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // findByClient
	
	public void update(Connection connection, FacturaVO factura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Factura SET fecha = ?, " +
				"pagada = ?, cliente = ? WHERE idFactura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setTimestamp(i++, factura.getFecha());
			preparedStatement.setBoolean(i++, factura.getPagada());
			preparedStatement.setString(i++, factura.getCliente());
			preparedStatement.setInt(i++, factura.getIdFactura());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(factura.getIdFactura(), 
						FacturaVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException("Duplicate row for identifier = '" + 
						factura.getIdFactura() + "' in table 'Factura'");
			}
			
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // update
	
	public void remove(Connection connection, int idFactura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Factura WHERE idFactura = ?";
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idFactura);
			
			/* execute query */
			int removeRows = preparedStatement.executeUpdate();
			
			if (removeRows == 0) {
				throw new InstanceNotFoundException(idFactura, 
						FacturaVO.class.getName());
			}
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove
	
	public List<FacturaVO> findNotPagadas(Connection connection, 
			int startIndex, int count) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT idFactura, fecha, cliente " +
					"FROM Factura WHERE pagada = 0 ORDER BY cliente";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<FacturaVO> facturaVOs = new ArrayList<FacturaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					
					int i = 1;
					int idFactura = resultSet.getInt(i++);
					Timestamp fecha = resultSet.getTimestamp(i++);
					String cliente = resultSet.getString(i++);
					
					facturaVOs.add(new FacturaVO(idFactura, fecha, 
							false, cliente));
					
					currentCount++;
					
				} while(resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return facturaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
	} // findNotPagadas
	
	public List<FacturaVO> findAll(Connection connection, 
			int startIndex, int count) throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Factura ORDER BY fecha";
			
			preparedStatement = connection.prepareStatement(queryString, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<FacturaVO> facturaVOs = new ArrayList<FacturaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					int i = 1;
					
					int idFactura = resultSet.getInt(i++);
					Timestamp fecha = resultSet.getTimestamp(i++);
					boolean pagada = resultSet.getBoolean(i++);
					String cliente = resultSet.getString(i++);
					
					facturaVOs.add(new FacturaVO(idFactura, fecha, 
							pagada, cliente));
					
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
	} // findAll
	
	public List<Linea_FacturaVO> getLineas(Connection connection, 
			int idFactura, int startIndex, int count) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT num_linea, producto, cantidad, " +
					"descuento, total FROM Linea_Factura WHERE factura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, idFactura);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			/* read objects */
			List<Linea_FacturaVO> lineaVOs = new ArrayList<Linea_FacturaVO>();
			int currentCount = 0;
			
			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				
				do {
					i = 1;
					int num_linea = resultSet.getInt(i++);
					int producto = resultSet.getInt(i++);
					int cantidad = resultSet.getInt(i++);
					double descuento = resultSet.getDouble(i++);
					double total = resultSet.getDouble(i++);
					
					lineaVOs.add(new Linea_FacturaVO(num_linea, idFactura, 
							producto, cantidad, descuento, total));
					
					currentCount++;
					
				} while (resultSet.next() && (currentCount < count));
			}
			
			/* return value objects */
			return lineaVOs;
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // getLineas

} // class
