
package m10r.imp;

import java.util.List;
import m10r.dao.CategoriaDao;
import m10r.model.Categoria;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class CategoriaDaoImp implements CategoriaDao{

    @Override
    public List<Categoria> mostrarCategorias(){
        
        List<Categoria> listaCategorias=null;
        Session sessionCategoria = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionCategoria.beginTransaction();
        String hql="FROM Categoria";
        
        try {
            listaCategorias = sessionCategoria.createQuery(hql).list();
            t.commit();
            sessionCategoria.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaCategorias;
    }

    @Override
    public void ingresarCategoria(Categoria categoria) {
        
        Session sessionCategoria = null;
        try {
            sessionCategoria = HibernateUtil.getSessionFactory().openSession();
            sessionCategoria.beginTransaction();
            sessionCategoria.save(categoria);
            sessionCategoria.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionCategoria.getTransaction().rollback();
        } finally {
            if (sessionCategoria!=null) {
                sessionCategoria.close();
            }
        }
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
    
        Session sessionCategoria = null;
        try {
            sessionCategoria = HibernateUtil.getSessionFactory().openSession();
            sessionCategoria.beginTransaction();
            sessionCategoria.update(categoria);
            sessionCategoria.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionCategoria.getTransaction().rollback();
        } finally {
            if (sessionCategoria!=null){
                sessionCategoria.close();
            }
        }
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
    
        Session sessionCategoria = null;
        try {
            sessionCategoria = HibernateUtil.getSessionFactory().openSession();
            sessionCategoria.beginTransaction();
            sessionCategoria.delete(categoria);
            sessionCategoria.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionCategoria.getTransaction().rollback();
        } finally {
            if (sessionCategoria!=null){
                sessionCategoria.close();
            }
        }
    }
    
}
