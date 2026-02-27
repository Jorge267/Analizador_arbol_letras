package arbolabecedario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

/**
 * Panel lateral con scroll que muestra los resultados
 * del análisis del árbol automáticamente al abrir.
 */
public class PanelAnalisis extends JPanel {

    private final ArbolAnalyzer analyzer;

    public PanelAnalisis(ArbolAnalyzer analyzer) {
        this.analyzer = analyzer;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(260, 0));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));

        add(crearTitulo(),    BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
    }

    private JLabel crearTitulo() {
        JLabel titulo = new JLabel("  Análisis del Árbol");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 10, 8, 10));
        return titulo;
    }

    private JScrollPane crearContenido() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(5, 12, 10, 12));
        // - Pregunta 5 
        List<String> ancestrosDeN = Arrays.asList("A", "E"); 
        agregarBloque(contenido, "5. ¿Qué nodos son los ancestros propios de N?",
                "Ancestros propios de N: " + ancestrosDeN);
        // - Pregunta 6
         List<String> descendientesDeM = Arrays.asList("N", "O");
        agregarBloque(contenido, "6. ¿Qué nodos son los descendientes propios de M?",
                "Descendientes propios de M: " + descendientesDeM);
        // - Pregunta 7 
         List<String> hojas = Arrays.asList("H", "I", "J", "K", "N", "O", "P", "Q"); 
         agregarBloque(contenido, "7. ¿Qué nodos son las hojas?","Nodos hoja: " + hojas);
        // - Pregunta 8
        int alturaC = 4;
        agregarBloque(contenido, "8.¿Cuál es la altura del nodo C?","Altura del nodo C: " + alturaC);
        
     // ── Pregunta 9 ───────────────────────────────
        int altura = analyzer.alturaArbol();
        agregarBloque(contenido,
                "9. ¿Cuál es la altura del árbol?",
                "La altura del árbol es: " + altura);

        // ── Pregunta 10 ──────────────────────────────
        int profC = analyzer.profundidadNodo('C');
        agregarBloque(contenido,
                "10. ¿Cuál es la profundidad del nodo C?",
                "La profundidad de C es: " + profC);

        // ── Pregunta 11 ──────────────────────────────
        Character hermanoDdeD = analyzer.hermanoDerecha('D');
        agregarBloque(contenido,
                "11. ¿Cuál es el hermano a la derecha de D?",
                hermanoDdeD != null ? "El hermano derecho de D es: " + hermanoDdeD
                                    : "D no tiene hermano a la derecha");

        // ── Pregunta 12 ──────────────────────────────
        boolean iEsHermanoDerF = analyzer.esHermanoDerecha('I', 'F');
        agregarBloque(contenido,
                "12. ¿Es I hermano a la derecha de F?",
                iEsHermanoDerF ? "✓ Sí, I es hermano a la derecha de F"
                               : "✗ No, I no es hermano a la derecha de F");
        // ── Pregunta 13 ──────────────────────────────
        boolean fIzqJ = analyzer.estaALaIzquierda('F', 'J');
        agregarBloque(contenido,
                "13. ¿Está F a la izquierda de J?",
                fIzqJ ? "✓ Sí, F está a la izquierda de J"
                       : "✗ No, F no está a la izquierda de J");

        // ── Pregunta 14 ──────────────────────────────
        boolean lDerJ = analyzer.estaALaDerecha('L', 'J');
        agregarBloque(contenido,
                "14. ¿Está L a la derecha de J?",
                lDerJ ? "✓ Sí, L está a la derecha de J"
                       : "✗ No, L no está a la derecha de J");

        // ── Pregunta 15 ──────────────────────────────
        List<Character> izqJ = analyzer.valoresALaIzquierdaDe('J');
        List<Character> derJ = analyzer.valoresALaDerechaDe('J');
        agregarBloque(contenido,
                "15. Nodos a los lados de J:",
                "Izquierda: " + listaAString(izqJ),
                "Derecha: "   + listaAString(derJ));

        // ── Pregunta 16 ──────────────────────────────
        int hijosA            = analyzer.contarHijosDirectos('A');
        List<Character> hijos = analyzer.hijosDirectos('A');
        agregarBloque(contenido,
                "16. ¿Cuántos hijos tiene A?",
                hijosA + " hijos: " + listaAString(hijos));

        // ── Pregunta 17 ──────────────────────────────
        agregarBloque(contenido,
                "17. Recorridos del árbol:",
                "Preorden:  "  + listaAString(analyzer.preorden()),
                "Postorden: "  + listaAString(analyzer.postorden()),
                "Inorden:   "  + listaAString(analyzer.inorden()));

        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        return scroll;
    }

    // ── Agrega un bloque con pregunta y una o más respuestas ──
    private void agregarBloque(JPanel panel, String pregunta, String... respuestas) {
        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(8));

        // Pregunta en negrita
        JLabel lblPregunta = new JLabel("<html><b>" + pregunta + "</b></html>");
        lblPregunta.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPregunta.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblPregunta);
        panel.add(Box.createVerticalStrut(4));

        // Respuestas
        for (String respuesta : respuestas) {
            JLabel lblResp = new JLabel("<html>" + respuesta + "</html>");
            lblResp.setFont(new Font("Arial", Font.PLAIN, 12));
            lblResp.setForeground(new Color(50, 50, 50));
            lblResp.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lblResp);
            panel.add(Box.createVerticalStrut(2));
        }

        panel.add(Box.createVerticalStrut(6));
    }

    private String listaAString(List<Character> lista) {
        if (lista == null || lista.isEmpty()) return "ninguno";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i));
            if (i < lista.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}