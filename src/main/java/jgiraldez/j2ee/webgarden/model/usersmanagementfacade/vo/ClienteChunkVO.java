/**
 * 
 */
package jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo;

import jgiraldez.j2ee.webgarden.model.cliente.vo.ClienteVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author jorge
 *
 */
public class ClienteChunkVO implements Serializable {

	private List<ClienteVO> clienteVOs;
    private boolean existMoreClientes;

    public ClienteChunkVO(List<ClienteVO> clienteVOs, boolean existMoreClientes) {
        
        this.clienteVOs = clienteVOs;
        this.existMoreClientes = existMoreClientes;
        
    }
    
    public List<ClienteVO> getClienteVOs() {
        
    	return clienteVOs;
    }
    
    public boolean getExistMoreClientes() {
        
    	return existMoreClientes;
    }
    
    public String toString() {
        
    	return new String("clienteVOs = " + clienteVOs + " | existMoreClientes = " + existMoreClientes);
    }
}
