
package m10r.dao;

import java.util.List;
import m10r.model.Usuario;

/**
 *
 * @author CSR
 */
public interface UsuarioDao {
    
    public List<Usuario> mostrarUsuarios();
    public void ingresarUsuario(Usuario usuario); 
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    
}
