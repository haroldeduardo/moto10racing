
package m10r.imp;

import java.util.List;
import m10r.dao.CiudadDao;
import m10r.model.Ciudad;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class CiudadImp implements CiudadDao{

        @Override
    public List<Ciudad> mostrarCiudades() {
        
        List<Ciudad> listaCiudades=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM Ciudad";
        
        try {
            listaCiudades = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaCiudades;
    }
    
}
