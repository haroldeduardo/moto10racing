package m10r.model;
// Generated Jun 29, 2018 3:25:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * CreditoCompra generated by hbm2java
 */
public class CreditoCompra  implements java.io.Serializable {


     private Integer idCreditoCompra;
     private int idEmpleado;
     private int idPersona;
     private float creditoNuevo;
     private Date fechaAbono;

    public CreditoCompra() {
    }

    public CreditoCompra(int idEmpleado, int idPersona, float creditoNuevo, Date fechaAbono) {
       this.idEmpleado = idEmpleado;
       this.idPersona = idPersona;
       this.creditoNuevo = creditoNuevo;
       this.fechaAbono = fechaAbono;
    }
   
    public Integer getIdCreditoCompra() {
        return this.idCreditoCompra;
    }
    
    public void setIdCreditoCompra(Integer idCreditoCompra) {
        this.idCreditoCompra = idCreditoCompra;
    }
    public int getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public int getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public float getCreditoNuevo() {
        return this.creditoNuevo;
    }
    
    public void setCreditoNuevo(float creditoNuevo) {
        this.creditoNuevo = creditoNuevo;
    }
    public Date getFechaAbono() {
        return this.fechaAbono;
    }
    
    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
    }




}


