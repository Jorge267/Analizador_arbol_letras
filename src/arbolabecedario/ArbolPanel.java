package arbolabecedario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel que dibuja el árbol: líneas entre nodos y los nodos encima.
 */
public class ArbolPanel extends JPanel {

    private final Nodo         raiz;
    private final NodoRenderer renderer;
    private       Nodo         nodoHover = null;

    public ArbolPanel(Nodo raiz) {
        this.raiz     = raiz;
        this.renderer = new NodoRenderer();

        setBackground(Color.WHITE);
        new LayoutCalculator().calcular(raiz);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Nodo anterior = nodoHover;
                nodoHover = encontrarNodoBajoCursor(raiz, e.getX(), e.getY());
                if (nodoHover != anterior) repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarAristas(g2, raiz);
        dibujarNodos(g2, raiz);
        dibujarTooltip(g2);
    }

    private void dibujarAristas(Graphics2D g2, Nodo nodo) {
        int radio = renderer.getRadio();
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(1.2f));
        for (Nodo hijo : nodo.getHijos()) {
            g2.drawLine(nodo.x, nodo.y + radio, hijo.x, hijo.y - radio);
            dibujarAristas(g2, hijo);
        }
    }

    private void dibujarNodos(Graphics2D g2, Nodo nodo) {
        renderer.dibujar(g2, nodo, nodo == nodoHover);
        for (Nodo hijo : nodo.getHijos()) {
            dibujarNodos(g2, hijo);
        }
    }

    private void dibujarTooltip(Graphics2D g2) {
        if (nodoHover == null) return;
        String texto = "Nodo: " + nodoHover.getValor()
                + " | Hijos: " + nodoHover.getHijos().size()
                + (nodoHover.esHoja() ? " [Hoja]" : "");
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(texto) + 10;
        int tx = nodoHover.x + 24;
        int ty = nodoHover.y - 8;
        if (tx + tw > getWidth() - 5) tx = nodoHover.x - tw - 10;
        g2.setColor(new Color(255, 255, 200));
        g2.fillRoundRect(tx, ty - 14, tw, 20, 6, 6);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(tx, ty - 14, tw, 20, 6, 6);
        g2.setColor(Color.BLACK);
        g2.drawString(texto, tx + 5, ty + 2);
    }

    private Nodo encontrarNodoBajoCursor(Nodo nodo, int mx, int my) {
        int radio = renderer.getRadio();
        if (Math.hypot(mx - nodo.x, my - nodo.y) <= radio + 4) return nodo;
        for (Nodo hijo : nodo.getHijos()) {
            Nodo r = encontrarNodoBajoCursor(hijo, mx, my);
            if (r != null) return r;
        }
        return null;
    }
}