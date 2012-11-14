package ru.alive.env.visual;

import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.exception.VisualisaitonException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pvyazankin
 * @since 12.11.12 18:58
 */
@Service
public class CreatureVisualisationHolder {
    private Map<Class<Creature>, CreatureVisualisation> cache = new HashMap<Class<Creature>, CreatureVisualisation>();

    public CreatureVisualisationHolder() {
        cache.put(Creature.class, new CreatureVisualisation());
    }

    public CreatureVisualisation getCreatureVisualisation(Creature creature) {
        if (!cache.containsKey(creature.getClass())) {
            throw new VisualisaitonException("Creature visualiser wasn't found");
        }
        return cache.get(creature.getClass());
    }
}
