package ru.alive.env.visual;

import ru.alive.env.Creature;

import java.awt.*;

/**
 * @author pvyazankin
 * @since 09.11.12 13:54
 */
public class CreatureVisualisation {

    private EnvironmentVisualisation envVis;
    private Creature creature;

    public CreatureVisualisation(EnvironmentVisualisation environmentVisualisation, Creature creature) {
        envVis = environmentVisualisation;
        this.creature = creature;
    }

    public void paintMyself(Graphics2D g2d, Dimension d) {
        g2d.setColor(Color.white);
        g2d.drawRect((int)d.getWidth(), (int)d.getHeight(), 2, 2);
    }
}
