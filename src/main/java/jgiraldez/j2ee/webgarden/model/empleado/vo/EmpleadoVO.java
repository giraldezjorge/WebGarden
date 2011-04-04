/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.empleado.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class EmpleadoVO implements Serializable {

	private String NIF;
	private String pass;
	private String pila;
	private String ap1;
	private String ap2;
	private String dir;
	private int cod_postal;
	private int tlf;
	private String poblacion;
	private String provincia;
	private long num_cuenta;
	private boolean esAdmin;
	
	public EmpleadoVO(String NIF,String pass, String pila, String ap1, 
			String ap2, String dir, int cod_postal, int tlf, String poblacion, 
			String provincia, long num_cuenta, boolean esAdmin) {

		this.NIF = NIF;
		this.pass = pass;
		this.pila = pila;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.dir = dir;
		this.cod_postal = cod_postal;
		this.tlf = tlf;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.num_cuenta = num_cuenta;
		this.esAdmin = esAdmin;
	}
	
	public long getNum_cuenta() {
		return num_cuenta;
	}
	
	public void setNum_cuenta(long cuenta) {
		this.num_cuenta = cuenta;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAp1() {
		return ap1;
	}

	public void setAp1(String ap1) {
		this.ap1 = ap1;
	}

	public String getAp2() {
		return ap2;
	}

	public void setAp2(String ap2) {
		this.ap2 = ap2;
	}

	public int getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getPila() {
		return pila;
	}

	public void setPila(String pila) {
		this.pila = pila;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}

	public String getNIF() {
		return NIF;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
}
