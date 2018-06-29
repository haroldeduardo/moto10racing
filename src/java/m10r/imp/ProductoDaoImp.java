
package m10r.imp;

import java.util.List;
import m10r.dao.ProductoDao;
import m10r.model.Producto;
import m10r.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class ProductoDaoImp implements ProductoDao{

    @Override
    public List<Producto> mostrarProductos(){
        
        List<Producto> listaProductos=null;
        Session sessionProducto = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionProducto.beginTransaction();
        String hql="FROM Producto";
        
        try {
            listaProductos = sessionProducto.createQuery(hql).list();
            t.commit();
            sessionProducto.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaProductos;
    }

    @Override
    public void ingresarProducto(Producto producto) {
        
        Session sessionProducto = null;
        try {
            sessionProducto = HibernateUtil.getSessionFactory().openSession();
            sessionProducto.beginTransaction();
            sessionProducto.save(producto);
            sessionProducto.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionProducto.getTransaction().rollback();
        } finally {
            if (sessionProducto!=null) {
                sessionProducto.close();
            }
        }
    }

    @Override
    public void actualizarProducto(Producto producto) {
    
        Session sessionProducto = null;
        try {
            sessionProducto = HibernateUtil.getSessionFactory().openSession();
            sessionProducto.beginTransaction();
            sessionProducto.update(producto);
            sessionProducto.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionProducto.getTransaction().rollback();
        } finally {
            if (sessionProducto!=null){
                sessionProducto.close();
            }
        }
    }

    @Override
    public void eliminarProducto(Producto producto) {
    
        Session sessionProducto = null;
        try {
            sessionProducto = HibernateUtil.getSessionFactory().openSession();
            sessionProducto.beginTransaction();
            sessionProducto.delete(producto);
            sessionProducto.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionProducto.getTransaction().rollback();
        } finally {
            if (sessionProducto!=null){
                sessionProducto.close();
            }
        }
    }
    
    @Override
    public Producto obtenerProductoPorCodigoProducto(Session sessionProductoPorCodigoProducto, String codigoProducto) throws Exception {
        String hql="FROM Producto WHERE codigoProducto=:codigoProducto";
        Query q = sessionProductoPorCodigoProducto.createQuery(hql);
        q.setParameter("codigoProducto", codigoProducto);
                return (Producto) q.uniqueResult();
    }
    
}