/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.session;

import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jorge
 *
 */
public class BillManager {

	private static HashMap<String, BillManager> instances = 
			new HashMap<String, BillManager>();
	private String NIF;
	private Map<Integer, Linea_FacturaVO> lines = 
								new HashMap<Integer, Linea_FacturaVO>();
	
	private BillManager(String NIF) {
		
		this.NIF = NIF;
	}
	
	public static BillManager getInstance(String NIF) {
		if (!instances.containsKey(NIF)) {
			instances.put(NIF, new BillManager(NIF));
		}
		return instances.get(NIF);
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}
	
	public Collection<Linea_FacturaVO> getLines() {
		return this.lines.values();
	}
	
	public void addLine(Linea_FacturaVO line) {
		this.lines.put(line.getNum_linea(), line);
	}
	
	public void deleteLine(int num_line) {
		Integer num = new Integer(num_line);
		this.lines.remove(num); 
	}
	
}
