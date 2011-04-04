/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.sql;

import java.sql.Connection;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.exceptions.ModelException;

/**
 * @author jorge
 *
 */
public interface PlainAction {

	Object execute(Connection connection) 
			throws ModelException, InternalErrorException;
	
}
