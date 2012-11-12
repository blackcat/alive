package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.util.AbstractThread;

import java.awt.*;
import java.util.*;

/**
 * @author pvyazankin
 * @since 08.11.12 9:51
 */
public class Environment extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);

    private static final UniverseEngine ENGINE = UniverseEngine.getInstance();
    private static Environment SINGLETON;

    private Dimension size = new Dimension(300, 300);
    private Map<Creature, Dimension> creatures = new HashMap<Creature, Dimension>();
    private Random random = new Random();

    static {
        SINGLETON = new Environment("2D environment");
    }

    protected Environment(String name) {
        super(name);
    }

    public static Environment getInstance() {
        return (Environment) SINGLETON;
    }

    @Override
    public void iteration() throws InterruptedException {
        // nothing to do by now
        synchronized (ENGINE) {
            wait(); // waiting for notification from engine
        }
    }

    public float getWorthModifier(int x, int y) {
        float m = (float) ((x + y) / (size.getWidth() + size.getHeight()));
        return m > 1 ? 1 : m;
    }

    public Dimension getSize() {
        return size;
    }

    public void addCreature(Creature creature) {
        creatures.put(creature, new Dimension(
                random.nextInt(size.width),
                random.nextInt(size.height)
        ));
    }

    public boolean addCreatures(Collection<Creature> creatures) {
        for (Creature creature : creatures) {
            this.addCreature(creature);
        }
        return true;
    }

    public boolean removeCreature(Creature creature) {
        return creatures.remove(creature) != null;
    }

    public void clearCreatures() {
        creatures.clear();
    }
}
