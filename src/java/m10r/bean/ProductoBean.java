
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.ProductoDao;
import m10r.imp.ProductoDaoImp;
import m10r.model.Producto;

/**
 *
 * @author CSR
 */
@Named(value = "productoBean")
@ManagedBean
@ViewScoped

public class ProductoBean implements Serializable {

    private List<Producto> listaProductos;
    private Producto producto;
    
    public ProductoBean() {
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        ProductoDao catDao = new ProductoDaoImp();
        listaProductos = catDao.mostrarProductos();
        return listaProductos;
    }
    
    public void nuevoProducto(){
        producto = new Producto();
    }
    
    public void ingresarProducto(){
        ProductoDao catDao = new ProductoDaoImp();
        catDao.ingresarProducto(producto);
    }
    
    public void actualizarProducto(){
        ProductoDao catDao = new ProductoDaoImp();
        catDao.actualizarProducto(producto);
        producto = new Producto();
    }
    
    public void eliminarProducto(){
        ProductoDao catDao = new ProductoDaoImp();
        catDao.eliminarProducto(producto);
        producto = new Producto();
    }
    
}
