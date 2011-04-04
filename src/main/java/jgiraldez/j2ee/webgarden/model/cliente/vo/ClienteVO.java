/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.cliente.vo;

import java.io.Serializable;

/**
 * @author jorge
 *
 */
public class ClienteVO implements Serializable {

	private String loginName;
	private String NIF;
	private String pass;
	private String pila;
	private String ap1;
	private String ap2;
	private String email;
	private String dir;
	private int cod_postal;
	private int tlf;
	private String poblacion;
	private String provincia;
	private String dir_facturacion;
	private boolean VIP;
	
	public ClienteVO(String loginName, String NIF, String pass, String pila, 
			String ap1, String ap2, String email, String dir, int cod_postal, 
			int tlf, String poblacion, String provincia, 
			String dir_facturacion, boolean VIP) {

		this.loginName = loginName;
		this.NIF = NIF;
		this.pass = pass;
		this.pila = pila;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.email = email;
		this.dir = dir;
		this.cod_postal = cod_postal;
		this.tlf = tlf;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.dir_facturacion = dir_facturacion;
		this.VIP = VIP;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDir_facturacion() {
		return dir_facturacion;
	}
	
	public void setDir_facturacion(String dir_facturacion) {
		this.dir_facturacion = dir_facturacion;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
	
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public boolean getVIP() {
		return VIP;
	}

	public void setVIP(boolean VIP) {
		this.VIP = VIP;
	}
	
}
