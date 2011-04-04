/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo;

import jgiraldez.j2ee.webgarden.model.empleado.vo.EmpleadoVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class EmpleadoChunkVO implements Serializable {

	private List<EmpleadoVO> empleadoVOs;
    private boolean existMoreEmpleados;

    public EmpleadoChunkVO(List<EmpleadoVO> empleadoVOs, 
    		boolean existMoreEmpleados) {
        
        this.empleadoVOs = empleadoVOs;
        this.existMoreEmpleados = existMoreEmpleados;
        
    }
    
    public List<EmpleadoVO> getEmpleadoVOs() {
        
    	return empleadoVOs;
    }
    
    public boolean getExistMoreEmpleados() {
        
    	return existMoreEmpleados;
    }
    
    public String toString() {
        
    	return new String("empleadoVOs = " + empleadoVOs +
    			" | existMoreEmpleados = " + existMoreEmpleados);
    }
}
