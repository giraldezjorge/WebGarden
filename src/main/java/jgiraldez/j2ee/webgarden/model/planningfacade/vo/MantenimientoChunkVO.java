/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.planningfacade.vo;

import jgiraldez.j2ee.webgarden.model.mantenimiento.vo.MantenimientoVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class MantenimientoChunkVO implements Serializable {

	private List<MantenimientoVO> mantenimientoVOs;
    private boolean existMoreMantenimientos;

    public MantenimientoChunkVO(List<MantenimientoVO> mantenimientoVOs, 
    		boolean existMoreMantenimientos) {
        
        this.mantenimientoVOs = mantenimientoVOs;
        this.existMoreMantenimientos = existMoreMantenimientos;
        
    }
    
    public List<MantenimientoVO> getMantenimientoVOs() {
        
    	return mantenimientoVOs;
    }
    
    public boolean getExistMoreMantenimientos() {
        
    	return existMoreMantenimientos;
    }
    
    public String toString() {
        
    	return new String("mantenimientoVOs = " + mantenimientoVOs +
    			" | existMoreMantenimientos = " + existMoreMantenimientos);
    }
}
