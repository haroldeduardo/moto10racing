package m10r.model;
// Generated Jun 29, 2018 3:25:40 PM by Hibernate Tools 4.3.1



/**
 * PresentacionProducto generated by hbm2java
 */
public class PresentacionProducto  implements java.io.Serializable {


     private Integer idPresentacionProducto;
     private String descripcionPresentacionPro;

    public PresentacionProducto() {
    }

    public PresentacionProducto(String descripcionPresentacionPro) {
       this.descripcionPresentacionPro = descripcionPresentacionPro;
    }
   
    public Integer getIdPresentacionProducto() {
        return this.idPresentacionProducto;
    }
    
    public void setIdPresentacionProducto(Integer idPresentacionProducto) {
        this.idPresentacionProducto = idPresentacionProducto;
    }
    public String getDescripcionPresentacionPro() {
        return this.descripcionPresentacionPro;
    }
    
    public void setDescripcionPresentacionPro(String descripcionPresentacionPro) {
        this.descripcionPresentacionPro = descripcionPresentacionPro;
    }




}


