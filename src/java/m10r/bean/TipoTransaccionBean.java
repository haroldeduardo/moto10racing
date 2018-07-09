
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.TipoTransaccionDao;
import m10r.imp.TipoTransaccionImp;
import m10r.model.TipoTransaccion;

/**
 *
 * @author CSR
 */

@Named(value = "tipoTransaccionBean")
@ManagedBean
@ViewScoped

public class TipoTransaccionBean implements Serializable {

    private List<TipoTransaccion> listaTipoTransacciones;
    private TipoTransaccion tipoTransaccion;
    
    public TipoTransaccionBean() {
    }

    public void setListaProductos(List<TipoTransaccion> listaTipoTransacciones) {
        this.listaTipoTransacciones = listaTipoTransacciones;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public List<TipoTransaccion> getListaTipoTransacciones() {
        TipoTransaccionDao ubiDao = new TipoTransaccionImp();
        listaTipoTransacciones = ubiDao.mostrarTipoTransacciones();
        return listaTipoTransacciones;
    }
    
    public void reporteTipoTransacciones() throws Exception {
        
        TipoTransaccionImp Dao;
        
        try{
            Dao = new TipoTransaccionImp();
            listaTipoTransacciones = Dao.mostrarTipoTransacciones();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}