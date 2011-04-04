package jgiraldez.j2ee.webgarden.util.exceptions;


import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author jorge
 *
 */
public class InternalErrorException extends Exception {
	
	private Exception encapsulatedException;
	
	public InternalErrorException(Exception exception) {
		encapsulatedException = exception;
	}
	
	public void printStackTrace() {
		printStackTrace(System.err);
	}
	
	public void printStackTrace(PrintStream printStream) {
		super.printStackTrace(printStream);
		printStream.println("***Information about encapsulated exception***");
		encapsulatedException.printStackTrace(printStream);
	}
	
	public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        printWriter.println("***Information about encapsulated exception***");
        encapsulatedException.printStackTrace(printWriter);
    }
}
