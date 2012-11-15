package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.brain.Brain;
import ru.alive.brain.RandomBrain;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 09.11.12 13:51
 */
public class Creature extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);
    public static final int SIZE = 2;
    private static int counter = 0;

    private Impact impactOfEnv = new Impact();
    private Impact impactToEnv = new Impact();

    private UniverseEngine engine;
    private Brain brain;

    public Creature(UniverseEngine engine, Brain brain) {
        super("Simple creature #" + ++counter);
        this.engine = engine;
        this.brain = brain;
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        log.trace("Creature acts");

        brain.getImpactTo(impactOfEnv, impactToEnv);
    }

    public Impact getImpactOfEnv() {
        return impactOfEnv;
    }

    public Impact getImpactToEnv() {
        return impactToEnv;
    }

    @Override
    public String toString() {
        return getName();
    }
}
