package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 08.11.12 10:03
 */
@Service
public class UniverseEngine extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(UniverseEngine.class);
    @Value("${universeEngine.tick}")
    public int tick = 1000;

    public static int TICK;

    @Autowired
    private Environment env;

    public UniverseEngine() {
        super("Universe engine");
    }

    public void init() {
        TICK = tick;
    }

    @Override
    public synchronized void iteration() throws InterruptedException{
        sleep(5*1000);
        log.debug("Universe iteration");
        notifyAll();
    }

    public void start() {
        env.start();
        for (Creature creature : env.getCreatures()) {
            creature.start();
        }
        super.start();
    }

    public int getTick() {
        return tick;
    }
}
