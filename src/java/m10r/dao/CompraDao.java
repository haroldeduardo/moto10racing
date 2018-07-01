
package m10r.dao;

import m10r.model.Compra;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public interface CompraDao {
    
    public Compra obtenerUltimoRegistroCompra( Session sessionUltimoRegistroCompra) throws Exception;
    
    public Long obtenerTotalRegistrosCompra(Session sessionRegistrosCompra);
    
    public boolean ingresarCompra(Session sessionIngresarCompra, Compra compra) throws Exception;
    
}
