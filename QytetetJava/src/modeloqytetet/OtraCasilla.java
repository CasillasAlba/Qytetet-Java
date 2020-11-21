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
public class OtraCasilla extends Casilla {
    TipoCasilla tipo;
    
    public OtraCasilla(int num, TipoCasilla tipo){
        super(num);
        this.tipo = tipo;
        if(this.tipo == TipoCasilla.IMPUESTO){
            setPrecioCompra(Qytetet.IMPUESTO);
        }
    }

    @Override
    protected TipoCasilla getTipo() {
       return tipo;
    }

    @Override
    protected TituloPropiedad getTitulo() {
        return null;
    }

    @Override
    protected boolean soyEdificable() {
        return false;
    }

    @Override
    public String toString() {
        return "Casilla{" + "numeroCasilla=" + getNumeroCasilla() + 
                ", tipo=" + tipo 
                + '}';
    }
       
}
