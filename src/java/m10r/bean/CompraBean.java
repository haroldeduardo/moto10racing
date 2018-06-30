
package m10r.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import m10r.dao.PersonaDao;
import m10r.dao.ProductoDao;
import m10r.imp.PersonaDaoImp;
import m10r.imp.ProductoDaoImp;
import m10r.model.Compra;
import m10r.model.DetalleCompra;
import m10r.model.Persona;
import m10r.model.Producto;
import m10r.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author CSR
 */
@Named(value = "compraBean")
@ViewScoped
@ManagedBean
public class CompraBean implements Serializable {
    
    Session sessionCompra=null;
    Transaction transactionCompra=null;
    
    private Persona persona;
    private Integer identificacionPersona;
    
    private Producto producto;
    private String codigoProducto;
    
    private List<DetalleCompra> listaDetalleCompra;
    
    private String unidadesCompradas;
    private String productoSeleccionado;
    private Compra compra;
    
    private String unidadesCompradasPorCodigo;

    public CompraBean() {
        this.listaDetalleCompra = new ArrayList<>();
        this.compra = new Compra();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Integer getIdentificacionPersona() {
        return identificacionPersona;
    }

    public void setIdentificacionPersona(Integer identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public List<DetalleCompra> getListaDetalleCompra() {
        return listaDetalleCompra;
    }

    public void setListaDetalleCompra(List<DetalleCompra> listaDetalleCompra) {
        this.listaDetalleCompra = listaDetalleCompra;
    }

    public String getUnidadesCompradas() {
        return unidadesCompradas;
    }

    public void setUnidadesCompradas(String unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public String getUnidadesCompradasPorCodigo() {
        return unidadesCompradasPorCodigo;
    }

    public void setUnidadesCompradasPorCodigo(String unidadesCompradasPorCodigo) {
        this.unidadesCompradasPorCodigo = unidadesCompradasPorCodigo;
    }
    
    public void agregarDatosPersona(Integer idPersona){
        this.sessionCompra=null;
        this.transactionCompra=null;
        
        try {
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            PersonaDao pDao = new PersonaDaoImp();
            this.transactionCompra = this.sessionCompra.beginTransaction();
            this.persona = pDao.obtenerPersonaPorId(this.sessionCompra, idPersona);
            this.transactionCompra.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Proveedor Agregado"));
        } catch (Exception e) {
            if (this.transactionCompra!=null){
                System.out.println(e.getMessage());
                transactionCompra.rollback();
            }
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
                
        }
        
    }
    
    public void agregarDatosPersonaPorIdentificacion(){
        this.sessionCompra=null;
        this.transactionCompra=null;
        
        try {
            if (this.identificacionPersona==null){
                return;
            }
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            PersonaDao pDao = new PersonaDaoImp();
            this.transactionCompra = this.sessionCompra.beginTransaction();
            this.persona = pDao.obtenerPersonaPorIdentificacion(this.sessionCompra, this.identificacionPersona);
            if (this.persona!=null){
                this.identificacionPersona=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Proveedor Agregado"));
            } else {
                this.identificacionPersona=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Proveedor Inexistente"));
            }
            this.transactionCompra.commit();
        } catch (Exception e) {
            if (this.transactionCompra!=null){
                System.out.println(e.getMessage());
                transactionCompra.rollback();
            }
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
                
        }
        
    }
    
    public void solicitarCantidadProducto(String codigoProducto){
        this.productoSeleccionado = codigoProducto;
    }
    
    public void agregarDatosProductoPorCodigoProducto(){
        this.sessionCompra=null;
        this.transactionCompra=null;
        
        try {
            
            if (!(this.unidadesCompradas.matches("[0-9]*")) || this.unidadesCompradas.equals("0") || this.unidadesCompradas.equals("")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Valor Incorrecto"));
                this.unidadesCompradas = "";
            
            } else {
                
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            ProductoDao pDao = new ProductoDaoImp();
            this.transactionCompra = this.sessionCompra.beginTransaction();
            this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionCompra, this.productoSeleccionado);
            
            this.listaDetalleCompra.add(new DetalleCompra(0, 0, this.producto.getCodigoProducto(), this.producto.getNombreProducto(), this.producto.getValorCompraProducto(), this.producto.getValorVentaProducto(), Integer.parseInt(this.unidadesCompradas), (Float.parseFloat(this.unidadesCompradas)*this.producto.getValorCompraProducto())));
            
            this.transactionCompra.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Producto Agregado"));
            
            this.unidadesCompradas="";
            
            this.calcularValorTotalCompra();
                
            }
             
        } catch (Exception e) {
            if (this.transactionCompra!=null){
                System.out.println(e.getMessage());
                transactionCompra.rollback();
            }
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
                
        }
        
    }
    
    public void mostrarDatosCantidadProductoPorCodigo(){
        this.sessionCompra=null;
        this.transactionCompra=null;
        
        try {
            if (this.codigoProducto==null){
                return;
            }
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            ProductoDao pDao = new ProductoDaoImp();
            this.transactionCompra = this.sessionCompra.beginTransaction();
            this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionCompra, this.codigoProducto);
            if (this.producto!=null){
                
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('dialogDatosCantidadProductoPorCodigo').show();");
                
                this.codigoProducto=null;
                
            } else {
                this.codigoProducto=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Producto Inexistente"));
            }
            this.transactionCompra.commit();
        } catch (Exception e) {
            if (this.transactionCompra!=null){
                System.out.println(e.getMessage());
                transactionCompra.rollback();
            }
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
                
        }
        
    }
    
    public void agregarDatosProductoPorCodigoProductoRead(){
            
            if (!(this.unidadesCompradasPorCodigo.matches("[0-9]*")) || this.unidadesCompradasPorCodigo.equals("0") || this.unidadesCompradasPorCodigo.equals("")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Valor Incorrecto"));
                this.unidadesCompradasPorCodigo = "";
            
            } else {
        
        this.listaDetalleCompra.add(new DetalleCompra(0, 0, this.producto.getCodigoProducto(), this.producto.getNombreProducto(), this.producto.getValorCompraProducto(), this.producto.getValorVentaProducto(), Integer.parseInt(this.unidadesCompradasPorCodigo), (Float.parseFloat(this.unidadesCompradasPorCodigo)*this.producto.getValorCompraProducto())));
        
        this.unidadesCompradasPorCodigo="";
                
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Producto Agregado"));
        
        this.calcularValorTotalCompra();
        
            }
        
    }
    
    public void calcularValorTotalCompra(){
        
        BigDecimal totalVentaCompra = new BigDecimal("0");
        
        try{
            for (DetalleCompra detalleCompraTotal : listaDetalleCompra) {
                BigDecimal totalVentaPorProducto = (new BigDecimal(detalleCompraTotal.getValorCompraProducto()).multiply(new BigDecimal(detalleCompraTotal.getUnidadesCompradas())));
                detalleCompraTotal.setTotalDetalleCompra(totalVentaPorProducto.floatValue());
                totalVentaCompra = totalVentaCompra.add(totalVentaPorProducto);
            }
            this.compra.setTotalCompra(totalVentaCompra.floatValue());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void eliminarProducto (String codigoProducto, Integer posicionSeleccionada){
        
        try {
            
            int i=0;
            
            for (DetalleCompra detalleProducto : this.listaDetalleCompra){
                if (detalleProducto.getCodigoProducto().equals(codigoProducto) && posicionSeleccionada.equals(i)){
                    this.listaDetalleCompra.remove(i);
                    break;
                }
                i++;
            }
            
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Producto Eliminado"));
        
        this.calcularValorTotalCompra();
        
        } catch (Exception e){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Producto Eliminado"));
        
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Cantidad Modificada"));
        this.calcularValorTotalCompra();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Sin Modificación"));
        
    }
    
}
