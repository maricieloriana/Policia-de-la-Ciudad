package modelo;

import java.util.ArrayList;

/**
 * Representa una banda delictiva registrada en el sistema.
 * 
 * Contiene la información relacionada con sus integrantes.
 */
public class Banda {
    private int numero;
    private int cantidadMiembros;
    private ArrayList<PersonaDetenida> integrantes;
    
    public Banda(){
        integrantes = new ArrayList<>();
    }

    public Banda(int numero, int cantidadMiembros) {
        this.numero = numero;
        this.cantidadMiembros = cantidadMiembros;
        this.integrantes = new ArrayList<>();
    }
    
    public int getNumero(){
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public int getCantidadMiembros(){
        return cantidadMiembros;
    }
    
    public void setCantidadMiembros(int cantidadMiembros){
        this.cantidadMiembros = cantidadMiembros;
    }

    public ArrayList<PersonaDetenida> getIntegrantes() {
        return integrantes;
    }
    
    public void agregarIntegrante(PersonaDetenida integrante){
        if(!integrantes.contains(integrante)){
            integrantes.add(integrante);
            integrante.setBanda(this);
        }
    }
    
    public void eliminarIntegrante(PersonaDetenida integrante){
        if(integrantes.remove(integrante)){
            integrante.setBanda(this);
            
        }
    }

    @Override
    public String toString() {
        return numero + ";" + cantidadMiembros;
    }
    
    
}
