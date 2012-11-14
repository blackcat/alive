package ru.alive.env;

import com.sun.javafx.css.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.util.AbstractThread;

import java.awt.*;

/**
 * @author pvyazankin
 * @since 09.11.12 13:51
 */
public class Creature extends AbstractThread {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);
    public static final int SIZE = 2;

    public Creature() {
        super("Simples creature");
    }

    @Override
    public void iteration() throws InterruptedException {
        log.debug("act!");
    }
}
