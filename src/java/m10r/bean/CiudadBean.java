
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.CiudadDao;
import m10r.imp.CiudadImp;
import m10r.model.Ciudad;

/**
 *
 * @author CSR
 */

@Named(value = "ciudadBean")
@ManagedBean
@ViewScoped

public class CiudadBean implements Serializable {

    private List<Ciudad> listaCiudades;
    private Ciudad ciudad;
    
    public CiudadBean() {
    }

    public void setListaProductos(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getListaCiudades() {
        CiudadDao ubiDao = new CiudadImp();
        listaCiudades = ubiDao.mostrarCiudades();
        return listaCiudades;
    }
    
    public void reporteCiudades() throws Exception {
        
        CiudadImp Dao;
        
        try{
            Dao = new CiudadImp();
            listaCiudades = Dao.mostrarCiudades();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}