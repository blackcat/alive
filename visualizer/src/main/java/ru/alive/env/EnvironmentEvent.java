package ru.alive.env;

import org.springframework.context.ApplicationEvent;

/**
 * @author dart
 * @since 11/14/12 10:44 PM
 */
public class EnvironmentEvent extends ApplicationEvent {
    public enum TYPE {
        renew
    }

    public EnvironmentEvent(Object source, TYPE type) {
        super(source);
    }
}
