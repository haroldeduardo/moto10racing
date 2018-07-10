
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.UsuarioDao;
import m10r.imp.UsuarioImp;
import m10r.model.Usuario;

/**
 *
 * @author CSR
 */

@Named(value = "usuarioBean")
@ManagedBean
@ViewScoped

public class UsuarioBean implements Serializable {

    private List<Usuario> listaUsuarios;
    private Usuario usuario = new Usuario();
    
    public UsuarioBean() {
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        UsuarioDao catDao = new UsuarioImp();
        listaUsuarios = catDao.mostrarUsuarios();
        return listaUsuarios;
    }
    
    public void nuevoUsuario(){
        usuario = new Usuario();
    }
    
    public void ingresarUsuario(){
        UsuarioDao catDao = new UsuarioImp();
        catDao.ingresarUsuario(usuario);
    }
    
    public void actualizarUsuario(){
        UsuarioDao catDao = new UsuarioImp();
        catDao.actualizarUsuario(usuario);
        usuario = new Usuario();
    }
    
    public void eliminarUsuario(){
        UsuarioDao catDao = new UsuarioImp();
        catDao.eliminarUsuario(usuario);
        usuario = new Usuario();
    }
    
    public void reporteUsuarios() throws Exception {
        
        UsuarioImp Dao;
        
        try{
            Dao = new UsuarioImp();
            listaUsuarios = Dao.mostrarUsuarios();
        }
        catch(Exception e){
        throw e;
        }
    }
        
}