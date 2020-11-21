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
public abstract class Casilla {
    private int numeroCasilla ;
    private int precio_compra = 0;

    public Casilla(int num){
        this.numeroCasilla = num;
    }
    
   /* public Casilla(int num, TituloPropiedad tit){
        numeroCasilla = num;
        tipo = TipoCasilla.CALLE;
        titulo = tit;
        precio_compra = tit.getPrecioCompra();
    }
    
    public Casilla(int num, TipoCasilla tip){
        numeroCasilla = num;
        precio_compra = 0;
        tipo = tip;
        titulo = null;
    }
   */
    
    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getPrecioCompra() {
        return precio_compra;
    }

    public void setPrecioCompra(int precio_compra) {
        this.precio_compra = precio_compra;
    }
    
    

    protected abstract TipoCasilla getTipo();
    

    protected abstract TituloPropiedad getTitulo();
    
    protected abstract boolean soyEdificable();

    
    
 /*   TituloPropiedad asignarPropietario(Jugador jugador){
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
    
    boolean soyEdificable(){
        boolean es_edificable = false;
        
       // if(tipo == TipoCasilla.CALLE){ 
       if(tipo.equals(TipoCasilla.CALLE)){
            es_edificable = true;
        }
        
        return es_edificable;
    }
    
    boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    
    @Override
    public String toString(){
        if (tipo == TipoCasilla.CALLE){
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + 
                ", precio_compra=" + precio_compra + ", tipo=" + tipo 
                + ", titulo=" + titulo + '}';
        }else{
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + 
                ", precio_compra=" + precio_compra + ", tipo=" + tipo 
                + '}';   
        }   
    }
*/
}