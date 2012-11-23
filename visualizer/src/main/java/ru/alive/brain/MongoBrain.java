package ru.alive.brain;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.env.Impact;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

/**
 * @author dart
 * @since 11/15/12 4:47 PM
 */
@Service
public class MongoBrain implements Brain {

    private static final Logger log = LoggerFactory.getLogger(MongoBrain.class);

    @Autowired
    private MongoOperations mongoOperations;

    private Random random = new Random(System.currentTimeMillis());

    @Value("${brain.effort}")
    private int effort;
//    private static int EFFORT;

    private Edge curr = new Edge(null, new Impact(), new Impact(), new Impact());
    private Edge prev = new Edge(null, new Impact(), new Impact(), new Impact());


    @PostConstruct
    public synchronized void init() {

        if (mongoOperations.collectionExists(Edge.class)) {
            mongoOperations.dropCollection(Edge.class);
        }
        mongoOperations.createCollection(Edge.class);
        mongoOperations.insert(curr);
    }

    @Override
    public void getImpactTo(Creature creature, Impact impactOfEnv, Impact impactToEnv) {

        impactToEnv.movementEffortX = random.nextInt(effort + 1) - effort / 2;
        impactToEnv.movementEffortY = random.nextInt(effort + 1) - effort / 2;

        // save edge
        prev.setImpactOf1(impactOfEnv);
        prev.setCreature(creature);
        mongoOperations.insert(prev);

        // new edge
        prev = new Edge(creature, impactOfEnv, null, impactToEnv);
    }

    @Override
    public String dump(Creature creature) {
        List<Edge> edges = mongoOperations.findAll(Edge.class);
        Gson gson = new Gson();
        return gson.toJson(edges);
    }
}
