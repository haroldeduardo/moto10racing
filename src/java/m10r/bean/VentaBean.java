
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
import m10r.dao.VentaDao;
import m10r.dao.PersonaDao;
import m10r.dao.ProductoDao;
import m10r.dao.DetalleVentaDao;
import m10r.imp.VentaImp;
import m10r.imp.DetalleVentaImp;
import m10r.imp.PersonaImp;
import m10r.imp.ProductoImp;
import m10r.model.Venta;
import m10r.model.DetalleVenta;
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

@Named(value = "ventaBean")
@ViewScoped
@ManagedBean

public class VentaBean implements Serializable {
    
    Session sessionVenta=null;
    Transaction transactionVenta=null;
    
    @ManagedProperty("#{InterfaceUsuarioBean}")
    private InterfaceUsuarioBean uBean;
    
    private Persona persona;
    private Integer identificacionPersona;
    
    private Producto producto;
    private String codigoProducto;
    
    private List<DetalleVenta> listaDetalleVenta;
    
    private String unidadesVendidas;
    private String productoSeleccionado;
    private Venta venta;
    
    private String unidadesVendidasPorCodigo;
    
    private Long numeroVenta;
    private BigDecimal totalVentaFactura;
    private float totalVentaFacturaVenta;
    
    private Empleado empleado;
    private TipoTransaccion tipoTransaccion;
    
    private boolean enabled;
    private String fechaSistema;

    public VentaBean() {
        this.venta = new Venta();
        this.listaDetalleVenta = new ArrayList<>();
        this.empleado = new Empleado();
        this.persona = new Persona();
        this.tipoTransaccion = new TipoTransaccion();
    }

    public InterfaceUsuarioBean getuBean() {
        return uBean;
    }

    public void setuBean(InterfaceUsuarioBean uBean) {
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

    public List<DetalleVenta> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVenta> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public String getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(String unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public String getUnidadesVendidasPorCodigo() {
        return unidadesVendidasPorCodigo;
    }

    public void setUnidadesVendidasPorCodigo(String unidadesVendidasPorCodigo) {
        this.unidadesVendidasPorCodigo = unidadesVendidasPorCodigo;
    }

    public Long getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(Long numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public BigDecimal getTotalVentaFactura() {
        return totalVentaFactura;
    }

    public void setTotalVentaFactura(BigDecimal totalVentaFactura) {
        this.totalVentaFactura = totalVentaFactura;
    }

    public float getTotalVentaFacturaVenta() {
        return totalVentaFacturaVenta;
    }

    public void setTotalVentaFacturaVenta(float totalVentaFacturaVenta) {
        this.totalVentaFacturaVenta = totalVentaFacturaVenta;
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
        this.sessionVenta=null;
        this.transactionVenta=null;
        
        try {
            this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
            PersonaDao pDao = new PersonaImp();
            this.transactionVenta = this.sessionVenta.beginTransaction();
            this.persona = pDao.obtenerPersonaPorId(this.sessionVenta, idPersona);
            this.transactionVenta.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Comprador Agregado"));
        } catch (Exception e) {
            if (this.transactionVenta!=null){
                System.out.println(e.getMessage());
                transactionVenta.rollback();
            }
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
                
        }
        
    }
    
    public void agregarDatosPersonaPorIdentificacion(){
        this.sessionVenta=null;
        this.transactionVenta=null;
        
        try {
            if (this.identificacionPersona==null){
                return;
            }
            this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
            PersonaDao pDao = new PersonaImp();
            this.transactionVenta = this.sessionVenta.beginTransaction();
            this.persona = pDao.obtenerPersonaPorIdentificacion(this.sessionVenta, this.identificacionPersona);
            if (this.persona!=null){
                this.identificacionPersona=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Comprador Agregado"));
            } else {
                this.identificacionPersona=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Comprador Inexistente"));
            }
            this.transactionVenta.commit();
        } catch (Exception e) {
            if (this.transactionVenta!=null){
                System.out.println(e.getMessage());
                transactionVenta.rollback();
            }
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
                
        }
        
    }
    
    public void solicitarCantidadProducto(String codigoProducto){
        this.productoSeleccionado = codigoProducto;
    }
    
    public void agregarDatosProductoPorCodigoProducto(){
        this.sessionVenta=null;
        this.transactionVenta=null;
        
        try {
            
            if (!(this.unidadesVendidas.matches("[0-9]*")) || this.unidadesVendidas.equals("0") || this.unidadesVendidas.equals("")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Valor Incorrecto"));
                this.unidadesVendidas = "";
            
            } else {
                
            this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
            ProductoDao pDao = new ProductoImp();
            this.transactionVenta = this.sessionVenta.beginTransaction();
            this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionVenta, this.productoSeleccionado);
            
            this.listaDetalleVenta.add(new DetalleVenta(0, 0, this.producto.getCodigoProducto(), this.producto.getNombreProducto(), this.producto.getValorVentaProducto(), this.producto.getIva(), Integer.parseInt(this.unidadesVendidas), (Float.parseFloat(this.unidadesVendidas)*this.producto.getValorVentaProducto())));
            
            this.transactionVenta.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Producto Agregado"));
            
            this.unidadesVendidas="";
            
            this.calcularValorTotalVenta();
                
            }
             
        } catch (Exception e) {
            if (this.transactionVenta!=null){
                System.out.println(e.getMessage());
                transactionVenta.rollback();
            }
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
                
        }
        
    }
    
    public void mostrarDatosCantidadProductoPorCodigo(){
        this.sessionVenta=null;
        this.transactionVenta=null;
        
        try {
            if (this.codigoProducto==null){
                return;
            }
            this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
            ProductoDao pDao = new ProductoImp();
            this.transactionVenta = this.sessionVenta.beginTransaction();
            this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionVenta, this.codigoProducto);
            if (this.producto!=null){
                
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('dialogDatosCantidadProductoPorCodigo').show();");
                
                this.codigoProducto=null;
                
            } else {
                this.codigoProducto=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Producto Inexistente"));
            }
            this.transactionVenta.commit();
        } catch (Exception e) {
            if (this.transactionVenta!=null){
                System.out.println(e.getMessage());
                transactionVenta.rollback();
            }
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
                
        }
        
    }
    
    public void agregarDatosProductoPorCodigoProductoRead(){
            
            if (!(this.unidadesVendidasPorCodigo.matches("[0-9]*")) || this.unidadesVendidasPorCodigo.equals("0") || this.unidadesVendidasPorCodigo.equals("")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Valor Incorrecto"));
                this.unidadesVendidasPorCodigo = "";
            
            } else {
        
        this.listaDetalleVenta.add(new DetalleVenta(0, 0, this.producto.getCodigoProducto(), this.producto.getNombreProducto(), this.producto.getValorVentaProducto(), this.producto.getIva(), Integer.parseInt(this.unidadesVendidasPorCodigo), (Float.parseFloat(this.unidadesVendidasPorCodigo)*this.producto.getValorCompraProducto())));
        
        this.unidadesVendidasPorCodigo="";
                
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Producto Agregado"));
        
        this.calcularValorTotalVenta();
        
            }
        
    }
    
    public void calcularValorTotalVenta(){
        
        this.totalVentaFactura = new BigDecimal("0");
        
        try{
            for (DetalleVenta detalleVentaTotal : listaDetalleVenta) {
                BigDecimal totalVentaPorProducto = (new BigDecimal(detalleVentaTotal.getValorVentaProducto()).multiply(new BigDecimal(detalleVentaTotal.getUnidadesVendidas())));
                detalleVentaTotal.setTotalDetalleVenta(totalVentaPorProducto.floatValue());
                totalVentaFactura = totalVentaFactura.add(totalVentaPorProducto);
            }
            this.venta.setTotalVenta(totalVentaFactura.floatValue());
            totalVentaFacturaVenta = (totalVentaFactura.floatValue());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void eliminarProducto (String codigoProducto, Integer posicionSeleccionada){
        
        try {
            
            int i=0;
            
            for (DetalleVenta detalleProducto : this.listaDetalleVenta){
                if (detalleProducto.getCodigoProducto().equals(codigoProducto) && posicionSeleccionada.equals(i)){
                    this.listaDetalleVenta.remove(i);
                    break;
                }
                i++;
            }
            
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Producto Eliminado"));
        
        this.calcularValorTotalVenta();
        
        } catch (Exception e){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Producto Eliminado"));
        
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Modificación Realizada"));
        this.calcularValorTotalVenta();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","Sin Modificacaciones"));
        
    }
    
    public void numeracionVenta(){
        this.sessionVenta = null;
        this.transactionVenta = null;
        
        try {
            this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
            this.transactionVenta = this.sessionVenta.beginTransaction();
            VentaDao cDao = new VentaImp();
            this.numeroVenta = cDao.obtenerTotalRegistrosVenta(this.sessionVenta);
            
            if (this.numeroVenta <=0 || this.numeroVenta == null){
                this.numeroVenta = Long.valueOf("1");
            } else {
                this.venta = cDao.obtenerUltimoRegistroVenta(sessionVenta);
                this.numeroVenta = Long.valueOf(this.venta.getIdVenta()+1);
                
                this.totalVentaFactura = new BigDecimal("0");
                
            }
            this.transactionVenta.commit();
        } catch (Exception e){
            if (this.transactionVenta!=null){
                this.transactionVenta.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
        }
    }
    
    public void limpiarFacturaVenta(){
        this.persona = new Persona();
        this.venta = new Venta ();
        this.listaDetalleVenta = new ArrayList<>();
        this.numeroVenta = null;
        this.totalVentaFacturaVenta = 0;
        
        this.disableBoton();
    }
    
    public void ingresarVentaFULL(){ /// aún definir DESCUENTOS !!!!!!!!!!
        this.sessionVenta = null;
        this.transactionVenta = null;
        this.empleado.setIdEmpleado(1); /// aún definir Id Empleado !!!!!!!!!!
        this.tipoTransaccion.setIdTipoTransaccion(1); /// aún por definir Tipo de Transacción Contado ó Credito !!!!!!!!!
        
        try {
                this.sessionVenta = HibernateUtil.getSessionFactory().openSession();
                ProductoDao pDao = new ProductoImp();
                VentaDao cDao = new VentaImp();
                DetalleVentaDao dcDao = new DetalleVentaImp();
                
                this.transactionVenta = this.sessionVenta.beginTransaction();
                
                this.venta.setNumeroVenta(Long.toString(this.numeroVenta));
                this.venta.setIdEmpleado(this.empleado.getIdEmpleado());
                this.venta.setIdPersona(this.persona.getIdPersona());
                this.venta.setIdTipoTransaccion(this.tipoTransaccion.getIdTipoTransaccion());
                
                cDao.ingresarVenta(this.sessionVenta, this.venta);
           
                this.venta = cDao.obtenerUltimoRegistroVenta(this.sessionVenta);
                
                for (DetalleVenta detalleVentaTotal : listaDetalleVenta){
                    this.producto = pDao.obtenerProductoPorCodigoProducto(this.sessionVenta, detalleVentaTotal.getCodigoProducto());
                    detalleVentaTotal.setIdVenta(this.venta.getIdVenta());
                    detalleVentaTotal.setIdProducto(this.producto.getIdProducto());
                    
                    dcDao.ingresarDetalleVenta(this.sessionVenta, detalleVentaTotal);
                }
                this.transactionVenta.commit();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Venta Registrada"));
                    
                    this.limpiarFacturaVenta();
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (this.transactionVenta!=null){
                this.transactionVenta.rollback();
            }
        } finally {
            if (this.sessionVenta!=null){
                this.sessionVenta.close();
            }
        }
    }
    
}