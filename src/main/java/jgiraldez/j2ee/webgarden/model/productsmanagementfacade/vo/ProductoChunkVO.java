/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo;

import jgiraldez.j2ee.webgarden.model.producto.vo.ProductoVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class ProductoChunkVO implements Serializable {

	private List<ProductoVO> productoVOs;
    private boolean existMoreProductos;

    public ProductoChunkVO(List<ProductoVO> productoVOs, 
    		boolean existMoreProductos) {
        
        this.productoVOs = productoVOs;
        this.existMoreProductos = existMoreProductos;
        
    }
    
    public List<ProductoVO> getProductoVOs() {
        
    	return productoVOs;
    }
    
    public boolean getExistMoreProductos() {
        
    	return existMoreProductos;
    }
    
    public String toString() {
        
    	return new String("productoVOs = " + productoVOs +
    			" | existMoreProductos = " + existMoreProductos);
    }
}
