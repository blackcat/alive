package ru.alive.brain;

import org.springframework.stereotype.Service;
import ru.alive.env.Impact;

import java.util.Random;

/**
 * @author dart
 * @since 11/14/12 4:41 PM
 */
@Service
public class RandomBrain implements Brain {

    private Random random = new Random();

    @Override
    public void getImpactTo(Impact impactOfEnv, Impact impactToEnv) {
        impactToEnv.movementEffortX = random.nextInt(26) - 13;
        impactToEnv.movementEffortY = -10;
    }
}
