
package m10r.dao;

import java.util.List;
import m10r.model.Empleado;

/**
 *
 * @author CSR
 */
public interface EmpleadoDao {
    
    public List<Empleado> mostrarEmpleados();
    public void ingresarEmpleado(Empleado empleado); 
    public void actualizarEmpleado(Empleado empleado);
    public void eliminarEmpleado(Empleado empleado);
            
}
