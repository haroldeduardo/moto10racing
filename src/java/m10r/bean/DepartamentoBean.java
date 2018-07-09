
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.DepartamentoDao;
import m10r.imp.DepartamentoImp;
import m10r.model.Departamento;

/**
 *
 * @author CSR
 */

@Named(value = "departamentoBean")
@ManagedBean
@ViewScoped

public class DepartamentoBean implements Serializable {

    private List<Departamento> listaDepartamentos;
    private Departamento departamento;
    
    public DepartamentoBean() {
    }

    public void setListaProductos(List<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamentos() {
        DepartamentoDao ubiDao = new DepartamentoImp();
        listaDepartamentos = ubiDao.mostrarDepartamentos();
        return listaDepartamentos;
    }
    
    public void reporteDepartamentos() throws Exception {
        
        DepartamentoImp Dao;
        
        try{
            Dao = new DepartamentoImp();
            listaDepartamentos = Dao.mostrarDepartamentos();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}