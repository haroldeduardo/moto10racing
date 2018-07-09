
package m10r.imp;

import java.util.List;
import m10r.dao.TipoTransaccionDao;
import m10r.model.TipoTransaccion;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class TipoTransaccionImp implements TipoTransaccionDao{
    
    @Override
    public List<TipoTransaccion> mostrarTipoTransacciones(){
        
        List<TipoTransaccion> listaTipoTransacciones=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM TipoTransaccion";
        
        try {
            listaTipoTransacciones = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaTipoTransacciones;
    }

}