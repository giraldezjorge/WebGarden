/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.productsmanagementfacade.vo;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class CategoriaChunkVO implements Serializable {

	private List<CategoriaVO> categoriaVOs;
    private boolean existMoreCategorias;

    public CategoriaChunkVO(List<CategoriaVO> categoriaVOs, 
    		boolean existMoreCategorias) {
        
        this.categoriaVOs = categoriaVOs;
        this.existMoreCategorias = existMoreCategorias;
        
    }
    
    public List<CategoriaVO> getCategoriaVOs() {
        
    	return categoriaVOs;
    }
    
    public boolean getExistMoreCategorias() {
        
    	return existMoreCategorias;
    }
    
    public String toString() {
        
    	return new String("categoriaVOs = " + categoriaVOs +
    			" | existMoreCategorias = " + existMoreCategorias);
    }  

}
