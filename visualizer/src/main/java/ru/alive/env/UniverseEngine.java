package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 08.11.12 10:03
 */
@Service
public class UniverseEngine extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(UniverseEngine.class);

    public UniverseEngine() {
        super("Universe engine");
    }

    @Override
    public synchronized void iteration() throws InterruptedException{
        sleep(5*1000);
        log.info("Universe iteration");
        notifyAll();
    }
}
