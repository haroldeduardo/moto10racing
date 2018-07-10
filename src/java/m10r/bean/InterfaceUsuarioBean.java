
package m10r.bean;

import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import m10r.imp.InterfaceUsuarioImp;
import m10r.model.Usuario;
import org.primefaces.context.RequestContext;
import m10r.dao.InterfaceUsuarioDao;

/**
 *
 * @author CSR
 */

@Named(value = "interfaceUsuarioBean")
@SessionScoped

public class InterfaceUsuarioBean implements Serializable {

    private Usuario usuario;
    
    private String username;
     
    private String password;
    
    public InterfaceUsuarioBean() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void loginUsuario(ActionEvent event) {
        RequestContext rc = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String Ruta = "";
        
        InterfaceUsuarioDao uDao = new InterfaceUsuarioImp();
        this.usuario = uDao.ingresoSistema(this.usuario);
       
        if(this.usuario != null) {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUserEmp());
            
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getUserEmp());
            Ruta = "/m10r/faces/views/HomePage.xhtml";
            
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Accesso Invalido", "Verifique Usuario & Password");
            this.usuario = new Usuario();
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        rc.addCallbackParam("loggedIn", loggedIn);
        rc.addCallbackParam("Ruta", Ruta);
    }
    
    public String finalizarSesion(){
        this.username = null;
        this.password = null;
        
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/indexInterface";  
    }
    
}
