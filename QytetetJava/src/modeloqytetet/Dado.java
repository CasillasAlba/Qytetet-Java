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
public class Dado {
    private int valor;
    private static final Dado instance = new Dado();
    
    private Dado (){
       // valor = tirar();
    }
    
    public int getValor() {
        return valor;
    }

    public static Dado getInstance()throws IllegalArgumentException {
        return instance;
    }

    int tirar(){
     valor = (int)(Math.random()*6+1);
     
     return valor;
    }

    @Override
    public String toString() {
        return "Dado{" + "valor=" + valor + '}';
    }
    
    
}
