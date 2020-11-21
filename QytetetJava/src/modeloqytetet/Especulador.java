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
public class Especulador extends Jugador {
    private int fianza;
    
    public Especulador(String n, int f){
        super(n);
        this.fianza = f;
    }
    
   Especulador(Jugador jugador,int fianza){
      super(jugador);
      this.fianza = fianza;
   }
   
    
    @Override //Para definir de manera diferente un metodo ya definido de la clase que heredas
    protected void pagarImpuesto(){
        modificarSaldo(-(getCasillaActual().getPrecioCompra()/2));
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        return this;
    }
    
    @Override
    protected boolean deboIrACarcel(){
        boolean ir_a_carcel = false;
        if(super.deboIrACarcel() && !pagarFianza()){
            ir_a_carcel = true;
        }
        
        return ir_a_carcel;
    }
    
    private boolean pagarFianza(){
        boolean tengo_saldo = false;
        
        if ( tengoSaldo(fianza)){
            tengo_saldo = true;
            modificarSaldo(-fianza);
        }
        
        return tengo_saldo;   
    }
    
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        boolean puedo_edificar = false;
        int numCasas;
        int costeEdificarCasa;
        
        numCasas = titulo.getNumCasas();
        
        if(numCasas < 8){
           costeEdificarCasa = titulo.getPrecioEdificar(); 
           boolean tengoSaldo;
           tengoSaldo = tengoSaldo(costeEdificarCasa);
           
           if(tengoSaldo){
               puedo_edificar = true; 
           }
        }

        return puedo_edificar;  
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        boolean puedo_edificar = false;
        int numCasas, numHoteles;
        int costeEdificarHotel;
        
        numCasas = titulo.getNumCasas();
        
        if (numCasas >= 4){
            numHoteles = titulo.getNumHoteles();
            if(numHoteles < 8){
                costeEdificarHotel = titulo.getPrecioEdificar();
                boolean tengoSaldo;
                tengoSaldo = tengoSaldo(costeEdificarHotel);
                
                if(tengoSaldo){
                    puedo_edificar = true;
                }
            }
        }
       return puedo_edificar; 
    } 

    @Override
    public String toString() {
        String res;
        res = super.toString();
        res += "Especulador{" + "fianza=" + fianza + '}';
        
        return res;
    }
      
}
