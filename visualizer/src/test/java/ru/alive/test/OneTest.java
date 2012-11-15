package ru.alive.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alive.brain.Edge;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author dart
 * @since 11/14/12 4:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class OneTest extends EmbeddedMongoTest{

    private static final Logger log = LoggerFactory.getLogger(OneTest.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void test() throws UnknownHostException {
        log.info(InetAddress.getLocalHost().getCanonicalHostName());
    }

    @Test
    public void mongoTest() throws Exception {
        if (mongoOperations.collectionExists(Edge.class)) {
            mongoOperations.dropCollection(Edge.class);
        }

        mongoOperations.createCollection(Edge.class);

        Edge edge = new Edge();
        edge.setImpactOf0(1);
        edge.setImpactOf1(2);
        edge.setImpactTo(3);

        mongoOperations.insert(edge);

        List<Edge> results = mongoOperations.findAll(Edge.class);
        Assert.assertFalse(results.isEmpty());

        results = mongoOperations.find(new Query(new Criteria("impactOf0").all(new Integer(1))), Edge.class);
        Assert.assertEquals(edge, results.get(0));
    }
}
