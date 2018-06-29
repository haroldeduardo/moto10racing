
package m10r.dao;

import java.util.List;
import m10r.model.Categoria;

/**
 *
 * @author CSR
 */
public interface CategoriaDao {
    
    public List<Categoria> mostrarCategorias();
    public void ingresarCategoria(Categoria categoria); 
    public void actualizarCategoria(Categoria categoria);
    public void eliminarCategoria(Categoria categoria);
            
}
