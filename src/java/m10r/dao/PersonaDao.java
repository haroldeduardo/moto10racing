
package m10r.dao;

import java.util.List;
import m10r.model.Persona;
import org.hibernate.Session;

/**
 *
 * @author CSR
 */
public interface PersonaDao {
    
    public List<Persona> mostrarPersonas();
    public void ingresarPersona(Persona persona); 
    public void actualizarPersona(Persona persona);
    public void eliminarPersona(Persona persona);
    
    public Persona obtenerPersonaPorId(Session sessionPersonaPorId, Integer idPersona) throws Exception;
    
    public Persona obtenerPersonaPorIdentificacion(Session sessionPersonaPorIdentificacion, Integer identificacionPersona) throws Exception;
            
}

