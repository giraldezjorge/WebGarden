/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.administrationfacade.vo;

import jgiraldez.j2ee.webgarden.model.linea_factura.vo.Linea_FacturaVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class Linea_FacturaChunkVO implements Serializable {

	private List<Linea_FacturaVO> lineaVOs;
    private boolean existMoreLineas;

    public Linea_FacturaChunkVO(List<Linea_FacturaVO> lineaVOs, 
    		boolean existMoreLineas) {
        
        this.lineaVOs = lineaVOs;
        this.existMoreLineas = existMoreLineas;
        
    }
    
    public List<Linea_FacturaVO> getLineaVOs() {
        
    	return lineaVOs;
    }
    
    public boolean getExistMoreLineas() {
        
    	return existMoreLineas;
    }
    
    public String toString() {
        
    	return new String("lineaVOs = " + lineaVOs +
    			" | existMoreLineas = " + existMoreLineas);
    }
}
