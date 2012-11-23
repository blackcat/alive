package ru.alive.brain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.env.Impact;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author dart
 * @since 11/14/12 4:41 PM
 */
@Service("randomBrain")
public class RandomBrain implements Brain {

    private Random random = new Random(System.currentTimeMillis());
    @Value("${brain.effort}")
    private int effort;
    private static int EFFORT;

    @PostConstruct
    public void init() {
        EFFORT = effort;
    }

    @Override
    public void getImpactTo(Creature creature, Impact impactOfEnv, Impact impactToEnv) {
        impactToEnv.movementEffortX = random.nextInt(EFFORT + 1) - EFFORT / 2;
        impactToEnv.movementEffortY = random.nextInt(EFFORT + 1) - EFFORT / 2;
    }

    @Override
    public String dump(Creature creature) {
        return "No dump available for " + this.getClass().getName();
    }
}
