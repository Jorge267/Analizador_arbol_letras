package arbolabecedario;

/**
 * Clase responsable de calcular las coordenadas (x, y)
 * de cada nodo para su correcta visualización en pantalla.
 * Utiliza un algoritmo recursivo de distribución horizontal.
 */
public class LayoutCalculator {

    private static final int NIVEL_ALTO  = 90;  // Espacio vertical entre niveles
    private static final int ESPACIO_X   = 70;  // Espacio horizontal entre hojas
    private static final int MARGEN_TOP  = 60;  // Margen superior inicial

    // ── Punto de entrada ────────────────────────────
    public void calcular(Nodo raiz) {
        int[] xCounter = { MARGEN_TOP };
        calcularRecursivo(raiz, 0, xCounter);
    }

    // ── Algoritmo recursivo ─────────────────────────
    private int calcularRecursivo(Nodo nodo, int nivel, int[] xCounter) {

        nodo.y = MARGEN_TOP + (nivel * NIVEL_ALTO);

        // Si es hoja, se le asigna la siguiente posición x disponible
        if (nodo.esHoja()) {
            nodo.x = xCounter[0];
            xCounter[0] += ESPACIO_X;
            return nodo.x;
        }

        // Si tiene hijos, el nodo se centra entre el primer y último hijo
        int primerX = -1;
        int ultimoX = -1;

        for (Nodo hijo : nodo.getHijos()) {
            int cx = calcularRecursivo(hijo, nivel + 1, xCounter);
            if (primerX == -1) primerX = cx;
            ultimoX = cx;
        }

        nodo.x = (primerX + ultimoX) / 2;
        return nodo.x;
    }
}