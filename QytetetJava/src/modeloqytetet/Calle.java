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
public class Calle extends Casilla {
    private TituloPropiedad titulo;
    private TipoCasilla tipo;
    
    public Calle(int num, TituloPropiedad tit){
        super(num);
        tipo = TipoCasilla.CALLE;
        titulo = tit;
        setPrecioCompra(tit.getPrecioCompra());
    }

    @Override
    public TituloPropiedad getTitulo() {
        return titulo;
    }

    @Override
    public TipoCasilla getTipo() {
        return tipo;
    }
    
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        
        return titulo;
    }
    
    
    int pagarAlquiler(){
        int costeAlquiler;
                
        costeAlquiler = titulo.pagarAlquiler();
         
        return costeAlquiler;
    }
    
    
    boolean propietarioEncarcelado(){
       return titulo.propietarioEncarcelado();
    }
    
    @Override
    protected boolean soyEdificable(){
        return true;
    }
    
    
    boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    
    @Override
    public String toString(){
         return "Calle{" + "numeroCasilla=" + getNumeroCasilla() + 
            ", precio_compra=" + getPrecioCompra() + ", tipo=" + tipo 
            + ", titulo=" + titulo + '}';
   
    }
    
}
