package arbolabecedario;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un nodo del árbol general.
 * Contiene el valor del nodo, su lista de hijos
 * y sus coordenadas visuales en pantalla.
 */
public class Nodo {
    
    private char valor;
    private List<Nodo> hijos;
    
    int x;
    int y;
    
    public Nodo(char valor){
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }
    
    public void agregarHijo (Nodo hijo) {
        hijos.add(hijo);
    }
    
    public boolean esHoja (){
        return hijos.isEmpty();
    }
    
    public char getValor () {
        return valor;
    }
    
    public List<Nodo> getHijos () {
        return hijos;
    }
}
    
    