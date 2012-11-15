package ru.alive.brain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alive.env.Impact;

import java.security.spec.ECFieldFp;
import java.util.Random;

/**
 * @author dart
 * @since 11/14/12 4:41 PM
 */
@Service
public class RandomBrain implements Brain {

    private Random random = new Random(System.currentTimeMillis());
    @Value("${brain.effort}")
    private int effort;
    private static int EFFORT;

    public void init() {
        EFFORT = effort;
    }

    @Override
    public void getImpactTo(Impact impactOfEnv, Impact impactToEnv) {
        impactToEnv.movementEffortX = random.nextInt(EFFORT + 1) - EFFORT / 2;
        impactToEnv.movementEffortY = random.nextInt(EFFORT + 1) - EFFORT / 2;
    }
}
