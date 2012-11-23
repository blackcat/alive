package ru.alive.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import ru.alive.brain.Brain;
import ru.alive.util.AbstractThread;

/**
 * @author pvyazankin
 * @since 09.11.12 13:51
 */
public class Creature extends AbstractThread implements BeanDefinition {

    private static final Logger log = LoggerFactory.getLogger(Environment.class);

    private AbstractBeanDefinition abstractBeanDefinition = new AbstractBeanDefinition() {
        private String parentName;

        @Override
        public AbstractBeanDefinition cloneBeanDefinition() {
            throw new RuntimeException("not implemented yet");
        }

        @Override
        public String getParentName() {
            return parentName;
        }

        @Override
        public void setParentName(String parentName) {
            this.parentName = parentName;
        }
    };

    public static final int SIZE = 2;
    private static int counter = 0;

    private Impact impactOfEnv = new Impact();
    private Impact impactToEnv = new Impact();

    @Autowired
    private UniverseEngine engine;
    @Autowired @Qualifier("mongoBrain")
    private Brain brain;

    public Creature() {
        super("simpleCreature" + ++counter);
    }

    @Override
    public void iteration() throws InterruptedException {
        sleep(UniverseEngine.TICK);
        log.trace("Creature acts");

        brain.getImpactTo(this, impactOfEnv, impactToEnv);
    }

    public Impact getImpactOfEnv() {
        return impactOfEnv;
    }

    public Impact getImpactToEnv() {
        return impactToEnv;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Brain getBrain() {
        return brain;
    }

    @Override
    public String getParentName() {
        return abstractBeanDefinition.getParentName();
    }

    @Override
    public void setParentName(String parentName) {
        abstractBeanDefinition.setParentName(parentName);
    }

    @Override
    public String getBeanClassName() {
        return abstractBeanDefinition.getBeanClassName();
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        abstractBeanDefinition.setBeanClassName(beanClassName);
    }

    @Override
    public String getFactoryBeanName() {
        return abstractBeanDefinition.getFactoryBeanName();
    }

    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        abstractBeanDefinition.setFactoryBeanName(factoryBeanName);
    }

    @Override
    public String getFactoryMethodName() {
        return abstractBeanDefinition.getFactoryMethodName();
    }

    @Override
    public void setFactoryMethodName(String factoryMethodName) {
        abstractBeanDefinition.setFactoryMethodName(factoryMethodName);
    }

    @Override
    public String getScope() {
        return abstractBeanDefinition.getScope();
    }

    @Override
    public void setScope(String scope) {
        abstractBeanDefinition.setScope(scope);
    }

    @Override
    public boolean isLazyInit() {
        return abstractBeanDefinition.isLazyInit();
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        abstractBeanDefinition.setLazyInit(lazyInit);
    }

    @Override
    public String[] getDependsOn() {
        return abstractBeanDefinition.getDependsOn();
    }

    @Override
    public void setDependsOn(String[] dependsOn) {
        abstractBeanDefinition.setDependsOn(dependsOn);
    }

    @Override
    public boolean isAutowireCandidate() {
        return abstractBeanDefinition.isAutowireCandidate();
    }

    @Override
    public void setAutowireCandidate(boolean autowireCandidate) {
        abstractBeanDefinition.setAutowireCandidate(autowireCandidate);
    }

    @Override
    public boolean isPrimary() {
        return abstractBeanDefinition.isPrimary();
    }

    @Override
    public void setPrimary(boolean primary) {
        abstractBeanDefinition.setPrimary(primary);
    }

    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return abstractBeanDefinition.getConstructorArgumentValues();
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return abstractBeanDefinition.getPropertyValues();
    }

    @Override
    public boolean isSingleton() {
        return abstractBeanDefinition.isSingleton();
    }

    @Override
    public boolean isPrototype() {
        return abstractBeanDefinition.isPrototype();
    }

    @Override
    public boolean isAbstract() {
        return abstractBeanDefinition.isAbstract();
    }

    @Override
    public int getRole() {
        return abstractBeanDefinition.getRole();
    }

    @Override
    public String getDescription() {
        return abstractBeanDefinition.getDescription();
    }

    @Override
    public String getResourceDescription() {
        return abstractBeanDefinition.getResourceDescription();
    }

    @Override
    public BeanDefinition getOriginatingBeanDefinition() {
        return abstractBeanDefinition.getOriginatingBeanDefinition();
    }

    @Override
    public void setAttribute(String name, Object value) {
        abstractBeanDefinition.setAttribute(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return abstractBeanDefinition.getAttribute(name);
    }

    @Override
    public Object removeAttribute(String name) {
        return abstractBeanDefinition.removeAttribute(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        return abstractBeanDefinition.hasAttribute(name);
    }

    @Override
    public String[] attributeNames() {
        return abstractBeanDefinition.attributeNames();
    }

    @Override
    public Object getSource() {
        return abstractBeanDefinition.getSource();
    }
}
