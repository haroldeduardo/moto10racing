
package m10r.bean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import m10r.dao.PersonaDao;
import m10r.imp.PersonaDaoImp;
import m10r.model.Persona;

/**
 *
 * @author CSR
 */
@Named(value = "personaBean")
@ManagedBean
@ViewScoped

public class PersonaBean implements Serializable {

    private List<Persona> listaPersonas;
    private Persona persona;
    
    public PersonaBean() {
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersonas() {
        PersonaDao catDao = new PersonaDaoImp();
        listaPersonas = catDao.mostrarPersonas();
        return listaPersonas;
    }
    
    public void nuevaPersona(){
        persona = new Persona();
    }
    
    public void ingresarPersona(){
        PersonaDao catDao = new PersonaDaoImp();
        catDao.ingresarPersona(persona);
    }
    
    public void actualizarPersona(){
        PersonaDao catDao = new PersonaDaoImp();
        catDao.actualizarPersona(persona);
        persona = new Persona();
    }
    
    public void eliminarPersona(){
        PersonaDao catDao = new PersonaDaoImp();
        catDao.eliminarPersona(persona);
        persona = new Persona();
    }
    
}
