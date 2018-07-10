
package m10r.imp;

import m10r.dao.VentaDao;
import m10r.model.Venta;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public class VentaImp implements VentaDao{

    @Override
    public Venta obtenerUltimoRegistroVenta(Session sessionUltimoRegistroVenta) throws Exception {
        String hql = "FROM Venta ORDER BY idVenta DESC";
        Query q = sessionUltimoRegistroVenta.createQuery(hql).setMaxResults(1);
        return (Venta) q.uniqueResult();
    }

    @Override
    public Long obtenerTotalRegistrosVenta(Session sessionRegistrosVenta) {
        String hql = "SELECT COUNT(*) FROM Venta";
        Query q = sessionRegistrosVenta.createQuery(hql);
        return (Long) q.uniqueResult();
    }

    @Override
    public boolean ingresarVenta(Session sessionIngresarVenta, Venta venta) throws Exception {
        sessionIngresarVenta.save(venta);
        return true;
    } 
    
}