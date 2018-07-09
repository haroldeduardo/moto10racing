
package m10r.imp;

import java.util.List;
import m10r.dao.DepartamentoDao;
import m10r.model.Departamento;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class DepartamentoImp implements DepartamentoDao{

    @Override
    public List<Departamento> mostrarDepartamentos() {
        
        List<Departamento> listaDepartamentos=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM Departamento";
        
        try {
            listaDepartamentos = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaDepartamentos;
    }
    
}
