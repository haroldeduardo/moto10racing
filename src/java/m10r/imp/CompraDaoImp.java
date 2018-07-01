
package m10r.imp;

import m10r.dao.CompraDao;
import m10r.model.Compra;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public class CompraDaoImp implements CompraDao{

    @Override
    public Compra obtenerUltimoRegistroCompra(Session sessionUltimoRegistroCompra) throws Exception {
        String hql = "FROM Compra ORDER BY idCompra DESC";
        Query q = sessionUltimoRegistroCompra.createQuery(hql).setMaxResults(1);
        return (Compra) q.uniqueResult();
    }

    @Override
    public Long obtenerTotalRegistrosCompra(Session sessionRegistrosCompra) {
        String hql = "SELECT COUNT(*) FROM Compra";
        Query q = sessionRegistrosCompra.createQuery(hql);
        return (Long) q.uniqueResult();
    }

    @Override
    public boolean ingresarCompra(Session sessionIngresarCompra, Compra compra) throws Exception {
        sessionIngresarCompra.save(compra);
        return true;
    } 
    
}
