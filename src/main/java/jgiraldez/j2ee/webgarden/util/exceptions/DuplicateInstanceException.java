/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.exceptions;

/**
 * @author jorge
 *
 */
public class DuplicateInstanceException extends InstanceException {
	
	public DuplicateInstanceException(Object key, String className) {
		
		super("Duplicate instance", key, className);
	}
}
