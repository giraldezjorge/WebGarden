/**
 * 
 */
package jgiraldez.j2ee.webgarden.http.controller.session;

import java.sql.Timestamp;

/**
 * @author jorge
 *
 */
public class PrintManager {

	private String firstName;
	private String surname1;
	private String surname2;
	private String productName;
	private String categoryName;
	private String fatherName;
	private int num_linea;
	private int factura;	
	private int cantidad;
	private double descuento;
	private double total;
	private int idFactura;
	private Timestamp fecha;
	private boolean pagada;
	private double totalFactura;
	private String dir;
	private String NIF;
	private String poblacion;
	private String provincia;
	private int tlf;
	private String email;
	
	public PrintManager() {
		
		this.firstName = null;
		this.surname1 = null;
		this.surname2 = null;
		this.productName = null;
		this.categoryName = null;
		this.fatherName = null;
		this.num_linea = 0;
		this.factura = 0;
		this.cantidad = 0;
		this.descuento = 0;
		this.total = 0;
		this.idFactura = 0;
		this.fecha = null;
		this.pagada = false;
		this.totalFactura = 0;
		this.dir = null;
		this.NIF = null;
		this.poblacion = null;
		this.provincia = null;
		this.tlf = 0;
		this.email = null;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public int getNum_linea() {
		return num_linea;
	}

	public void setNum_linea(int numLinea) {
		num_linea = numLinea;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public boolean isPagada() {
		return pagada;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
