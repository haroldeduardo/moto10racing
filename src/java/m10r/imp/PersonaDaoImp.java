
package m10r.imp;

import java.util.List;
import m10r.dao.PersonaDao;
import m10r.model.Persona;
import m10r.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class PersonaDaoImp implements PersonaDao{

    @Override
    public List<Persona> mostrarPersonas(){
        
        List<Persona> listaPersonas=null;
        Session sessionPersona = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionPersona.beginTransaction();
        String hql="FROM Persona";
        
        try {
            listaPersonas = sessionPersona.createQuery(hql).list();
            t.commit();
            sessionPersona.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaPersonas;
    }

    @Override
    public void ingresarPersona(Persona persona) {
        
        Session sessionPersona = null;
        try {
            sessionPersona = HibernateUtil.getSessionFactory().openSession();
            sessionPersona.beginTransaction();
            sessionPersona.save(persona);
            sessionPersona.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionPersona.getTransaction().rollback();
        } finally {
            if (sessionPersona!=null) {
                sessionPersona.close();
            }
        }
    }

    @Override
    public void actualizarPersona(Persona persona) {
    
        Session sessionPersona = null;
        try {
            sessionPersona = HibernateUtil.getSessionFactory().openSession();
            sessionPersona.beginTransaction();
            sessionPersona.update(persona);
            sessionPersona.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionPersona.getTransaction().rollback();
        } finally {
            if (sessionPersona!=null){
                sessionPersona.close();
            }
        }
    }

    @Override
    public void eliminarPersona(Persona persona) {
    
        Session sessionPersona = null;
        try {
            sessionPersona = HibernateUtil.getSessionFactory().openSession();
            sessionPersona.beginTransaction();
            sessionPersona.delete(persona);
            sessionPersona.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionPersona.getTransaction().rollback();
        } finally {
            if (sessionPersona!=null){
                sessionPersona.close();
            }
        }
    }

    @Override
    public Persona obtenerPersonaPorId(Session sessionPersonaPorId, Integer idPersona) throws Exception {
        String hql="FROM Persona WHERE idPersona=:idPersona";
        Query q = sessionPersonaPorId.createQuery(hql);
        q.setParameter("idPersona", idPersona);
                return (Persona) q.uniqueResult();
    }
    
    @Override
    public Persona obtenerPersonaPorIdentificacion(Session sessionPersonaPorIdentificacion, Integer identificacionPersona) throws Exception {
        String hql="FROM Persona WHERE identificacionPersona=:identificacionPersona";
        Query q = sessionPersonaPorIdentificacion.createQuery(hql);
        q.setParameter("identificacionPersona", identificacionPersona);
                return (Persona) q.uniqueResult();
    }
    
}
