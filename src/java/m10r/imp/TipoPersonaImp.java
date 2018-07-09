
package m10r.imp;

import java.util.List;
import m10r.dao.TipoPersonaDao;
import m10r.model.TipoPersona;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class TipoPersonaImp implements TipoPersonaDao{
    
    @Override
    public List<TipoPersona> mostrarTipoPersonas(){
        
        List<TipoPersona> listaTipoPersonas=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM TipoPersona";
        
        try {
            listaTipoPersonas = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaTipoPersonas;
    }

}