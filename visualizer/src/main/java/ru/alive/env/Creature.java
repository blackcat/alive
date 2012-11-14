package ru.alive.env;

import com.sun.javafx.css.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alive.util.AbstractThread;

import java.awt.*;

/**
 * @author pvyazankin
 * @since 09.11.12 13:51
 */
public class Creature extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);
    public static final int SIZE = 2;

    private UniverseEngine engine;

    public Creature(UniverseEngine engine) {
        super("Simple creature");
        this.engine = engine;
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        // nothing to do by now
//        synchronized (this) {
            log.debug("act!");
//            wait(); // waiting for notification from engine
//        }
    }
}
