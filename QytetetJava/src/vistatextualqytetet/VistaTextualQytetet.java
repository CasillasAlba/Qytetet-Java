/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistatextualqytetet;

import java.util.ArrayList;
import java.util.Scanner;
import controladorqytetet.*;
import modeloqytetet.Qytetet;

/**
 *
 * @author albil
 */
public class VistaTextualQytetet {
    private static ControladorQytetet controlador;
    private static Qytetet modelo;
    private static VistaTextualQytetet ui;
    private static final Scanner in = new Scanner(System.in);
    
    
    public ArrayList<String> obtenerNombreJugadores(){        
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
        
        return nombres_jugadores;
    }
    
    public int elegirCasilla(int opcionMenu){
       ArrayList<Integer> casillasValidas = new ArrayList<>();
       ArrayList<String> conversion = new ArrayList<>();
       String res;
       int resultado;
       
       casillasValidas = controlador.obtenerCasillasValidas(opcionMenu);
       
        for (Integer i : casillasValidas){
            conversion.add(Integer.toString(i));
        }
       
       
       if (casillasValidas.isEmpty()){
           resultado = -1;
       }else{
           
           System.out.print("Casillas validas:\t ");
           
           for (Integer c : casillasValidas){
               System.out.print(c + " ");
               
           }
           System.out.println("");
           res = leerValorCorrecto(conversion);
           resultado = Integer.parseInt(res);
       }
      
       return resultado;
       
    }
    
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos){
     String valor = " "; 
    
    while (true){
        valor = in.nextLine(); //Recoge un texto si o si
        for (String s : valoresCorrectos){
            if (valor.equals(s)){
                return valor;
            }
        }
            
        System.out.println("Error, opcion incorrecta, vuelve a introducirla");
     
    }
      
    }
    
    public int elegirOperacion(){
        ArrayList<Integer> opcionesValidas = new ArrayList<>();
        ArrayList<String> conversion = new ArrayList<>();
        String res;
        int resultado;

        opcionesValidas = controlador.obtenerOperacionesJuegoValidas();
        
        for (Integer i : opcionesValidas){
            conversion.add(Integer.toString(i));
        }
        
        System.out.println("OPCIONES A ELEGIR: ");
        for(Integer i : opcionesValidas){
            System.out.println(i + " --> " + OpcionMenu.values()[i].toString() ); 
        }
        res = leerValorCorrecto(conversion);
        
        resultado = Integer.parseInt(res);
        
        
        return resultado;  
    }
    
    public static void main(String [] args){
     System.out.println("Vista textual");
     ui = new VistaTextualQytetet();
     
     controlador = ControladorQytetet.getInstance();
     modelo = Qytetet.getInstance();
     
     controlador.setNombreJugadores(ui.obtenerNombreJugadores());
     int operacionElegida, casillaElegida = 0;
     boolean necesitaElegirCasilla;
     
     do{ 
         
        operacionElegida = ui.elegirOperacion();
        necesitaElegirCasilla = controlador.necesitaElegirCasilla(operacionElegida);
        if (necesitaElegirCasilla)   
            casillaElegida = ui.elegirCasilla(operacionElegida);
        if (!necesitaElegirCasilla || casillaElegida >= 0)
            System.out.println(controlador.realizarOperacion(operacionElegida, casillaElegida)); 
     }while (operacionElegida != OpcionMenu.TERMINARJUEGO.ordinal());
             
    }
}
