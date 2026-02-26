package arbolabecedario;

/**
 * Clase que representa el árbol general.
 * Se encarga de construir y almacenar la estructura
 * completa del árbol con todos sus nodos.
 */
public class ArbolGeneral {

    private Nodo raiz;

    // ── Constructor ─────────────────────────────────
    public ArbolGeneral() {
        this.raiz = construirArbol();
    }

    // ── Construcción del árbol ──────────────────────
    private Nodo construirArbol() {

        // Nivel 0 - Raíz
        Nodo A = new Nodo('A');

        // Nivel 1
        Nodo B = new Nodo('B');
        Nodo C = new Nodo('C');
        Nodo D = new Nodo('D');
        Nodo E = new Nodo('E');

        // Nivel 2
        Nodo F = new Nodo('F');
        Nodo I = new Nodo('I');
        Nodo J = new Nodo('J');
        Nodo N = new Nodo('N');

        // Nivel 3
        Nodo G = new Nodo('G');
        Nodo K = new Nodo('K');
        Nodo P = new Nodo('P');
        Nodo Q = new Nodo('Q');

        // Nivel 4
        Nodo H = new Nodo('H');
        Nodo L = new Nodo('L');
        Nodo M = new Nodo('M');

        // ── Relaciones padre → hijo ──────────────────
        A.agregarHijo(B);
        A.agregarHijo(C);
        A.agregarHijo(D);
        A.agregarHijo(E);

        B.agregarHijo(F);

        C.agregarHijo(I);
        C.agregarHijo(J);

        E.agregarHijo(N);

        F.agregarHijo(G);

        J.agregarHijo(K);

        N.agregarHijo(P);
        N.agregarHijo(Q);

        G.agregarHijo(H);

        K.agregarHijo(L);
        K.agregarHijo(M);

        return A;
    }

    // ── Getter ───────────────────────────────────────
    public Nodo getRaiz() {
        return raiz;
    }
}