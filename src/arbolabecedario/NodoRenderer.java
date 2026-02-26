package arbolabecedario;

import java.awt.*;

/**
 * Dibuja cada nodo del árbol de forma simple:
 * círculo blanco con borde negro y la letra adentro.
 * Si el mouse está encima, el fondo es amarillo.
 */
public class NodoRenderer {

    private static final int RADIO = 18;

    public void dibujar(Graphics2D g2, Nodo nodo, boolean esHover) {
        // Fondo del círculo
        g2.setColor(esHover ? Color.YELLOW : Color.WHITE);
        g2.fillOval(nodo.x - RADIO, nodo.y - RADIO, RADIO * 2, RADIO * 2);

        // Borde del círculo
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(esHover ? 2f : 1.5f));
        g2.drawOval(nodo.x - RADIO, nodo.y - RADIO, RADIO * 2, RADIO * 2);

        // Letra
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        String letra = String.valueOf(nodo.getValor());
        g2.drawString(letra,
                nodo.x - fm.stringWidth(letra) / 2,
                nodo.y + fm.getAscent() / 2 - 1);
    }

    public int getRadio() {
        return RADIO;
    }
}