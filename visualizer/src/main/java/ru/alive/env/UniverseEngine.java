package ru.alive.env;

/**
 * @author pvyazankin
 * @since 08.11.12 10:03
 */
public class UniverseEngine extends Thread {

    protected static final UniverseEngine SINGLETON = new UniverseEngine("Universe Engine");



    protected static UniverseEngine getInstance() {
        return SINGLETON;
    }

    private UniverseEngine() {
    }

    private UniverseEngine(Runnable target) {
        super(target);
    }

    private UniverseEngine(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    private UniverseEngine(String name) {
        super(name);
    }

    private UniverseEngine(ThreadGroup group, String name) {
        super(group, name);
    }

    private UniverseEngine(Runnable target, String name) {
        super(target, name);
    }

    private UniverseEngine(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    private UniverseEngine(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    @Override
    public void run() {
        throw new RuntimeException("not implemented yet");
    }
}
