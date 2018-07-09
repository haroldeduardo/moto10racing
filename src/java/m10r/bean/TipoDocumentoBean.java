
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.TipoDocumentoDao;
import m10r.imp.TipoDocumentoImp;
import m10r.model.TipoDocumento;

/**
 *
 * @author CSR
 */

@Named(value = "tipoDocumentoBean")
@ManagedBean
@ViewScoped

public class TipoDocumentoBean implements Serializable {

    private List<TipoDocumento> listaTipoDocumentos;
    private TipoDocumento tipoDocumento;
    
    public TipoDocumentoBean() {
    }

    public void setListaProductos(List<TipoDocumento> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumento> getListaTipoDocumentos() {
        TipoDocumentoDao ubiDao = new TipoDocumentoImp();
        listaTipoDocumentos = ubiDao.mostrarTipoDocumentos();
        return listaTipoDocumentos;
    }
    
    public void reporteTipoDocumentos() throws Exception {
        
        TipoDocumentoImp Dao;
        
        try{
            Dao = new TipoDocumentoImp();
            listaTipoDocumentos = Dao.mostrarTipoDocumentos();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}
