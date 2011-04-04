/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.vo;

import jgiraldez.j2ee.webgarden.model.factura.vo.FacturaVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class FacturaChunkVO implements Serializable {

	private List<FacturaVO> facturaVOs;
    private boolean existMoreFacturas;

    public FacturaChunkVO(List<FacturaVO> facturaVOs, 
    		boolean existMoreFacturas) {
        
        this.facturaVOs = facturaVOs;
        this.existMoreFacturas = existMoreFacturas;
        
    }
    
    public List<FacturaVO> getFacturaVOs() {
        
    	return facturaVOs;
    }
    
    public boolean getExistMoreFacturas() {
        
    	return existMoreFacturas;
    }
    
    public String toString() {
        
    	return new String("facturaVOs = " + facturaVOs +
    			" | existMoreFacturas = " + existMoreFacturas);
    }
}
