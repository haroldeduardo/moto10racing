package m10r.model;
// Generated Jul 1, 2018 11:19:14 PM by Hibernate Tools 4.3.1



/**
 * Menu generated by hbm2java
 */
public class Menu  implements java.io.Serializable {


     private Integer idMenu;
     private String nombreMenu;
     private String linkMenu;
     private int idModulos;

    public Menu() {
    }

    public Menu(String nombreMenu, String linkMenu, int idModulos) {
       this.nombreMenu = nombreMenu;
       this.linkMenu = linkMenu;
       this.idModulos = idModulos;
    }
   
    public Integer getIdMenu() {
        return this.idMenu;
    }
    
    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }
    public String getNombreMenu() {
        return this.nombreMenu;
    }
    
    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }
    public String getLinkMenu() {
        return this.linkMenu;
    }
    
    public void setLinkMenu(String linkMenu) {
        this.linkMenu = linkMenu;
    }
    public int getIdModulos() {
        return this.idModulos;
    }
    
    public void setIdModulos(int idModulos) {
        this.idModulos = idModulos;
    }




}


