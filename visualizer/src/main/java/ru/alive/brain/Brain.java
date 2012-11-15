package ru.alive.brain;

import ru.alive.env.Impact;

public interface Brain {
    public void getImpactTo(Impact impactOfEnv, Impact impactToEnv);
}