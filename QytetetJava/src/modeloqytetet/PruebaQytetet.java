/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Alba Casillas  2ºA (A1)
 */
public class PruebaQytetet {
  
 /*  CÓDIGO DE LA PRACTICA 1
    static Qytetet juego = new Qytetet();
     
    private ArrayList Metodo1(ArrayList<Sorpresa> m){
         ArrayList<Sorpresa> aux = new ArrayList<>();
         for (Sorpresa s: m){ // for (Tipo t: array())
             if(s.getValor() > 0){
                 aux.add(new Sorpresa(s.getTexto(), s.getValor(),s.getTipo())) ;
             }   // ¿Sería valido escribir aux.add(s) para meter el objeto
                 //por el que estoy iterando en el nuevo array ?
         }
         
         return aux ;
     }
    
     private ArrayList Metodo2(ArrayList<Sorpresa> m){
         ArrayList<Sorpresa> aux = new ArrayList<>();
         for (Sorpresa s: m){
             if (s.getTipo()== TipoSorpresa.IRACASILLA){
                 aux.add(new Sorpresa(s.getTexto(), s.getValor(),s.getTipo())) ;   
             }
         }
         
         return aux ;
     }
     
     private ArrayList Metodo3(ArrayList<Sorpresa> m, TipoSorpresa tipo_s){
         ArrayList<Sorpresa> aux = new ArrayList<>() ;
         for (Sorpresa s: m){
             if (s.getTipo()== tipo_s){
                 aux.add(new Sorpresa(s.getTexto(), s.getValor(),s.getTipo())) ;
             }
         }
         
         return aux;
     }
  */
     /**
     * @param args the command line arguments
     */
    public static void principal(String[] args) {
        // TODO code application logic here
        
    /*   PruebaQytetet prueba1 = new PruebaQytetet(),
                      prueba2 = new PruebaQytetet(),
                      prueba3 = new PruebaQytetet() ;
        
       
        prueba1.Metodo1(juego.getMazo());
        prueba2.Metodo2(juego.getMazo());
        
        TipoSorpresa[] vector ;
        
        vector = TipoSorpresa.values() ;
        
        for (int i= 0; i < 10 ; i++){        
        prueba3.Metodo3(juego.getMazo(),vector[i]);
        }
        
        System.out.print(prueba1.toString());
        System.out.print(prueba2.toString());
        System.out.print(prueba3.toString());
     */ 
        
        Qytetet juego = Qytetet.getInstance();
        Scanner in = new Scanner(System.in); //No pongo el private static final 
                                             //porque si no, da fallo.
        
        ArrayList<String> nombres_jugadores = new ArrayList<>();
        
       
        System.out.println();
        System.out.println("Introduzca el numero de jugadores");
        int numero_jugadores = in.nextInt();
        
        while(numero_jugadores <= 1 || numero_jugadores > 4){
           System.out.println("Numero de jugadores incorrecto");
           numero_jugadores = in.nextInt(); 
        }
        in.nextLine();  //Para que el Scanner no coja la basurilla como un valor
        System.out.println("Introduzca sus nombres");
        String x;
        for(int i = 1; i<= numero_jugadores ; i++){
            System.out.print(i + ": ");
            x = in.nextLine();
            nombres_jugadores.add(x);
         }
        System.out.println();
        
        juego.inicializarJuego(nombres_jugadores);


       System.out.print(juego.toString());
       
       
       //Comprobamos que se cree correctamente el dado y que al tirarlo se 
       //genere un numero aleatorio del 1 al 6.
       
       System.out.println("El valor inicial del dado es: " + juego.getValorDado());
       
       juego.tirarDado();
       
       System.out.println("El nuevo valor del dado es: " + juego.getValorDado());
       
       
       
  
      //Comprobamos que los jugadores comiencen en la Casilla 0 (la inicial) 
       
       for(Jugador j : juego.getJugadores()){
           System.out.println(j.getNombre() + " está en la casilla " + j.getCasillaActual().getNumeroCasilla());
       }
       
        System.out.println("Todos los jugadores han comenzado en la casilla inicial");
        
        
        
       
        System.out.println("El primer jugador es: " + juego.getJugadorActual().getNombre());
        
        
        
        
        
   
       //Movimiento de los personajes
       //Probamos los metodos "mover" y "siguienteJugador"       
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.mover(1);
       
       System.out.println("\n\ty se mueve a la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       System.out.println("Jugador " + juego.getJugadorActual().getNombre() 
                + " tiene un saldo de: " +  juego.obtenerSaldoJugadorActual() + " euros." );
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la calle " 
               + juego.obtenerCasillaJugadorActual().getTitulo().getNombre());
       
        System.out.println(" El precio de la propiedad es de : " 
                + juego.obtenerCasillaJugadorActual().getTitulo().getPrecioCompra());  
        
       if(juego.comprarTituloPropiedad()){
         System.out.println("El jugador ha comprado la propiedad");
       }else{
           System.out.println("No se ha podido comprar la propiedad");
       }
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Edificar una casa cuesta: " 
                + juego.getJugadorActual().getCasillaActual().getTitulo().getPrecioEdificar());
        
        int contador = 0;
        
        while ( contador < 4){
         if(juego.edificarCasa(1)){
            System.out.println("El jugador ha edificado una casa");
         }else{
           System.out.println("No se ha podido edificar");
         }
         
         contador++;
       }

       System.out.println("El jugador ha edificado " + contador + " casas");
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("El jugador quiere edificar un hotel");
       
        if(juego.edificarHotel(1)){
            System.out.println("El jugador ha edificado un hotel");
        }else{
           System.out.println("No se ha podido edificar");
        }
        
        System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       
       
       
       
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.mover(2);
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       
       
       
       
       
       //Probamos si sirven lo métodos de aplicarSorpresa, metiendo a un jugador en la carcel
       
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.println("Jugador " + juego.getJugadorActual().getNombre() 
                + " tiene un saldo de: " +  juego.obtenerSaldoJugadorActual() + " euros." );
       
       Sorpresa irCarcel = new Sorpresa("Te han pillado consumiendo droga. Vas a la carcel",
                juego.getTablero().getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA);
       
       juego.setCartaActual(irCarcel);
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.aplicarSorpresa();
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       System.out.println("Ahora el jugador está en la cárcel");
      
       //Comprobamos que puede salir de la cárcel
       
       juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
       
       if(juego.getJugadorActual().getEncarcelado() == true){
           System.out.println("El jugador " + juego.getJugadorActual().getNombre() +
                   " no ha salido de la cárcel");
       }else{
           System.out.println("El jugador " + juego.getJugadorActual().getNombre() +
                   " ha salido de la cárcel pagando");
       }
       
        System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
        
        
        
        
       
       //Probamos si los jugadores pueden moverse un numero de casillas aleatorias
       //indicadas al tirar el dado
       
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.mover(juego.tirarDado());
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       
       
       
       
       
       
       //Probamos el funcionamiento del métdo "jugar"
       
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.jugar();
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       
       
       
       
       
       
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.mover(2);
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       System.out.println("Jugador " + juego.getJugadorActual().getNombre() 
                + " tiene un saldo de: " +  juego.obtenerSaldoJugadorActual() + " euros." );
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la calle " 
               + juego.getJugadorActual().getCasillaActual().getTitulo().getNombre());
       
        System.out.println(" El precio de la propiedad es de : " 
                + juego.obtenerCasillaJugadorActual().getTitulo().getPrecioCompra());  
        
       if(juego.comprarTituloPropiedad()){
         System.out.println("El jugador ha comprado la propiedad");
       }else{
           System.out.println("No se ha podido comprar la propiedad");
       }
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Se va a hipotecar la propiedad");
       
       juego.hipotecarPropiedad(4);
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("El jugador se lo ha pensado mejor y va a cancelar su hipoteca");
       juego.cancelarHipoteca(4);
       
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Edificar una casa cuesta: " 
                + juego.getJugadorActual().getCasillaActual().getTitulo().getPrecioEdificar());
        
       if(juego.edificarCasa(4)){
         System.out.println("El jugador ha edificado una casa");
       }else{
           System.out.println("No se ha podido edificar");
       }
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       
       
       
       
       
       //Comprobación del funcionamiento del tablero circular
      
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.println("Jugador " + juego.getJugadorActual().getNombre() 
            + " tiene un saldo de: " +  juego.obtenerSaldoJugadorActual() + " euros." );
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
               + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.mover(9);
       juego.mover(3);
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
        System.out.println("Se paga el alquiler de esta casilla ya que tiene dueño");
       
      System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
        
       
       
       
       //Funcionamiento cartas sorpresa
        System.out.println("Sorpresas PAGARCOBRAR");
        
       juego.siguienteJugador();
       System.out.println("Jugador " + juego.getJugadorActual().getNombre());
       
       System.out.println("Jugador " + juego.getJugadorActual().getNombre() 
            + " tiene un saldo de: " +  juego.obtenerSaldoJugadorActual() + " euros." );
       
       System.out.println("Gana 275 euros gracias a una carta sorpresa");
       
       Sorpresa s1 = new Sorpresa("Te has encontrado una cartera en la calle. "
                + "Ganas 275 euros", 275, TipoSorpresa.PAGARCOBRAR);
       
       juego.setCartaActual(s1);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Pierde 100 euros debido a una carta sorpresa");
       
       Sorpresa s2 = new Sorpresa("Invitas a tu novia a una cita romantica y quieres"
                + "estar a la altura. Te gastas en ella 100"
                + " euros", -100, TipoSorpresa.PAGARCOBRAR);
       
       juego.setCartaActual(s2);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Sorpresas PORCASAHOTEL");
       
       System.out.println("Ganas 60 euros por cada casa/hotel");
        
       Sorpresa s3 = new Sorpresa("Tu amigo de la infancia es arquitecto ¡y te ha hecho"
                + "unas construcciones de lujo! Cobras 60 euros por casa/hotel",
                60, TipoSorpresa.PORCASAHOTEL);
       
       juego.setCartaActual(s3);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Pierdes 95 euros por cada casa/hotel");
        
       Sorpresa s4 = new Sorpresa("Han descubierto el escondite de unos narcotraficanes"
                + "en una de tus construcciones. Ahora debes pagar 95 euros por"
                + "cada casa/hotel", -95, TipoSorpresa.PORCASAHOTEL);
       
       juego.setCartaActual(s4);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Sorpresas PORJUGADOR");
       
       System.out.println("Ganas 20 euros de cada jugador");
        
       Sorpresa s5 = new Sorpresa("Es tu cumpleaños. Tus amigos te dan 20 euros cada"
                + "uno", 20, TipoSorpresa.PORJUGADOR);
       
       juego.setCartaActual(s5);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
       System.out.println("Pierdes 15 euros de cada jugador");
        
       Sorpresa s6 = new Sorpresa("Sales de la discoteca e invitas a tus amigos a ir"
                + "de after. Pagas 15 euros a cada uno", -15, TipoSorpresa.PORJUGADOR);
       
       juego.setCartaActual(s6);
       
       juego.aplicarSorpresa();
       
       System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
       
        System.out.println("Sorpesas IRACASILLA");
        
        Sorpresa s7 = new Sorpresa("Quieres ir a la discoteca Copera, obviamente en la"
                + " calle Copera. Avanzas a la casilla 14.", 14, TipoSorpresa.IRACASILLA);
       
       juego.setCartaActual(s7);
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
              + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.aplicarSorpresa();
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       
       Sorpresa s8 = new Sorpresa("Tu novia vive en la calle Salsipuedes y vas a su"
                + "casa para darle una sorpresa. Avanzas a la casilla 18",18,
                TipoSorpresa.IRACASILLA);
       
       juego.setCartaActual(s8);
       
       System.out.print(juego.getJugadorActual().getNombre() + " está en la casilla " 
              + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
       juego.aplicarSorpresa();
       
       System.out.println("\n\ty se mueve a la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
       
    
       //PROBAR ESPECULADOR
       Sorpresa s9 = new Sorpresa("Como eres el más chulo del barrio, te vuelves un Especulador",
              3000, TipoSorpresa.CONVERTIRME);
       
       
       juego.setCartaActual(s9);
       
       juego.aplicarSorpresa();
       System.out.println("El jugador actual ahora es de la clase: " + juego.getJugadorActual().getClass().getSimpleName());
      //System.out.println(juego.getJugadorActual().getClass().getSimpleName()); TE DICE DE QUE CLASE ES
       
        for(Jugador j: juego.getJugadores()){ 
            System.out.println("\t" + j.toString());
        }
        
        System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
        
        
          System.out.print(juego.getJugadorActual().getNombre() + " está en la calle " 
               + juego.obtenerCasillaJugadorActual().getTitulo().getNombre());
       
        System.out.println(" El precio de la propiedad es de : " 
                + juego.obtenerCasillaJugadorActual().getTitulo().getPrecioCompra());  
        
        
        System.out.println("El especulador va a pagar el impuesto");
        
      
        juego.getJugadorActual().pagarImpuesto();
        
        System.out.println("Su saldo actual es de: " + juego.obtenerSaldoJugadorActual() + " euros.");
        
        
        
        
        System.out.println("Propiedades de los jugadores");

        for(int nj=0; nj<juego.getJugadores().size(); nj++){
            System.out.print("Las propiedades del jugador: " + juego.getJugadorActual().getNombre() + " son: [");
            ArrayList<Integer> indicePropiedades = juego.obtenerPropiedadesJugador();

            for(int pi = 0; pi<indicePropiedades.size(); pi++){
                System.out.print(" " + indicePropiedades.get(pi));
            }
            System.out.println("]");

            juego.siguienteJugador();
        }
        
        
        
        System.out.println("Propiedades de los jugadores segun estado de hipoteca (hipotecadas)");

        for(int nj=0; nj<juego.getJugadores().size(); nj++){
            System.out.print("Las propiedades del jugador: " + juego.getJugadorActual().getNombre() + " son: [");
            ArrayList<Integer> indicePropiedades = juego.obtenerPropiedadesJugadorSegunEstadoHipoteca(true);

            for(int pi = 0; pi<indicePropiedades.size(); pi++){
                System.out.print(" " + indicePropiedades.get(pi));
            }
            System.out.println("]");

            juego.siguienteJugador();
        }
        
   
       
      
        System.out.println("Ranking de los jugadores: ");
       
        
        juego.obtenerRanking();
        
        for(Jugador a : juego.getJugadores()){
            System.out.println(a.getNombre() + " con capital: " + a.obtenerCapital()); 
        }
       
    }
   
    
}