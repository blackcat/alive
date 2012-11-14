package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 09.11.12 13:51
 */
public class Creature extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);
    public static final int SIZE = 2;
    private Impact impactOfEnv = new Impact();
    private Impact impactToEnv = new Impact();

    private UniverseEngine engine;

    public Creature(UniverseEngine engine) {
        super("Simple creature");
        this.engine = engine;
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        log.debug("Creature acts");
    }

    public Impact getImpactOfEnv() {
        return impactOfEnv;
    }

    public Impact getImpactToEnv() {
        return impactToEnv;
    }
}
