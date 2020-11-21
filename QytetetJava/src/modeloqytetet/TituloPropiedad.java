/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author albil
 */
public class TituloPropiedad {
    private String nombre ;
    private boolean hipotecada ;
    private int precioCompra ;
    private int alquilerBase ;
    private float factorRevalorizacion;
    private int hipotecaBase ;
    private int precioEdificar ;
    private int numHoteles, numCasas ;
    private Jugador propietario;
    
    public TituloPropiedad(String nom, int pcompra, int alqbase, float facrev, 
            int hipbase, int pedif){
        hipotecada = false; 
        numHoteles = 0 ;
        numCasas = 0 ;
        nombre = nom;
        precioCompra = pcompra;
        alquilerBase = alqbase ;
        factorRevalorizacion = facrev ;
        hipotecaBase = hipbase ;
        precioEdificar = pedif ;
    }
    
    String getNombre(){
     return nombre;
    }
    
    boolean getHipotecada(){
        return hipotecada;
    }
    
    int getPrecioCompra(){
        return precioCompra ;
    }
    
    int getAlquilerBase(){
        return alquilerBase ;
    }
    
    float getFactorRevalorizacion(){
        return factorRevalorizacion ;
    }
    
    int getHipotecaBase(){
        return hipotecaBase ;
    }
    
    int getPrecioEdificar(){
        return precioEdificar;
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }
    
    Jugador getPropietario(){
        return propietario;
    }

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    int hipotecar(){
        int costeHipoteca; 
        costeHipoteca = calcularCosteHipotecar();
        
        setHipotecada(true);
        
        return costeHipoteca;
    }
    
    int pagarAlquiler(){
        int costeAlquiler;
        
        costeAlquiler = calcularImporteAlquiler();
        
        propietario.modificarSaldo(costeAlquiler);
        
        return costeAlquiler;
    }
    
    boolean propietarioEncarcelado(){
        boolean esta_encarcelado = false;
        
        if(propietario.getEncarcelado()){
            esta_encarcelado = true;
        }
        
        return esta_encarcelado;
    }

    boolean tengoPropietario(){
        boolean es_propietario = false;
        
        if(propietario != null){
          es_propietario = true ;  
        }
        
      return es_propietario ;
    }
    
    int calcularCosteCancelar(){
        int coste;
        
        coste = (int) (calcularCosteHipotecar()*0.10);
        
        return coste;
    }
    
    int calcularCosteHipotecar(){
        int costeHipoteca ;
        
        costeHipoteca = (int) (hipotecaBase + numCasas*0.5*hipotecaBase + numHoteles*hipotecaBase);
        
        return costeHipoteca;
    }
    
    int calcularImporteAlquiler(){
        int costeAlquiler;
        
        costeAlquiler = (int) (alquilerBase +  numCasas*0.5 + numHoteles*2);
        
        return costeAlquiler;
    }
    
    int calcularPrecioVenta(){
        int precioVenta ;
     
        precioVenta = (int)(precioCompra + (numCasas + numHoteles)*precioEdificar*factorRevalorizacion);
        
        return precioVenta;
    }
    
    void cancelarHipoteca(){
        hipotecada = false;
    }
    
    
    void edificarCasa(){
        numCasas += 1;
    }
    
    void edificarHotel(){
        numHoteles += 1;
        numCasas = 0;
    }
    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre + ", hipotecada=" + 
                hipotecada + ", precioCompra=" + precioCompra +
                ", alquilerBase=" + alquilerBase + ", factorRevalorizacion="
                + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + 
                ", precioEdificar=" + precioEdificar + ", numHoteles="
                + numHoteles + ", numCasas=" + numCasas + '}';
    }
}
