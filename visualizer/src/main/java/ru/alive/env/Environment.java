package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alive.util.AbstractThread;

import java.awt.*;
import java.util.*;

/**
 * @author pvyazankin
 * @since 08.11.12 9:51
 */
@Service("environment")
public class Environment extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);

    @Autowired
    private UniverseEngine engine;

    private static final int USIZE = 300;
    private Dimension size = new Dimension(USIZE, USIZE);
    private Map<Creature, Dimension> creatures = new HashMap<Creature, Dimension>();
    private Random random = new Random(USIZE);

    protected Environment() {
        super("2D environment");
    }

    @Override
    public void iteration() throws InterruptedException {
        // nothing to do by now
        synchronized (engine) {
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

    /**
     * Returns a copy of creatures collection. Changes will not be reflected.
     */
    public Set<Creature> getCreatures() {
        return new HashSet<Creature>(creatures.keySet());
    }

    public void addSomeRandomCreatures() {
        Creature[] creatures = new Creature[]{
                new Creature(),
                new Creature(),
                new Creature(),
                new Creature()
        };
        this.addCreatures(Arrays.asList(creatures));
    }
}
