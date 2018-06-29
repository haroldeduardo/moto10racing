
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.EmpleadoDao;
import m10r.imp.EmpleadoDaoImp;
import m10r.model.Empleado;

/**
 *
 * @author CSR
 */
@Named(value = "empleadoBean")
@ManagedBean
@ViewScoped

public class EmpleadoBean implements Serializable {

    private List<Empleado> listaEmpleados;
    private Empleado empleado;
    
    public EmpleadoBean() {
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListaEmpleados() {
        EmpleadoDao catDao = new EmpleadoDaoImp();
        listaEmpleados = catDao.mostrarEmpleados();
        return listaEmpleados;
    }
    
    public void nuevoEmpleado(){
        empleado = new Empleado();
    }
    
    public void ingresarEmpleado(){
        EmpleadoDao catDao = new EmpleadoDaoImp();
        catDao.ingresarEmpleado(empleado);
    }
    
    public void actualizarEmpleado(){
        EmpleadoDao catDao = new EmpleadoDaoImp();
        catDao.actualizarEmpleado(empleado);
        empleado = new Empleado();
    }
    
    public void eliminarEmpleado(){
        EmpleadoDao catDao = new EmpleadoDaoImp();
        catDao.eliminarEmpleado(empleado);
        empleado = new Empleado();
    }
    
}
