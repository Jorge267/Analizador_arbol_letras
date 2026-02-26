package arbolabecedario;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene todos los métodos de análisis del árbol.
 * Responde preguntas sobre posiciones, hijos y recorridos.
 */
public class ArbolAnalyzer {

    private final Nodo raiz;

    // ── Constructor ──────────────────────────────────
    public ArbolAnalyzer(Nodo raiz) {
        this.raiz = raiz;
    }

    // ── Pregunta 13: ¿Está F a la izquierda de J? ───
    public boolean estaALaIzquierda(char valorA, char valorB) {
        Nodo nodoA = buscarNodo(raiz, valorA);
        Nodo nodoB = buscarNodo(raiz, valorB);
        if (nodoA == null || nodoB == null) return false;
        return nodoA.x < nodoB.x;
    }

    // ── Pregunta 14: ¿Está L a la derecha de J? ─────
    public boolean estaALaDerecha(char valorA, char valorB) {
        Nodo nodoA = buscarNodo(raiz, valorA);
        Nodo nodoB = buscarNodo(raiz, valorB);
        if (nodoA == null || nodoB == null) return false;
        return nodoA.x > nodoB.x;
    }

    // ── Pregunta 15: Valores a izquierda y derecha ──
    public List<Character> valoresALaIzquierdaDe(char valor) {
        Nodo nodo = buscarNodo(raiz, valor);
        List<Character> resultado = new ArrayList<>();
        if (nodo == null) return resultado;
        buscarValoresPorLado(raiz, nodo.x, resultado, "izquierda");
        return resultado;
    }

    public List<Character> valoresALaDerechaDe(char valor) {
        Nodo nodo = buscarNodo(raiz, valor);
        List<Character> resultado = new ArrayList<>();
        if (nodo == null) return resultado;
        buscarValoresPorLado(raiz, nodo.x, resultado, "derecha");
        return resultado;
    }

    // ── Pregunta 16: Hijos directos de un nodo ──────
    public int contarHijosDirectos(char valor) {
        Nodo nodo = buscarNodo(raiz, valor);
        if (nodo == null) return 0;
        return nodo.getHijos().size();
    }

    public List<Character> hijosDirectos(char valor) {
        Nodo nodo = buscarNodo(raiz, valor);
        List<Character> resultado = new ArrayList<>();
        if (nodo == null) return resultado;
        for (Nodo hijo : nodo.getHijos()) {
            resultado.add(hijo.getValor());
        }
        return resultado;
    }

    // ── Pregunta 17: Recorridos ──────────────────────
    public List<Character> preorden() {
        List<Character> resultado = new ArrayList<>();
        recorrerPreorden(raiz, resultado);
        return resultado;
    }

    public List<Character> postorden() {
        List<Character> resultado = new ArrayList<>();
        recorrerPostorden(raiz, resultado);
        return resultado;
    }

    public List<Character> inorden() {
        List<Character> resultado = new ArrayList<>();
        recorrerInorden(raiz, resultado);
        return resultado;
    }

    // ── Algoritmos de recorrido ──────────────────────

    // Preorden: raíz → hijos de izquierda a derecha
    private void recorrerPreorden(Nodo nodo, List<Character> resultado) {
        if (nodo == null) return;
        resultado.add(nodo.getValor());
        for (Nodo hijo : nodo.getHijos()) {
            recorrerPreorden(hijo, resultado);
        }
    }

    // Postorden: hijos de izquierda a derecha → raíz
    private void recorrerPostorden(Nodo nodo, List<Character> resultado) {
        if (nodo == null) return;
        for (Nodo hijo : nodo.getHijos()) {
            recorrerPostorden(hijo, resultado);
        }
        resultado.add(nodo.getValor());
    }

    // Inorden: primer hijo → raíz → resto de hijos
    private void recorrerInorden(Nodo nodo, List<Character> resultado) {
        if (nodo == null) return;
        List<Nodo> hijos = nodo.getHijos();
        if (!hijos.isEmpty()) {
            recorrerInorden(hijos.get(0), resultado);
        }
        resultado.add(nodo.getValor());
        for (int i = 1; i < hijos.size(); i++) {
            recorrerInorden(hijos.get(i), resultado);
        }
    }

    // ── Helpers ──────────────────────────────────────

    // Busca un nodo por su valor recorriendo todo el árbol
    private Nodo buscarNodo(Nodo nodo, char valor) {
        if (nodo == null) return null;
        if (nodo.getValor() == valor) return nodo;
        for (Nodo hijo : nodo.getHijos()) {
            Nodo resultado = buscarNodo(hijo, valor);
            if (resultado != null) return resultado;
        }
        return null;
    }

    // Busca valores de nodos que estén a un lado de una x dada
    private void buscarValoresPorLado(Nodo nodo, int x, List<Character> resultado, String lado) {
        if (nodo == null) return;
        if (lado.equals("izquierda") && nodo.x < x) resultado.add(nodo.getValor());
        if (lado.equals("derecha")   && nodo.x > x) resultado.add(nodo.getValor());
        for (Nodo hijo : nodo.getHijos()) {
            buscarValoresPorLado(hijo, x, resultado, lado);
        }
    }
}