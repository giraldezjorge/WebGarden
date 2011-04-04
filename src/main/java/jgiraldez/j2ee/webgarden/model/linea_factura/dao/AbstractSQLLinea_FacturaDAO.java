/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.linea_factura.dao;

import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

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
public abstract class AbstractSQLLinea_FacturaDAO 
		implements SQLLinea_FacturaDAO {

	protected AbstractSQLLinea_FacturaDAO() {};
	
	public boolean exists(Connection connection, int num_linea, int factura) 
			throws InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			/* create "preparedStatement"*/
			String queryString = "SELECT * FROM Linea_Factura " +
					"WHERE num_linea = ? and factura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, num_linea);
			preparedStatement.setInt(i++, factura);
			
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
	
	public Linea_FacturaVO find(Connection connection, 
			int num_linea, int factura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "SELECT * FROM Linea_Factura " +
					"WHERE num_linea = ? and factura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, num_linea);
			preparedStatement.setInt(i++, factura);
			
			/* execute query */
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				throw new InstanceNotFoundException(num_linea, 
						Linea_FacturaVO.class.getName());
			}
			
			/* get results */
			i = 3;
			int producto = resultSet.getInt(i++);
			int cantidad = resultSet.getInt(i++);
			double descuento = resultSet.getDouble(i++);
			double total = resultSet.getDouble(i++);
			
			return new Linea_FacturaVO(num_linea, factura, producto, 
					cantidad, descuento, total);
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // find
	
	public List<Linea_FacturaVO> findByFactura(Connection connection, 
			int factura, int startIndex, int count) 
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
			preparedStatement.setInt(i++, factura);
			
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
					
					lineaVOs.add(new Linea_FacturaVO(num_linea, factura, 
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
	} // findByFactura
	
	public void update(Connection connection, Linea_FacturaVO linea) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "UPDATE Linea_Factura SET producto = ?, " +
					"cantidad = ?, descuento = ?, total = ? " +
					"WHERE num_linea = ? AND factura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, linea.getProducto());
			preparedStatement.setInt(i++, linea.getCantidad());
			preparedStatement.setDouble(i++, linea.getDescuento());
			preparedStatement.setDouble(i++, linea.getTotal());
			preparedStatement.setInt(i++, linea.getNum_linea());
			preparedStatement.setInt(i++, linea.getFactura());
			
			/* execute query */
			int updateRows = preparedStatement.executeUpdate();
			
			if (updateRows == 0) {
				throw new InstanceNotFoundException(linea.getNum_linea(), 
						Linea_FacturaVO.class.getName());
			}
			
			if (updateRows > 1) {
				throw new SQLException(
						"Duplicate row for identifier : num_linea '" + 
						linea.getNum_linea() + "' factura '" + 
						linea.getFactura() + "' in table 'Linea_Factura'");
			}
			
			
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // update
	
	public void remove(Connection connection, int num_linea, int factura) 
			throws InstanceNotFoundException, InternalErrorException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			/* create "preparedStatement" */
			String queryString = "DELETE FROM Linea_Factura " +
					"WHERE num_linea = ? and factura = ?";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			/* fill "preparedStatement" */
			int i = 1;
			preparedStatement.setInt(i++, num_linea);
			preparedStatement.setInt(i++, factura);
			
			/* execute query */
			int removeRows = preparedStatement.executeUpdate();
			
			if (removeRows == 0) {
				throw new InstanceNotFoundException(num_linea, 
						Linea_FacturaVO.class.getName());
			}
		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}
	} // remove

} // class
