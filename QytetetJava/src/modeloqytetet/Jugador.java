/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;
import java.lang.Comparable;
/**
 *
 * @author albil
 */

public class Jugador implements Comparable{ 
    private boolean encarcelado;
    private String nombre;
    private int saldo ;
    private Sorpresa cartaLibertad;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades = new ArrayList<>();

    public Jugador(String nombre) {
        this.nombre = nombre;
        encarcelado = false;
        saldo = 7500;
    }
    
    protected Jugador(Jugador otroJugador){
        this.nombre = otroJugador.nombre;
        this.encarcelado = otroJugador.encarcelado ;
        this.saldo = otroJugador.saldo;
        this.cartaLibertad = otroJugador.cartaLibertad;
        this.casillaActual = otroJugador.casillaActual;
        this.propiedades = otroJugador.propiedades;    
    }
    
   @Override
    public int compareTo(Object otroJugador){
      int otroCapital = ((Jugador) otroJugador).obtenerCapital();
      return otroCapital - obtenerCapital();       
    }
    
    
/*   @Override
    public int compareTo(Object otroJugador){
        if(obtenerCapital() > ((Jugador) otroJugador).obtenerCapital()){
            return 1;
        }else if (obtenerCapital() < ((Jugador) otroJugador).obtenerCapital()){
            return -1;
        }
        
        return 0;
    }
 */   
            
    public boolean getEncarcelado() {
        return encarcelado;
    }

    public String getNombre() { //Lo pongo público para usarlo en la clase ControladorQytetet
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }

    Casilla getCasillaActual() {
        return casillaActual;
    }

    ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }

    void setEncarcelado(boolean encarcelado ) {
        this.encarcelado = encarcelado;
    }

    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }
    
    
    boolean cancelarHipoteca(TituloPropiedad titulo){
        boolean cancelada = false;
        int coste;
        
        coste = titulo.calcularCosteCancelar();
        
        if(tengoSaldo(coste)){
            titulo.cancelarHipoteca();
            cancelada = true;
            modificarSaldo(-coste);
        }
        
        return cancelada;
    }
    
    boolean comprarTituloPropiedad(){
        boolean comprado = false ;
        int costeCompra;
        TituloPropiedad titulo;
        
        costeCompra = casillaActual.getPrecioCompra();
        
        if(costeCompra < saldo){
            //El metodo asignarPropietario ahora solo pertenece a la clase Calle, por lo cual
            // hay que hacer un casting a la casilla actual para inidicar que es una calle
            Calle casillaCalle = (Calle) casillaActual;
            titulo = casillaCalle.asignarPropietario(this);
            propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado = true;
        }
        
        return comprado;
    }
    
    int cuantasCasasHotelesTengo(){
        int contador = 0;
        
        for(int i = 0; i < propiedades.size(); i++){
            contador += propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();
        }
        
        
        return contador;
    }
    
    //Otra forma: int cuantasCasasHotelesTengo(){
    //              int contador = 0;
    //              for(TituloPropiedad p : propiedades){
    //                  contador += p.getNumCasas() + p.getNumHoteles();
    //              }
    
    boolean deboPagarAlquiler(){
        boolean pagarAlquiler = false;
        
        TituloPropiedad titulo;
        titulo = casillaActual.getTitulo();
        
        boolean esDeMiPropiedad = esDeMiPropiedad(titulo);
        
        if(!esDeMiPropiedad){
            boolean tienePropietario;
            
            tienePropietario = titulo.tengoPropietario();
            
            if(tienePropietario){
                boolean encarcelado;
                encarcelado = titulo.propietarioEncarcelado();
                
                if(!encarcelado){
                   boolean estaHipotecada;
                   estaHipotecada = titulo.getHipotecada();
                   
                   if(!estaHipotecada){
                       pagarAlquiler = true;
                   }
                }
            }
        }
        
       return pagarAlquiler;
      
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa aux = null;
        if(cartaLibertad != null){
            aux = cartaLibertad;
            cartaLibertad = null; //aquí ya no tienes la carta porque la has usao
        }
        
        return aux;
    }
    
    protected boolean edificarCasa(TituloPropiedad titulo){
       boolean edificada = false;
        
       if(puedoEdificarCasa(titulo)){
           titulo.edificarCasa();
           modificarSaldo(-titulo.getPrecioEdificar());
           edificada = true;
       }
        
       return edificada;
    }
    
    protected boolean edificarHotel(TituloPropiedad titulo){
       boolean edificado = false;
        
       if(puedoEdificarHotel(titulo)){
           titulo.edificarHotel();
           modificarSaldo(-titulo.getPrecioEdificar());
           edificado = true;
       }
        
       return edificado;
    }
    
    void eliminarDeMisPropiedades (TituloPropiedad titulo){
        propiedades.remove(titulo);
        
        titulo.setPropietario(null);
    }
    
    boolean esDeMiPropiedad(TituloPropiedad titulo){
        boolean es_propiedad = false;
        
        for(int i= 0; i < propiedades.size() && !es_propiedad; i++){
            if(propiedades.get(i) == titulo){
                es_propiedad = true;
            }
        }
        
        return es_propiedad;
    }

/*    
    boolean estoyEnCalleLibre(){
        return !encarcelado;
    }
*/    
    void hipotecarPropiedad(TituloPropiedad titulo){
        int costeHipoteca;
        
        costeHipoteca = titulo.hipotecar();
        
        modificarSaldo(costeHipoteca);
        
    }
    
    void irACarcel(Casilla casilla){
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    
    int modificarSaldo(int cantidad){
        saldo = saldo + cantidad;

        return saldo;
    }
    
    public int obtenerCapital(){
        int dinero = saldo ;
        
        
        for (int i= 0; i < propiedades.size(); i++){
            dinero += propiedades.get(i).getPrecioCompra() 
                    + (propiedades.get(i).getNumCasas()
                    + propiedades.get(i).getNumHoteles())*propiedades.get(i).getPrecioEdificar();
            
             if(propiedades.get(i).getHipotecada()){
                 dinero -= propiedades.get(i).getHipotecaBase();
             }
        }
        return dinero;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada){
        ArrayList<TituloPropiedad> aux = new ArrayList<>();
        
        for(int i= 0; i < propiedades.size(); i++){
            if(hipotecada == propiedades.get(i).getHipotecada()){
               aux.add(propiedades.get(i)); 
            }
        }
        
        return aux;
    }
    
    void pagarAlquiler(){
       int costeAlquiler;
       
       Calle casillaCalle = (Calle) casillaActual;
       
       costeAlquiler = casillaCalle.pagarAlquiler();
       
       modificarSaldo(-costeAlquiler);
    }
    
    protected void pagarImpuesto(){
        saldo -= casillaActual.getPrecioCompra();
    }
    
    void pagarLibertad(int cantidad){
        boolean tengoSaldo;
        
        tengoSaldo = tengoSaldo(cantidad);
        
        if(tengoSaldo){
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
    }
    
    boolean tengoCartaLibertad(){
        return cartaLibertad != null;
    }
    
    protected boolean tengoSaldo(int cantidad){
        boolean mucho_dinero = false;
        
        if (saldo > cantidad){
            mucho_dinero = true;
        }
        
        return mucho_dinero;
    }
    
    void venderPropiedad(Casilla casilla){ 
        TituloPropiedad titulo;
        int precioVenta ;
        
        titulo = casilla.getTitulo();
        
        eliminarDeMisPropiedades(titulo);
         
        precioVenta = titulo.calcularPrecioVenta();
        
        modificarSaldo(precioVenta);
         
    }
    
    //Metodos nuevos práctica 4:
    
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        boolean puedo_edificar = false;
        int numCasas, numHoteles;
        int costeEdificarHotel;
        
        numCasas = titulo.getNumCasas();
        
        if (numCasas == 4){
            numHoteles = titulo.getNumHoteles();
            if(numHoteles < 4){
                costeEdificarHotel = titulo.getPrecioEdificar();
                boolean tengoSaldo;
                tengoSaldo = tengoSaldo(costeEdificarHotel);
                
                if(tengoSaldo){
                    puedo_edificar = true;
                }
            }
        }else{
            System.out.println("¡No puedes edificar un hotel hasta que tengas 4 casas!");
        }
       return puedo_edificar; 
    }
    
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
         boolean puedo_edificar = false;
        int numCasas;
        int costeEdificarCasa;
        
        numCasas = titulo.getNumCasas();
        
        if(titulo.getPropietario() == this){
            if(numCasas < 4){
            costeEdificarCasa = titulo.getPrecioEdificar(); 
            boolean tengoSaldo;
            tengoSaldo = tengoSaldo(costeEdificarCasa);
           
            if(tengoSaldo){
                puedo_edificar = true; 
            }else{
                System.out.println("No tienes dinero para edifiar una casa");
            }
            }else{
                System.out.println("No puedes edificar porque ya tienes 4 casas");
            }
        }else{
            System.out.println("Esta calle no es tuya");
        }
        

        return puedo_edificar;
    }
    
    
    protected boolean deboIrACarcel(){
        return !tengoCartaLibertad();
    }
    
    protected Especulador convertirme(int fianza){
        Especulador jug_especulador = new Especulador(this, fianza);
       
        return jug_especulador;
    }
    
   
    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre 
                + ", saldo=" + saldo + ", capital= " + obtenerCapital() + ", cartaLibertad=" + cartaLibertad +
                ", casillaActual=" + casillaActual 
                + ", propiedades=" + propiedades + '}';
    }
    
    
}
