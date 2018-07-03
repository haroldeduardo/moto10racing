
package m10r.auxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CSR
 */
public class RestriccionesInterface implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        String currentPage = fc.getViewRoot().getViewId();
        boolean isPageLogin = currentPage.lastIndexOf("indexInterface.xhtml") > -1 ? true : false;
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        Object usuario = session.getAttribute("usuario");
        
        if (!isPageLogin && usuario==null){
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "/indexInterface.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
