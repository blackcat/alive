package ru.alive.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author dart
 * @since 11/16/12 6:28 AM
 */
public class LevelFilter extends AbstractMatcherFilter<ILoggingEvent> {
    private Set<Level> levels = new HashSet<Level>();

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        if (levels.contains(event.getLevel())) {
            return onMatch;
        } else {
            return onMismatch;
        }
    }

    public void setLevels(String levelsString) {
        StringTokenizer tokenizer = new StringTokenizer(levelsString, ",");
        while (tokenizer.hasMoreTokens()) {
            levels.add(Level.valueOf(tokenizer.nextToken()));
        }
    }

    public void start() {
        if (this.levels != null && !levels.isEmpty()) {
            super.start();
        }
    }
}
