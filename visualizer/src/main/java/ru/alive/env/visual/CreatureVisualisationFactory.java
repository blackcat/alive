package ru.alive.env.visual;

import ru.alive.env.Creature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pvyazankin
 * @since 12.11.12 18:58
 */
public class CreatureVisualisationFactory {
    private Map<Class<Creature>, CreatureVisualisation> cache = new HashMap<Class<Creature>, CreatureVisualisation>();

    public CreatureVisualisationFactory() {
        cache.put(Creature.class, new CreatureVisualisation());
    }

    public CreatureVisualisation getCreatureVisualisation(Creature creature) {

    }
}
