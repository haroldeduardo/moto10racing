
package m10r.dao;

import m10r.model.Venta;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public interface VentaDao {
    
    public Venta obtenerUltimoRegistroVenta( Session sessionUltimoRegistroVenta) throws Exception;
    
    public Long obtenerTotalRegistrosVenta(Session sessionRegistrosVenta);
    
    public boolean ingresarVenta(Session sessionIngresarVenta, Venta venta) throws Exception;
    
}
