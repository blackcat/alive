package ru.alive.brain;

import ru.alive.env.Creature;
import ru.alive.env.Impact;

public interface Brain {
    public void getImpactTo(Creature creature, Impact impactOfEnv, Impact impactToEnv);
    public String dump(Creature creature);
}