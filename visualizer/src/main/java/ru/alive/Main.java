package ru.alive;

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

    public static void main(String[] args) {
        UniverseEngine universeEngine = UniverseEngine.getInstance();

        Environment environment = Environment.getInstance();
        Creature[] creatures = new Creature[]{
                Creature.getInstance(),
                Creature.getInstance(),
                Creature.getInstance(),
                Creature.getInstance()
        };
        environment.addCreatures(Arrays.asList(creatures));

        EnvironmentVisualisation visualisation = new EnvironmentVisualisation(environment);
        Window window = new Window(visualisation);
    }
}
