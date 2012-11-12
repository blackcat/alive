package ru.alive.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alive.exception.AliveException;

/**
 * @author pvyazankin
 * @since 08.11.12 11:15
 */
public abstract class AbstractThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(AbstractThread.class);

    protected AbstractThread SINGLETON;

    private boolean suspended;
    private boolean stopped;


    private AbstractThread() {
    }

    private AbstractThread(Runnable target) {
        super(target);
    }

    private AbstractThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    protected AbstractThread(String name) {
        super(name);
    }

    private AbstractThread(ThreadGroup group, String name) {
        super(group, name);
    }

    private AbstractThread(Runnable target, String name) {
        super(target, name);
    }

    private AbstractThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    private AbstractThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    @Override
    public final void run() {
        try {
            synchronized (this) {
                while (!suspended) {
                    iteration();
                }
            }
        } catch (InterruptedException e) {
            log.error("Something bad occured..");
            throw new AliveException(e);
        }
    }

    public abstract void iteration() throws InterruptedException;

    public synchronized boolean isSuspended() {
        return suspended;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public synchronized void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
