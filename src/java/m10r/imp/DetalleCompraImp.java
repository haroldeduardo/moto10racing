
package m10r.imp;

import m10r.dao.DetalleCompraDao;
import m10r.model.DetalleCompra;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public class DetalleCompraImp implements DetalleCompraDao{

    @Override
    public boolean ingresarDetalleCompra(Session sessionIngresarDetalleCompra, DetalleCompra detalleCompra) throws Exception {
        sessionIngresarDetalleCompra.save(detalleCompra);
        return true; 
    }
    
}
