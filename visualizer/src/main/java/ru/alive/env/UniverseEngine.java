package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 08.11.12 10:03
 */
public class UniverseEngine extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(UniverseEngine.class);

    private static UniverseEngine SINGLETON;

    public static UniverseEngine getInstance() {
        return (UniverseEngine) SINGLETON;
    }

    static  {
        SINGLETON = new UniverseEngine("Universe Engine");
    }

    private UniverseEngine(String name) {
        super(name);
    }

    @Override
    public synchronized void iteration() throws InterruptedException{
        sleep(5*1000);
        log.info("Universe iteration");
        notifyAll();
    }
}
