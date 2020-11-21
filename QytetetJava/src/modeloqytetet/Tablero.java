/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author albil
 */
public class Tablero {
    private ArrayList<Casilla> casillas ;
    private Casilla carcel ;

    public Tablero() {
        inicializar();
    }

    ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    Casilla getCarcel() {
        return carcel;
    }
    
    private void inicializar(){
        casillas = new ArrayList<>();
        
        casillas.add(new OtraCasilla(0,TipoCasilla.SALIDA));
        casillas.add(new Calle(1,new TituloPropiedad("Calle"
                + "Arabasta",500,70,50,115,130)));
        casillas.add(new Calle(2,new TituloPropiedad("Calle"
                + "Abrazamozas",732,240,73,160,220)));
        casillas.add(new OtraCasilla(3,TipoCasilla.PARKING));
        casillas.add(new Calle(4,new TituloPropiedad("Calle"
                + "Porriño",900,305,90,540,577)));
        casillas.add(new Calle(5,new TituloPropiedad("Calle"
                + "de la Duda",666,80,66,200,215)));
        casillas.add(new OtraCasilla(6,TipoCasilla.SORPRESA));
        casillas.add(new Calle(7,new TituloPropiedad("Calle"
                + "Quilombo",1080,256,108,640,610)));
        casillas.add(new Calle(8,new TituloPropiedad("Calle"
                + "de los Gorrones",835,136,83,490,415)));
        casillas.add(new OtraCasilla(9,TipoCasilla.CARCEL));
        casillas.add(new Calle(10,new TituloPropiedad("Calle"
                + "Parderrubias",699,160,69,340,355)));
        casillas.add(new Calle(11,new TituloPropiedad("Calle"
                + "Dressrosa",1000,213,100,675,690)));
        casillas.add(new OtraCasilla(12,TipoCasilla.SORPRESA));
        casillas.add(new Calle(13,new TituloPropiedad("Calle"
                + "Copera",2005,310,200,760,830)));
        casillas.add(new OtraCasilla(14,TipoCasilla.IMPUESTO));
        casillas.add(new Calle(15,new TituloPropiedad("Calle"
                + "Estrambótica",1365,195,136,727,800)));
        casillas.add(new OtraCasilla(16,TipoCasilla.SORPRESA));
        casillas.add(new Calle(17,new TituloPropiedad("Calle"
                + "Salsipuedes",1340,160,134,710,750)));
        casillas.add(new Calle(18,new TituloPropiedad("Calle"
                + "Raftel",2300,287,230,800,830)));
        casillas.add(new OtraCasilla(19,TipoCasilla.JUEZ));
        
        carcel = casillas.get(9);
    }
    
    
    boolean esCasillaCarcel(int numeroCasilla){
        boolean es_casilla = false;
        
        if(casillas.get(numeroCasilla)== carcel){
            es_casilla = true;
        }
        
        return es_casilla;
    }
    
    Casilla obtenerCasillaFinal(Casilla casilla, int desplazamiento){
        int pos = (casilla.getNumeroCasilla() + desplazamiento)%Qytetet.NUM_CASILLAS;
        return casillas.get(pos);
    }
    
    //Precondición: numeroCasilla > 0 && numeroCasilla < numero_max_casillas
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    
    @Override
    public String toString() {
        String salida = "Tablero: \n";
        
        for (Casilla c : casillas){
            salida += "\t" + c.toString() + "\n";
        }
        salida += "\tCarcel: " + carcel.toString() + "\n}";
        
        return salida;
        //return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + '}';
    }
    
}


