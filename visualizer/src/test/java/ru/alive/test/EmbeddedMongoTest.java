package ru.alive.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author dart
 * @since 11/14/12 6:17 PM
 */
public class EmbeddedMongoTest {
    private static final String DATABASE_NAME = "embedded";

    private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    private Mongo _mongo;

    @Before
    public void before() throws Exception {

        MongodStarter runtime = MongodStarter.getDefaultInstance();
        _mongodExe = runtime.prepare(new MongodConfig(Version.Main.V2_0, 27017, Network.localhostIsIPv6()));
        _mongod = _mongodExe.start();

        _mongo = new Mongo("localhost", 27017);
    }

    @After
    public void after() throws Exception {
        _mongod.stop();
        _mongodExe.cleanup();
    }

    public Mongo getMongo() {
        return _mongo;
    }

//    @Test
//    public void shouldCreateNewObjectInEmbeddedMongoDb() {
//        // given
//        DB db = getMongo().getDB(DATABASE_NAME);
//        DBCollection col = db.createCollection("testCollection", new BasicDBObject());
//
//        // when
//        col.save(new BasicDBObject("testDoc", new Date()));
//
//        // then
//        Assert.assertTrue(col.getCount() == 1);
//    }
}
