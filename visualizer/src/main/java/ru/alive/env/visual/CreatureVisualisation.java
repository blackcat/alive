package ru.alive.env.visual;

import ru.alive.env.Creature;

import java.awt.*;

/**
 * @author pvyazankin
 * @since 09.11.12 13:54
 */
public class CreatureVisualisation {

    private Stroke stroke = new BasicStroke(1);
    private int size;

    public CreatureVisualisation() {
    }

    public void paintMyself(Graphics2D g2d, Dimension d) {
        g2d.setColor(Color.white);
        g2d.setStroke(stroke);
        g2d.drawRect((int) d.getWidth() - size / 2, (int) d.getHeight() - size / 2, size, size);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
