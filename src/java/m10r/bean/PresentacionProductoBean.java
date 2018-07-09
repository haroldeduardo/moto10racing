
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.PresentacionProductoDao;
import m10r.imp.PresentacionProductoImp;
import m10r.model.PresentacionProducto;

/**
 *
 * @author CSR
 */

@Named(value = "presentacionProductoBean")
@ManagedBean
@ViewScoped

public class PresentacionProductoBean implements Serializable {

    private List<PresentacionProducto> listaPresentacionProductos;
    private PresentacionProducto presentacionProducto;
    
    public PresentacionProductoBean() {
    }

    public void setListaProductos(List<PresentacionProducto> listaPresentacionProductos) {
        this.listaPresentacionProductos = listaPresentacionProductos;
    }

    public PresentacionProducto getPresentacionProducto() {
        return presentacionProducto;
    }

    public void setPresentacionProducto(PresentacionProducto presentacionProducto) {
        this.presentacionProducto = presentacionProducto;
    }

    public List<PresentacionProducto> getListaPresentacionProductos() {
        PresentacionProductoDao ubiDao = new PresentacionProductoImp();
        listaPresentacionProductos = ubiDao.mostrarPresentacionProductos();
        return listaPresentacionProductos;
    }
    
    public void reportePresentacionesProducto() throws Exception {
        
        PresentacionProductoImp Dao;
        
        try{
            Dao = new PresentacionProductoImp();
            listaPresentacionProductos = Dao.mostrarPresentacionProductos();
        }
        catch(Exception e){
        throw e;
        }
    }
    
}
