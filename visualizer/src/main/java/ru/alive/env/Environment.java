package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.alive.brain.Brain;
import ru.alive.util.AbstractThread;

import javax.annotation.PostConstruct;
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
    @Autowired
    private ApplicationContext context;
    @Autowired(required = false)
    private Collection<Creature> creatures;

    private int usize = 300;
    private Dimension size = new Dimension(usize, usize);

    @Value("${environment.resistanceLimit}")
    private int resistanceLimit;

    private Map<Creature, Dimension> creaturesMap = new HashMap<Creature, Dimension>();
    private final Random random = new Random(System.currentTimeMillis());

    protected Environment() {
        super("2D environment");
    }

    @PostConstruct
    public void init() {
        addCreatures(creatures);
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        log.trace("Environment iteration");

        int movx, movy;
        for (Map.Entry<Creature, Dimension> entry : creaturesMap.entrySet()) {
            movx = entry.getKey().getImpactToEnv().movementEffortX;
            movy = entry.getKey().getImpactToEnv().movementEffortY;

            movx = movx > resistanceLimit ? resistanceLimit : movx;
            movx = movx < -resistanceLimit ? -resistanceLimit : movx;
            movy = movy > resistanceLimit ? resistanceLimit : movy;
            movy = movy < -resistanceLimit ? -resistanceLimit : movy;
            // read impacts from creaturesMap
            entry.getValue().setSize(
                    entry.getValue().getWidth() + (entry.getValue().getWidth() + movx <= 0 || entry.getValue().getWidth() + movx > usize ? 0 : movx),
                    entry.getValue().getHeight() + (entry.getValue().getHeight() + movy <= 0 || entry.getValue().getHeight() + movy > usize ? 0 : movy)
            );
            log.trace(entry.getKey().toString() + " new position is " + entry.getValue());

            // write impacts to creaturesMap
            entry.getKey().getImpactOfEnv().worthModifier = getWorthModifier(entry.getValue());
        }
        context.publishEvent(new EnvironmentEvent(this, EnvironmentEvent.TYPE.renew));
        log.trace("Environment iteration finished");
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

    private void addCreature(Creature creature) {
        Dimension d = new Dimension(
                random.nextInt(size.width),
                random.nextInt(size.height)
        );
        creaturesMap.put(creature, d);
        creature.getImpactOfEnv().worthModifier = getWorthModifier(d);
    }

    private boolean addCreatures(Collection<Creature> creatures) {
        if (creatures == null) {
            return false;
        }
        for (Creature creature : creatures) {
            this.addCreature(creature);
        }
        return true;
    }

    public boolean removeCreature(Creature creature) {
        creature.stop(true);
        return creaturesMap.remove(creature) != null;
    }

    public void clearCreatures() {
        for (Creature creature : creaturesMap.keySet()) {
            creature.stop(true);
        }
        creaturesMap.clear();
    }

    /**
     * Returns a copy of creaturesMap collection. Changes will not be reflected.
     */
    public Set<Creature> getCreaturesMap() {
        return new HashSet<Creature>(creaturesMap.keySet());
    }
//
//    public void addSomeRandomCreatures() {
//        Creature[] creaturesMap = new Creature[]{
//                new Creature(engine, brain),
//                new Creature(engine, brain),
//                new Creature(engine, brain),
//                new Creature(engine, brain)
//        };
//        this.addCreatures(Arrays.asList(creaturesMap));
//    }

    public Dimension getCreaturePosition(Creature c) {
        return creaturesMap.get(c);
    }
}
