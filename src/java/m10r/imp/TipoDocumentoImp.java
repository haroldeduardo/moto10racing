
package m10r.imp;

import java.util.List;
import m10r.dao.TipoDocumentoDao;
import m10r.model.TipoDocumento;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class TipoDocumentoImp implements TipoDocumentoDao{
    
    @Override
    public List<TipoDocumento> mostrarTipoDocumentos(){
        
        List<TipoDocumento> listaTipoDocumentos=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM TipoDocumento";
        
        try {
            listaTipoDocumentos = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaTipoDocumentos;
    }

}