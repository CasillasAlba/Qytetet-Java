/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modeloqytetet;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author albil
 */
public class Qytetet {
     private static final Qytetet instance = new Qytetet();
     
    private Qytetet(){} 
     
    public static Qytetet getInstance(){
        return instance;
    }
    
    public static final int MAX_JUGADORES = 4;
    static final int NUM_SORPRESAS = 10;
    public static final int NUM_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALDO_SALIDA = 1000;
    protected static int IMPUESTO = 500;
    
    
    ArrayList<Sorpresa> mazo = new ArrayList<>();
    private static Tablero tablero ;
    private Sorpresa cartaActual ;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private Jugador jugadorActual;
    Dado dado = Dado.getInstance();
    EstadoJuego estadoJuego = EstadoJuego.JA_PREPARADO;
    
    
    public Tablero getTablero() {
        return tablero;
    }
   

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }
    
    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Dado getDado() {
        return dado;
    }
    
    int getValorDado(){
        return dado.getValor();
    }
    
    ArrayList<Sorpresa> getMazo(){
        return mazo ;
    }
    
    public void setCartaActual(Sorpresa cartaActual){
       this.cartaActual = cartaActual; 
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
    } 
    
    private void inicializarJugadores(ArrayList<String> nombres){
        //jugadores = new ArrayList();
        
        for(int i=0; i< nombres.size(); ++i){
            Jugador jugador = new Jugador(nombres.get(i));
            jugadores.add(jugador);
        }
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarTablero();
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        salidaJugadores();
    }
    
    private void inicializarCartasSorpresa(){
        //CONVERTIRME
        mazo.add(new Sorpresa("Como eres el más chulo del barrio, te vuelves un Especulador",
              3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa("¡Wow! Te has convertido en Especulador", 5000, TipoSorpresa.CONVERTIRME));
        //PAGARCOBRAR
        mazo.add(new Sorpresa("Te has encontrado una cartera en la calle. "
                + "Ganas 275 euros", 275, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Invitas a tu novia a una cita romantica y quieres"
                + "estar a la altura. Te gastas en ella 100"
                + " euros", -100, TipoSorpresa.PAGARCOBRAR));
        //IRACASILLA
        mazo.add(new Sorpresa("Quieres ir a la discoteca Copera, obviamente en la"
                + " calle Copera. Avanzas a la casilla 13.", 13, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Tu novia vive en la calle Salsipuedes y vas a su"
                + "casa para darle una sorpresa. Avanzas a la casilla 18",18,
                TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Te han pillado consumiendo droga. Vas a la carcel",
                tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        //PORCASAHOTEL
        mazo.add(new Sorpresa("Tu amigo de la infancia es arquitecto ¡y te ha hecho"
                + "unas construcciones de lujo! Cobras 60 euros por casa/hotel",
                60, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Han descubierto el escondite de unos narcotraficanes"
                + "en una de tus construcciones. Ahora debes pagar 95 euros por"
                + "cada casa/hotel", -95, TipoSorpresa.PORCASAHOTEL));
        //PORJUGADOR
        mazo.add(new Sorpresa("Es tu cumpleaños. Tus amigos te dan 20 euros cada"
                + "uno", 20, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Sales de la discoteca e invitas a tus amigos a ir"
                + "de after. Pagas 15 euros a cada uno", -15, TipoSorpresa.PORJUGADOR));
        //SALIRCARCEL
        mazo.add(new Sorpresa("Tu amigo el camello ha ganado más de lo normal y "
                + "te paga la fianza",0,TipoSorpresa.SALIRCARCEL));
        
        Collections.shuffle(mazo); //Baraja aleatoriamente
       
    }
     
    void actuarSiEnCasillaEdificable(){
        boolean deboPagar, tengoPropietario;
        Casilla casilla;
        deboPagar = jugadorActual.deboPagarAlquiler();
        
        if(deboPagar){
            jugadorActual.pagarAlquiler();
            
            if(jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        
         casilla = obtenerCasillaJugadorActual(); 
         
         
         Calle casillaCalle = (Calle) casilla;
         tengoPropietario = casillaCalle.tengoPropietario();
         
         if(estadoJuego != EstadoJuego.ALGUNJUGADORENBANCARROTA){
             if (tengoPropietario){
                 setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
             }else{
                setEstadoJuego(EstadoJuego.JA_PUEDECOMPAROGESTIONAR); 
             }
         }
    }
    
    void actuarSiEnCasillaNoEdificable(){
       Casilla casillaActual;
       
       setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
       
       casillaActual = jugadorActual.getCasillaActual();
       
       if(casillaActual.getTipo() == TipoCasilla.IMPUESTO){
           jugadorActual.pagarImpuesto();
       }else{
           if(casillaActual.getTipo() == TipoCasilla.JUEZ){
               encarcelarJugador();
           }else if (casillaActual.getTipo() == TipoCasilla.SORPRESA){
               cartaActual = mazo.remove(0);
               setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
           }  
       }
         
    }
    
    public void aplicarSorpresa(){
        int valor, cantidad, numeroTotal;
        boolean casillaCarcel;
        
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        if(cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }else{
            mazo.add(cartaActual);
            
           if(cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
               jugadorActual.modificarSaldo(cartaActual.getValor());
               
               if(jugadorActual.getSaldo() <= 0){
                   setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
               }
               
               }else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
                   valor = cartaActual.getValor();
                   casillaCarcel = tablero.esCasillaCarcel(valor);
                   
                   if (casillaCarcel){
                     encarcelarJugador();
                   }else{
                       jugadorActual.setCasillaActual(tablero.obtenerCasillaNumero(valor));
                   }
               }else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
                   cantidad = cartaActual.getValor();
                   numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
                   jugadorActual.modificarSaldo(cantidad*numeroTotal);
                   
                   if(jugadorActual.getSaldo() <= 0){
                       setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                   }
               }else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
                    for(Jugador jugador : jugadores){
                       if(jugador != jugadorActual){
                           jugador.modificarSaldo(-cartaActual.getValor());
                            
                       if(jugador.getSaldo() <= 0){
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                       }
                        
                       jugadorActual.modificarSaldo(cartaActual.getValor());
                       
                       if(jugadorActual.getSaldo() <= 0){
                           setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                       }
                     }

                   }
               }else if (cartaActual.getTipo() == TipoSorpresa.CONVERTIRME){
                   Especulador jug_especulador;
                   jug_especulador = jugadorActual.convertirme(cartaActual.getValor());
                   
                   int posiciones = 0;
                   
                   for(int i = 0; i < jugadores.size(); i++){
                       if (jugadores.get(i) == jugadorActual){
                          posiciones = i;
                       }
                   }
                   
                   jugadores.set(posiciones, jug_especulador); //jugadores.set(posiciones, jug_especulador); 
                   jugadorActual = jug_especulador;

               }
           } 
        }
        
    public boolean comprarTituloPropiedad(){
        boolean comprado; 
        comprado = jugadorActual.comprarTituloPropiedad(); 
        
        if(comprado){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
            
        return comprado;   
    }
    
    public boolean edificarCasa(int numeroCasilla){
       boolean edificada = false;
       TituloPropiedad titulo;
       Casilla casilla;
       
       casilla = tablero.obtenerCasillaNumero(numeroCasilla);
       titulo = casilla.getTitulo();
       edificada = jugadorActual.edificarCasa(titulo);
               
        if(edificada){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
       
       return edificada;
    }
    
    public boolean edificarHotel(int numeroCasilla){
        boolean edificado = false;
        Casilla casilla;
        TituloPropiedad titulo;
        
        casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        
        titulo = casilla.getTitulo();
        
        edificado = jugadorActual.edificarHotel(titulo);
        
        if(edificado){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return edificado;
    }
    
    private void encarcelarJugador(){
        if (jugadorActual.deboIrACarcel()){
            Casilla casillaCarcel;
            
            casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
            
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }else{ 
          Sorpresa carta;
          carta = jugadorActual.devolverCartaLibertad();
          mazo.add(carta);
          
          setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
    } 
    
    public void hipotecarPropiedad(int numeroCasilla){
        Casilla casilla;
        TituloPropiedad titulo;
        
        casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        titulo = casilla.getTitulo();
        
        jugadorActual.hipotecarPropiedad(titulo);
      
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean encarcelado;
        
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int resultado;
            resultado = tirarDado();
            
            if(resultado >= 5){
                jugadorActual.setEncarcelado(false);
            }
        }else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
        
        encarcelado = jugadorActual.getEncarcelado();
        
        if(encarcelado){
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }else{
            setEstadoJuego(EstadoJuego.JA_PREPARADO);
        }
        
        return encarcelado;
    }
    
    public void jugar(){
        tirarDado();
        
        Casilla c;
        c = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(),dado.getValor());
        
        mover(c.getNumeroCasilla());
    }
    
    void mover(int numCasillaDestino){
        Casilla casillaInicial, casillaFinal;
        
        casillaInicial = jugadorActual.getCasillaActual();
        casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        
        jugadorActual.setCasillaActual(casillaFinal);
        
        //Si se pasa por la casilla de Salida
        if (numCasillaDestino < casillaInicial.getNumeroCasilla()){
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        }
        
        if(casillaFinal.soyEdificable()){
            actuarSiEnCasillaEdificable();
        }else{
            actuarSiEnCasillaNoEdificable(); 
        }
        
        
    }
    
    public Casilla obtenerCasillaJugadorActual(){
        return jugadorActual.getCasillaActual();
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero(){
        return tablero.getCasillas();
    }
    
    //No puedo poner ArrayList<int> porque int es un valor. Los Arrays requieren
    //una clase. Un objeto de tipo INTEGER contiene un campo de tipo int.
    public ArrayList<Integer> obtenerPropiedadesJugador(){
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<TituloPropiedad> las_propiedades = jugadorActual.getPropiedades();
       
        //Para cada casilla, se comprueba cual es del propietario
        for(Casilla c : tablero.getCasillas()){
            
            for(TituloPropiedad p : las_propiedades){
                if(c.getTitulo() == p){
                    aux.add(c.getNumeroCasilla());
                }
            }
            
        }
       
        return aux;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca (boolean estadoHipoteca){
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<TituloPropiedad> las_propiedades = jugadorActual.obtenerPropiedades(estadoHipoteca);
       
        //Para cada casilla, se comprueba cual es del propietario
        for(Casilla c : tablero.getCasillas()){
            
            for(TituloPropiedad p : las_propiedades){
                if(c.getTitulo() == p){
                    aux.add(c.getNumeroCasilla());
                }
            }
            
        }
       
          
        return aux;
        
     }
    
    public void obtenerRanking(){
           Collections.sort(jugadores);
    }
    
    public int obtenerSaldoJugadorActual(){
        return jugadorActual.getSaldo();
    }
    
    private void salidaJugadores(){
        Casilla salida = tablero.obtenerCasillaNumero(0);
        //Casilla carcel = tablero.getCarcel();
        for(int i = 0 ; i < jugadores.size(); i++){
            jugadores.get(i).setCasillaActual(salida);
            //jugadores.get(i).setCasillaActual(carcel);
            //jugadores.get(i).setEncarcelado(true);
        }
        
        int inicial = (int) (Math.random()*jugadores.size());
        
        jugadorActual = jugadores.get(inicial);
    }
    
    public void siguienteJugador(){
        
        //Posicion de tu jugador en el array de jugadores
        int pos = 0;
        boolean encontrado = false;
        for (int i=0; i<jugadores.size() && !encontrado; i++){
            if(jugadorActual == jugadores.get(i)){
                pos = i;
                encontrado = true;
            }
        }
        pos = (pos+1)%jugadores.size();
        
        jugadorActual = jugadores.get(pos);
        if(jugadorActual.getEncarcelado()){
           estadoJuego = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        }else{
            estadoJuego = EstadoJuego.JA_PREPARADO;
        }
    }
    
    int tirarDado(){
       int valor_dado;
       valor_dado = dado.tirar();
       
       return valor_dado;
    }
    
    public void venderPropiedad(int numeroCasilla){
        Casilla casilla;
        
        casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        
        jugadorActual.venderPropiedad(casilla);
        
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);   
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        boolean cancelada = false;
        Casilla casilla;
        TituloPropiedad titulo;
        
        casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        titulo = casilla.getTitulo();
      
        cancelada = jugadorActual.cancelarHipoteca(titulo);
        
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return cancelada;
    }

    @Override
    public String toString() {
      String res;

        res = "\n\nMAZO\n";

        for(Sorpresa carta : mazo){
            res = res + "\t" + carta.toString() + "\n";
        }

        res = res + "\n\nTABLERO\n";
        res = res + tablero.toString();

        res = res + "\n\nJUGADORES\n";
        for(Jugador jugador : jugadores){
            res = res + "\t" + jugador.toString() + "\n";
        }

        return res;
    }
   
    
    
 }
    

