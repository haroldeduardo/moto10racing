package m10r.model;
// Generated Jun 29, 2018 3:25:40 PM by Hibernate Tools 4.3.1



/**
 * Departamento generated by hbm2java
 */
public class Departamento  implements java.io.Serializable {


     private Integer idDepartamento;
     private String nombreDepartamento;

    public Departamento() {
    }

    public Departamento(String nombreDepartamento) {
       this.nombreDepartamento = nombreDepartamento;
    }
   
    public Integer getIdDepartamento() {
        return this.idDepartamento;
    }
    
    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }
    
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }




}


