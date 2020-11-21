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
public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor ;
    
    public Sorpresa(String otro_texto,int otro_valor, TipoSorpresa otro_tipo){
        texto = otro_texto ;
        tipo = otro_tipo ;
        valor = otro_valor ;
    }
    
    String getTexto(){
     return texto;
    }
    
    TipoSorpresa getTipo(){
        return tipo ;
    }
    
    int getValor(){
        return valor ;
    }
    
    @Override
    public String toString(){
        return "\tSorpresa{" + "texto=" + texto + ", valor=" +
                Integer.toString(valor) + ",tipo=" + tipo + "}" ;
        
    }
    
}
