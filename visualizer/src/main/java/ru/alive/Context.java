package ru.alive;

import com.mongodb.Mongo;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.alive.env.Environment;
import ru.alive.env.visual.ControlPanel;
import ru.alive.env.visual.CreatureVisualisationHolder;
import ru.alive.env.visual.EnvironmentVisualisation;
import ru.alive.env.visual.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @author dart
 * @since 11/16/12 4:30 AM
 */
@Configuration
public class Context {

    @Value("${mongo.host}")
    private String mongoHost;
    @Value("${mongo.databaseName}")
    private String mongoDatabaseName;
    @Value("${mongo.port}")
    private int mongoPort;



    //---------------------------
    // Visualisation


    @Bean
    public Window window(EnvironmentVisualisation environmentVisualisation, ControlPanel controlPanel) {
        return new Window(environmentVisualisation, controlPanel);
    }

    @Bean
    public EnvironmentVisualisation environmentVisualisation(Environment environment, CreatureVisualisationHolder creatureVisualisationHolder) {
        return new EnvironmentVisualisation(environment, creatureVisualisationHolder);
    }



    //---------------------------
    // Mongo


    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new Mongo(mongoHost, mongoPort);
    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, mongoDatabaseName);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public MongoExceptionTranslator mongoExceptionTranslator() {
        return new MongoExceptionTranslator();
    }






    //----------
    // Utils

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("app.properties"));
        return configurer;
    }
}
