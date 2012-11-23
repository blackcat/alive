package ru.alive;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import ru.alive.env.Creature;
import ru.alive.env.Environment;
import ru.alive.env.UniverseEngine;
import ru.alive.env.visual.EnvironmentVisualisation;
import ru.alive.env.visual.Window;

import java.util.Arrays;

/**
 * @author pvyazankin
 * @since 07.11.12 19:16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ConfigurationException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        // config
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("app.properties"));

        // context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.alive");

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Creature.class);
        for (int i = 0; i < config.getInt("creatures.quantity"); i++) {
            BeanDefinition creatureBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
            context.registerBeanDefinition("creature" + i, creatureBeanDefinition);
        }
        context.refresh();

        // universe start
        UniverseEngine universeEngine = context.getBean(UniverseEngine.class);
        universeEngine.start();
    }
}
