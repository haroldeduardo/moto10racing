
package m10r.imp;

import java.util.List;
import m10r.dao.PresentacionProductoDao;
import m10r.model.PresentacionProducto;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class PresentacionProductoImp implements PresentacionProductoDao{
    
    @Override
    public List<PresentacionProducto> mostrarPresentacionProductos(){
        
        List<PresentacionProducto> listaPresentacionProductos=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM PresentacionProducto";
        
        try {
            listaPresentacionProductos = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaPresentacionProductos;
    }

}
