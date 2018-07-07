
package m10r.imp;

import java.util.List;
import m10r.dao.UbicacionProductoDao;
import m10r.model.UbicacionProducto;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class UbicacionProductoImp implements UbicacionProductoDao{
    
    @Override
    public List<UbicacionProducto> mostrarUbicacionProductos(){
        
        List<UbicacionProducto> listaUbicacionProductos=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM UbicacionProducto";
        
        try {
            listaUbicacionProductos = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaUbicacionProductos;
    }

}
