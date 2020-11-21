/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorqytetet;

import java.util.ArrayList;
import java.util.Collections;
import modeloqytetet.Casilla;
import modeloqytetet.EstadoJuego;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;
import modeloqytetet.Jugador;

/**
 *
 * @author albil
 */
public class ControladorQytetet {
    private static final ControladorQytetet instance = new ControladorQytetet();
    private ArrayList<String> nombreJugadores ;
    private static Qytetet modelo;
    private OpcionMenu opcion; 
    
    private ControladorQytetet(){
       nombreJugadores = new ArrayList<>(); 
    }
    
     public static ControladorQytetet getInstance()throws IllegalArgumentException {
        modelo = Qytetet.getInstance();
        return instance;
    }
     
     public void setNombreJugadores(ArrayList<String> nombreJugadores){
         this.nombreJugadores = nombreJugadores;
     }
     
     public ArrayList<Integer> obtenerOperacionesJuegoValidas(){
        ArrayList<Integer> opcionesValidas = new ArrayList<>();
        
        if(modelo.getJugadores().size() == 0){
            opcionesValidas.add(OpcionMenu.INICIARJUEGO.ordinal());
        }else{
            opcionesValidas.add(OpcionMenu.TERMINARJUEGO.ordinal());
            opcionesValidas.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
            opcionesValidas.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
            opcionesValidas.add(OpcionMenu.MOSTRARTABLERO.ordinal());
            
            if(modelo.getEstadoJuego() == EstadoJuego.JA_PREPARADO){
                opcionesValidas.add(OpcionMenu.JUGAR.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADO){
                opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD){
                opcionesValidas.add(OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.ordinal());
                opcionesValidas.add(OpcionMenu.INTENTARSALIRCARCELTIRANDODADO.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDECOMPAROGESTIONAR){
                opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
                opcionesValidas.add(OpcionMenu.COMPRARTITULOPROPIEDAD.ordinal());
                opcionesValidas.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                opcionesValidas.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                opcionesValidas.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                opcionesValidas.add(OpcionMenu.EDIFICARCASA.ordinal());
                opcionesValidas.add(OpcionMenu.EDIFICARHOTEL.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDEGESTIONAR){
                opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
                opcionesValidas.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                opcionesValidas.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                opcionesValidas.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                opcionesValidas.add(OpcionMenu.EDIFICARCASA.ordinal());
                opcionesValidas.add(OpcionMenu.EDIFICARHOTEL.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.JA_CONSORPRESA){
                opcionesValidas.add(OpcionMenu.APLICARSORPRESA.ordinal());
            }else if (modelo.getEstadoJuego() == EstadoJuego.ALGUNJUGADORENBANCARROTA){
                opcionesValidas.add(OpcionMenu.OBTENERRANKING.ordinal());
            }
        }
        
        Collections.sort(opcionesValidas);
        return opcionesValidas;
      
     }
     
     public boolean necesitaElegirCasilla(int opcionMenu){
         ArrayList<Integer> opcionModifica = new ArrayList<>();
         
         opcionModifica.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
         opcionModifica.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
         opcionModifica.add(OpcionMenu.EDIFICARCASA.ordinal());
         opcionModifica.add(OpcionMenu.EDIFICARHOTEL.ordinal());
         opcionModifica.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
         
         for (Integer om : opcionModifica){
             if (opcionMenu == om){
                 return true;
             }
         }
         
         return false; 
     }
     
     public ArrayList<Integer> obtenerCasillasValidas(int opcionMenu){
        ArrayList<Integer> props = new ArrayList<>();
        
        opcion = OpcionMenu.values()[opcionMenu]; //Me devuelve el valor que se encuentra en la pos de opcionMenu
        
        if(opcion == OpcionMenu.HIPOTECARPROPIEDAD){
            props = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        }else if (opcion == OpcionMenu.CANCELARHIPOTECA){
            props = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(true);
        }else if (opcion == OpcionMenu.EDIFICARCASA){
            props = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        }else if (opcion == OpcionMenu.EDIFICARHOTEL){
            props = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        }else if (opcion == OpcionMenu.VENDERPROPIEDAD){
            props = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        }
       
        return props;
     }
     
     public String realizarOperacion(int opcionElegida, int casillaElegida){
         String s = "";
         
         opcion = OpcionMenu.values()[opcionElegida];
         
         switch(opcion){
            case INICIARJUEGO:
              s = "¡Qué empiece el juego!";
              modelo.inicializarJuego(nombreJugadores);
            break;  
             
            case APLICARSORPRESA:
              s = "Se va a aplicar la carta sorpresa: " + modelo.getCartaActual().toString();
              modelo.aplicarSorpresa();
            break;

            case CANCELARHIPOTECA:
               s = "El jugador " + modelo.getJugadorActual().getNombre() + " cancela la hipoteca de la " + casillaElegida;
               modelo.cancelarHipoteca(casillaElegida);
            break;

            case COMPRARTITULOPROPIEDAD:
               s = "El jugador: " + modelo.getJugadorActual().getNombre() + " va a compar la calle " + modelo.obtenerCasillaJugadorActual().getNumeroCasilla();
               modelo.comprarTituloPropiedad();
            break;

            case EDIFICARCASA:
                s = "Se va a edificar una casa en la calle " + casillaElegida;
                modelo.edificarCasa(casillaElegida);
            break;

            case EDIFICARHOTEL:
                s = "Se va a edificar un hotel en la calle " + casillaElegida;
                modelo.edificarHotel(casillaElegida);
            break;

            case HIPOTECARPROPIEDAD:
                 s = "El jugador " + modelo.getJugadorActual().getNombre() + " hipoteca la calle " + casillaElegida;
                modelo.hipotecarPropiedad(casillaElegida);
            break;

            case INTENTARSALIRCARCELPAGANDOLIBERTAD:
                s = "Intentado salir de la carcel PAGANDOLIBERTAD";
                modelo.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            break;

            case INTENTARSALIRCARCELTIRANDODADO:
                s = "Intentando salir de la carcel TIRANDODADO";
                modelo.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                
                if(modelo.getJugadorActual().getEncarcelado() == false){
                    s += "¡Ha habido suerte! Te has librado de la carcel";
                }else{
                    s += "Qué pena... Tendrás que estar encarcelado un poco más";    
                }
            break;

            case JUGAR:
                s = "El jugador " + modelo.getJugadorActual().getNombre() + " va a jugar su turno\n";
                Casilla cas = modelo.obtenerCasillaJugadorActual();
                s = s + "\n\tCasilla Actual del jugador " + cas.getNumeroCasilla();
                modelo.jugar();
                cas = modelo.obtenerCasillaJugadorActual();
                 s = s + "\tAl lanzar el dado ha salido " + modelo.getDado().getValor() + " y ha avanzado hasta la casilla " + cas.getNumeroCasilla();
            break;

            case MOSTRARJUGADORACTUAL:
               s = "Jugador actual: " + modelo.getJugadorActual().toString();
            break;

            case MOSTRARJUGADORES:
               for (Jugador i : modelo.getJugadores()){
                   s += "Jugador: " + i.toString();
               } 
            break;

            case MOSTRARTABLERO:
                s = "Tablero: " + modelo.getTablero().toString();
            break;

            case OBTENERRANKING:
                s = "Obteniendo ranking de los jugadores...";
                modelo.obtenerRanking();
                for(Jugador a : modelo.getJugadores()){
                   s+= a.getNombre() + " con capital: " + a.obtenerCapital() + "\n";  
                }
            break;

            case PASARTURNO:
                s = "¡Turno del siguiente jugador!";
                modelo.siguienteJugador();
                s += "\nEl jugador pasa a ser: " + modelo.getJugadorActual().getNombre(); 
            break;

            case TERMINARJUEGO:
                s = "El juego ha terminado. Calculando ranking de los jugadores...\n";
                modelo.obtenerRanking();
                
                for(Jugador a : modelo.getJugadores()){
                   s+= a.getNombre() + " con capital: " + a.obtenerCapital() + "\n";  
                }
            break;

            case VENDERPROPIEDAD:
                s = "El jugador: " + modelo.getJugadorActual().getNombre() + " va a vender la propiedad";
                modelo.venderPropiedad(casillaElegida);
            break;
         }
         
         return s;
     }
    
}
