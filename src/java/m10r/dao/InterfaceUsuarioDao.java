
package m10r.dao;

import m10r.model.Usuario;

/**
 *
 * @author CSR
 */
public interface InterfaceUsuarioDao {
    
    public Usuario obtenerInformacionPorUsuario(Usuario usuario);
    
    public Usuario ingresoSistema(Usuario usuario);
    
}
