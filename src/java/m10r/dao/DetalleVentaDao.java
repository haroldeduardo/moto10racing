
package m10r.dao;

import m10r.model.DetalleVenta;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public interface DetalleVentaDao {
    
    public boolean ingresarDetalleVenta(Session sessionIngresarDetalleVenta, DetalleVenta detalleVenta) throws Exception;
    
}