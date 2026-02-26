package arbolabecedario;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación.
 * Árbol a la izquierda, panel de análisis a la derecha.
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Árbol General");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 600);
        setLocationRelativeTo(null);

        ArbolGeneral  arbol    = new ArbolGeneral();
        ArbolAnalyzer analyzer = new ArbolAnalyzer(arbol.getRaiz());

        JPanel contenedor = new JPanel(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Árbol General", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 8, 0));
        contenedor.add(titulo, BorderLayout.NORTH);

        // Árbol
        contenedor.add(new ArbolPanel(arbol.getRaiz()), BorderLayout.CENTER);

        // Panel lateral
        contenedor.add(new PanelAnalisis(analyzer), BorderLayout.EAST);

        add(contenedor);
        setVisible(true);
    }
}