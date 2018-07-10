
package m10r.imp;

import m10r.auxiliares.InteraccionesInterface;
import m10r.model.Usuario;
import m10r.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import m10r.dao.InterfaceUsuarioDao;

/**
 *
 * @author CSR
 */
public class InterfaceUsuarioImp implements InterfaceUsuarioDao{

    @Override
    public Usuario obtenerInformacionPorUsuario(Usuario usuario) {
        Session sessionUsuario = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Usuario WHERE userEmp=:userEmp";
        Query q = sessionUsuario.createQuery(hql);
        q.setParameter("userEmp", usuario.getUserEmp());
        return (Usuario) q.uniqueResult();
    }

    @Override
    public Usuario ingresoSistema(Usuario usuario) {
        Usuario user = this.obtenerInformacionPorUsuario(usuario);
        
        if(user!=null){
            
            if(!user.getPassEmp().equals(InteraccionesInterface.sha512(usuario.getPassEmp()))){
                user = null;
                
            }
        }
        return user;
    }
    
}
