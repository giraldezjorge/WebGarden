/**
 * 
 */
package jgiraldez.j2ee.webgarden.util.exceptions;

/**
 * @author jorge
 *
 */
public abstract class ModelException extends Exception {
	
	protected ModelException() {}

    protected ModelException(String message) {
        super(message);
    }

}
