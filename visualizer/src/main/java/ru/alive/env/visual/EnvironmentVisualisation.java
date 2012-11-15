package ru.alive.env.visual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.env.Environment;
import ru.alive.env.EnvironmentEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

import static java.lang.Math.*;

/**
 * @author pvyazankin
 * @since 08.11.12 9:52
 */
public class EnvironmentVisualisation extends JPanel implements ApplicationListener<EnvironmentEvent> {

    private static final Logger log = LoggerFactory.getLogger(EnvironmentVisualisation.class);

    private Environment env;
    @Autowired
    private CreatureVisualisationHolder creatureVisualisationHolder = new CreatureVisualisationHolder();

    private Collection<CreatureVisualisation> creatureVisualisations = new HashSet<CreatureVisualisation>();


    public EnvironmentVisualisation(Environment environment, CreatureVisualisationHolder holder) {
        env = environment;
        setOpaque(true);
        setSize(env.getSize());
        setMinimumSize(env.getSize());
        setMaximumSize(env.getSize());
        setPreferredSize(env.getSize());
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        creatureVisualisationHolder = holder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(10));
        Color color;
        float mod;
        for (int x = 0; x < getWidth() * 2; x++) {
            mod = env.getWorthModifier(x, x);
            g2d.setColor(new Color(mod, 1 - mod, 0.001f));
            int k = round(mod * x);
            g2d.drawArc(0 - k, 0 - k, 2 * k, 2 * k, 0, -180);
        }

        paintCreatures(g2d);

        setBackground(Color.white);
    }

    protected void paintCreatures(Graphics2D g2d) {
        for (Creature c : env.getCreatures()) {
            creatureVisualisationHolder.getCreatureVisualisation(c).paintMyself(g2d, env.getCreaturePosition(c));
        }
    }

    @Override
    public void onApplicationEvent(EnvironmentEvent event) {
        repaint();
    }

    //    public vo
}
