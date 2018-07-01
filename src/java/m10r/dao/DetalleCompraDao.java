
package m10r.dao;

import m10r.model.DetalleCompra;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public interface DetalleCompraDao {
    
    public boolean ingresarDetalleCompra(Session sessionIngresarDetalleCompra, DetalleCompra detalleCompra) throws Exception;
    
}
