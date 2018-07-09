
package m10r.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import m10r.dao.CompraDao;
import m10r.dao.PersonaDao;
import m10r.dao.ProductoDao;
import m10r.dao.DetalleCompraDao;
import m10r.imp.CompraImp;
import m10r.imp.DetalleCompraImp;
import m10r.imp.PersonaImp;
import m10r.imp.ProductoImp;
import m10r.model.Compra;
import m10r.model.DetalleCompra;
import m10r.model.Empleado;
import m10r.model.Persona;
import m10r.model.Producto;
import m10r.model.TipoTransaccion;
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
    
    @ManagedProperty("#{UsuarioBean}")
    private UsuarioBean uBean;
    
    private Persona persona;
    private Integer identificacionPersona;
    
    private Producto producto;
    private String codigoProducto;
    
    private List<DetalleCompra> listaDetalleCompra;
    
    private String unidadesCompradas;
    private String productoSeleccionado;
    private Compra compra;
    
    private String unidadesCompradasPorCodigo;
    
    private Long numeroCompra;
    private BigDecimal totalCompraFactura;
    private float totalCompraFacturaCompra;
    
    private Empleado empleado;
    private TipoTransaccion tipoTransaccion;
    
    private boolean enabled;
    private String fechaSistema;

    public CompraBean() {
        this.compra = new Compra();
        this.listaDetalleCompra = new ArrayList<>();
        this.empleado = new Empleado();
        this.persona = new Persona();
        this.tipoTransaccion = new TipoTransaccion();
    }

    public UsuarioBean getuBean() {
        return uBean;
    }

    public void setuBean(UsuarioBean uBean) {
        this.uBean = uBean;
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

    public Long getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(Long numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public BigDecimal getTotalCompraFactura() {
        return totalCompraFactura;
    }

    public void setTotalCompraFactura(BigDecimal totalCompraFactura) {
        this.totalCompraFactura = totalCompraFactura;
    }

    public float getTotalCompraFacturaCompra() {
        return totalCompraFacturaCompra;
    }

    public void setTotalCompraFacturaCompra(float totalCompraFacturaCompra) {
        this.totalCompraFacturaCompra = totalCompraFacturaCompra;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public void enableBoton(){
        enabled = true;
    }
    
    public void disableBoton(){
        enabled = false;
    }

    public String getFechaSistema() {
        Calendar dateS = new GregorianCalendar();
        
        int ano = dateS.get(Calendar.YEAR);
        int mes = dateS.get(Calendar.MONTH);
        int dia = dateS.get(Calendar.DAY_OF_WEEK);
        
        this.fechaSistema = (dia + "/" + (mes+1) + "/" + ano);
        
        return fechaSistema;
    }
    
    public void agregarDatosPersona(Integer idPersona){
        this.sessionCompra=null;
        this.transactionCompra=null;
        
        try {
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            PersonaDao pDao = new PersonaImp();
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
            PersonaDao pDao = new PersonaImp();
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
            ProductoDao pDao = new ProductoImp();
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
            ProductoDao pDao = new ProductoImp();
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
        
        this.totalCompraFactura = new BigDecimal("0");
        
        try{
            for (DetalleCompra detalleCompraTotal : listaDetalleCompra) {
                BigDecimal totalVentaPorProducto = (new BigDecimal(detalleCompraTotal.getValorCompraProducto()).multiply(new BigDecimal(detalleCompraTotal.getUnidadesCompradas())));
                detalleCompraTotal.setTotalDetalleCompra(totalVentaPorProducto.floatValue());
                totalCompraFactura = totalCompraFactura.add(totalVentaPorProducto);
            }
            this.compra.setTotalCompra(totalCompraFactura.floatValue());
            totalCompraFacturaCompra = (totalCompraFactura.floatValue());
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Modificación Realizada"));
        this.calcularValorTotalCompra();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Sin Modificacaciones"));
        
    }
    
    public void numeracionCompra(){
        this.sessionCompra = null;
        this.transactionCompra = null;
        
        try {
            this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
            this.transactionCompra = this.sessionCompra.beginTransaction();
            CompraDao cDao = new CompraImp();
            this.numeroCompra = cDao.obtenerTotalRegistrosCompra(this.sessionCompra);
            
            if (this.numeroCompra <=0 || this.numeroCompra == null){
                this.numeroCompra = Long.valueOf("1");
            } else {
                this.compra = cDao.obtenerUltimoRegistroCompra(sessionCompra);
                this.numeroCompra = Long.valueOf(this.compra.getIdCompra()+1);
                
                this.totalCompraFactura = new BigDecimal("0");
                
            }
            this.transactionCompra.commit();
        } catch (Exception e){
            if (this.transactionCompra!=null){
                this.transactionCompra.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
        }
    }
    
    public void limpiarFacturaCompra(){
        this.persona = new Persona();
        this.compra = new Compra ();
        this.listaDetalleCompra = new ArrayList<>();
        this.numeroCompra = null;
        this.totalCompraFacturaCompra = 0;
        
        this.disableBoton();
    }
    
    public void ingresarCompraFULL(){
        this.sessionCompra = null;
        this.transactionCompra = null;
        this.empleado.setIdEmpleado(1); /// definir Id Empleado...................
        this.tipoTransaccion.setIdTipoTransaccion(1); /// definir Tipo de Transacción Contado v Credito...................
        
        try {
                this.sessionCompra = HibernateUtil.getSessionFactory().openSession();
                ProductoDao pDao = new ProductoImp();
                CompraDao cDao = new CompraImp();
                DetalleCompraDao dcDao = new DetalleCompraImp();
                
                this.transactionCompra = this.sessionCompra.beginTransaction();
                
                this.compra.setNumeroCompra(Long.toString(this.numeroCompra));
                this.compra.setIdEmpleado(this.empleado.getIdEmpleado());
                this.compra.setIdPersona(this.persona.getIdPersona());
                this.compra.setIdTipoTransaccion(this.tipoTransaccion.getIdTipoTransaccion());
                
                cDao.ingresarCompra(this.sessionCompra, this.compra);
           
                this.compra = cDao.obtenerUltimoRegistroCompra(this.sessionCompra);
                
                for (DetalleCompra detalleCompraTotal : listaDetalleCompra){
                    this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionCompra, detalleCompraTotal.getCodigoProducto());
                    detalleCompraTotal.setIdCompra(this.compra.getIdCompra());
                    detalleCompraTotal.setIdProducto(this.producto.getIdProducto());
                    
                    dcDao.ingresarDetalleCompra(this.sessionCompra, detalleCompraTotal);
                }
                this.transactionCompra.commit();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Compra Registrada"));
                    
                    this.limpiarFacturaCompra();
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (this.transactionCompra!=null){
                this.transactionCompra.rollback();
            }
        } finally {
            if (this.sessionCompra!=null){
                this.sessionCompra.close();
            }
        }
    }
    
}
