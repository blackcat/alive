package ru.alive.env.visual;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.exception.VisualisaitonException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pvyazankin
 * @since 12.11.12 18:58
 */
@Service
public class CreatureVisualisationHolder {
    private Map<Class<Creature>, CreatureVisualisation> cache = new HashMap<Class<Creature>, CreatureVisualisation>();

    @Value("${creatureVisualisation.size}")
    private int size;

    public CreatureVisualisationHolder() {
        cache.put(Creature.class, new CreatureVisualisation());
    }

    @PostConstruct
    public void init() {
        for (Map.Entry<Class<Creature>, CreatureVisualisation> entry : cache.entrySet()) {
            entry.getValue().setSize(size);
        }
    }

    public CreatureVisualisation getCreatureVisualisation(Creature creature) {
        if (!cache.containsKey(creature.)) {
            throw new VisualisaitonException("Creature visualiser wasn't found");
        }
        return cache.get(creature.getClass());
    }
}
