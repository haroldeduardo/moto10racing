
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.CategoriaDao;
import m10r.imp.CategoriaDaoImp;
import m10r.model.Categoria;

/**
 *
 * @author CSR
 */
@Named(value = "categoriaBean")
@ManagedBean
@ViewScoped

public class CategoriaBean implements Serializable {

    private List<Categoria> listaCategorias;
    private Categoria categoria;
    
    public CategoriaBean() {
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategorias() {
        CategoriaDao catDao = new CategoriaDaoImp();
        listaCategorias = catDao.mostrarCategorias();
        return listaCategorias;
    }
    
    public void nuevaCategoria(){
        categoria = new Categoria();
    }
    
    public void ingresarCategoria(){
        CategoriaDao catDao = new CategoriaDaoImp();
        catDao.ingresarCategoria(categoria);
    }
    
    public void actualizarCategoria(){
        CategoriaDao catDao = new CategoriaDaoImp();
        catDao.actualizarCategoria(categoria);
        categoria = new Categoria();
    }
    
    public void eliminarCategoria(){
        CategoriaDao catDao = new CategoriaDaoImp();
        catDao.eliminarCategoria(categoria);
        categoria = new Categoria();
    }
        
}
