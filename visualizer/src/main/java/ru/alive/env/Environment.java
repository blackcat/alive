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

    private int usize = 300;
    private Dimension size = new Dimension(usize, usize);
    private int resistanceLimit = 5;
    private Map<Creature, Dimension> creatures = new HashMap<Creature, Dimension>();
    private final Random random = new Random(usize - Creature.SIZE);

    protected Environment() {
        super("2D environment");
    }

    public void init() {
        addSomeRandomCreatures();
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        log.trace("Environment iteration");

        int movx, movy;
        for (Map.Entry<Creature, Dimension> entry : creatures.entrySet()) {
            movx = entry.getKey().getImpactToEnv().movementEffortX;
            movy = entry.getKey().getImpactToEnv().movementEffortY;
            // read impacts from creatures
            entry.getValue().setSize(
                    Math.abs(movx) > resistanceLimit && entry.getValue().getWidth() + movx >= 0 && entry.getValue().getWidth() + movx < usize ? resistanceLimit : movx,
                    Math.abs(movy) > resistanceLimit && entry.getValue().getHeight() + movy >= 0 && entry.getValue().getHeight() + movy < usize ? resistanceLimit : movy
            );

            // write impacts to creatures
            entry.getKey().getImpactOfEnv().worthModifier = getWorthModifier(entry.getValue());
        }
        log.debug("Environment iteration finished");
    }

    public float getWorthModifier(Dimension d) {
        float m = (float) ((d.getWidth() + d.getHeight()) / (size.getWidth() + size.getHeight()));
        return m > 1 ? 1 : m;
    }

    public float getWorthModifier(int x, int y) {
        return getWorthModifier(new Dimension(x, y));
    }

    public Dimension getSize() {
        return size;
    }

    public void addCreature(Creature creature) {
        Dimension d = new Dimension(
                random.nextInt(size.width),
                random.nextInt(size.height)
        );
        creatures.put(creature, d);
        creature.getImpactOfEnv().worthModifier = getWorthModifier(d);
    }

    public boolean addCreatures(Collection<Creature> creatures) {
        for (Creature creature : creatures) {
            this.addCreature(creature);
        }
        return true;
    }

    public boolean removeCreature(Creature creature) {
        creature.stop(true);
        return creatures.remove(creature) != null;
    }

    public void clearCreatures() {
        for (Creature creature : creatures.keySet()) {
            creature.stop(true);
        }
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
                new Creature(engine),
                new Creature(engine),
                new Creature(engine),
                new Creature(engine)
        };
        this.addCreatures(Arrays.asList(creatures));
    }

    public Dimension getCreaturePosition(Creature c) {
        return creatures.get(c);
    }
}
