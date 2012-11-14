package ru.alive;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alive.env.Creature;
import ru.alive.env.Environment;
import ru.alive.env.UniverseEngine;
import ru.alive.env.visual.EnvironmentVisualisation;
import ru.alive.env.visual.Window;

import java.util.Arrays;

/**
 * @author pvyazankin
 * @since 07.11.12 19:16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        UniverseEngine universeEngine = UniverseEngine.getInstance();
//
//        Environment environment = Environment.getInstance();
//        environment.addSomeRandomCreatures();
//
//
//        EnvironmentVisualisation visualisation = new EnvironmentVisualisation(environment);
//        Window window = new Window(visualisation);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UniverseEngine universeEngine = context.getBean(UniverseEngine.class);
        universeEngine.start();
    }
}
