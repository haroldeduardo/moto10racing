
package m10r.imp;

import java.util.List;
import m10r.dao.EmpleadoDao;
import m10r.model.Empleado;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CSR
 */
public class EmpleadoDaoImp implements EmpleadoDao{

    @Override
    public List<Empleado> mostrarEmpleados(){
        
        List<Empleado> listaEmpleados=null;
        Session sessionEmpleado = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessionEmpleado.beginTransaction();
        String hql="FROM Empleado";
        
        try {
            listaEmpleados = sessionEmpleado.createQuery(hql).list();
            t.commit();
            sessionEmpleado.close();
        } catch (Exception e) {
            t.rollback();
        }
        return listaEmpleados;
    }

    @Override
    public void ingresarEmpleado(Empleado empleado) {
        
        Session sessionEmpleado = null;
        try {
            sessionEmpleado = HibernateUtil.getSessionFactory().openSession();
            sessionEmpleado.beginTransaction();
            sessionEmpleado.save(empleado);
            sessionEmpleado.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionEmpleado.getTransaction().rollback();
        } finally {
            if (sessionEmpleado!=null) {
                sessionEmpleado.close();
            }
        }
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
    
        Session sessionEmpleado = null;
        try {
            sessionEmpleado = HibernateUtil.getSessionFactory().openSession();
            sessionEmpleado.beginTransaction();
            sessionEmpleado.update(empleado);
            sessionEmpleado.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionEmpleado.getTransaction().rollback();
        } finally {
            if (sessionEmpleado!=null){
                sessionEmpleado.close();
            }
        }
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
    
        Session sessionEmpleado = null;
        try {
            sessionEmpleado = HibernateUtil.getSessionFactory().openSession();
            sessionEmpleado.beginTransaction();
            sessionEmpleado.delete(empleado);
            sessionEmpleado.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionEmpleado.getTransaction().rollback();
        } finally {
            if (sessionEmpleado!=null){
                sessionEmpleado.close();
            }
        }
    }
    
}
