
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.TipoPersonaDao;
import m10r.imp.TipoPersonaImp;
import m10r.model.TipoPersona;

/**
 *
 * @author CSR
 */

@Named(value = "tipoPersonaBean")
@ManagedBean
@ViewScoped

public class TipoPersonaBean implements Serializable {

    private List<TipoPersona> listaTipoPersonas;
    private TipoPersona tipoPersona;
    
    public TipoPersonaBean() {
    }

    public void setListaProductos(List<TipoPersona> listaTipoPersonas) {
        this.listaTipoPersonas = listaTipoPersonas;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<TipoPersona> getListaTipoPersonas() {
        TipoPersonaDao ubiDao = new TipoPersonaImp();
        listaTipoPersonas = ubiDao.mostrarTipoPersonas();
        return listaTipoPersonas;
    }
    
    public void reportePersonas() throws Exception {
        
        TipoPersonaImp Dao;
        
        try{
            Dao = new TipoPersonaImp();
            listaTipoPersonas = Dao.mostrarTipoPersonas();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}