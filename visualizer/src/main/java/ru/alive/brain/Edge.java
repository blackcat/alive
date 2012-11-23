package ru.alive.brain;

import ru.alive.env.Creature;
import ru.alive.env.Impact;

/**
 * @author dart
 * @since 11/14/12 5:55 PM
 */
public class Edge {

    private Creature creature;
    private Impact impactOf0;
    private Impact impactOf1;
    private Impact impactTo;

    public Edge() {
    }

    public Edge(Creature creature, Impact impactOf0, Impact impactOf1, Impact impactTo) {
        this.creature = creature;
        this.impactOf0 = impactOf0;
        this.impactOf1 = impactOf1;
        this.impactTo = impactTo;
    }

    public Impact getImpactOf0() {
        return impactOf0;
    }

    public void setImpactOf0(Impact impactOf0) {
        this.impactOf0 = impactOf0;
    }

    public Impact getImpactOf1() {
        return impactOf1;
    }

    public void setImpactOf1(Impact impactOf1) {
        this.impactOf1 = impactOf1;
    }

    public Impact getImpactTo() {
        return impactTo;
    }

    public void setImpactTo(Impact impactTo) {
        this.impactTo = impactTo;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (creature != null ? !creature.equals(edge.creature) : edge.creature != null) return false;
        if (impactOf0 != null ? !impactOf0.equals(edge.impactOf0) : edge.impactOf0 != null) return false;
        if (impactOf1 != null ? !impactOf1.equals(edge.impactOf1) : edge.impactOf1 != null) return false;
        if (impactTo != null ? !impactTo.equals(edge.impactTo) : edge.impactTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = creature != null ? creature.hashCode() : 0;
        result = 31 * result + (impactOf0 != null ? impactOf0.hashCode() : 0);
        result = 31 * result + (impactOf1 != null ? impactOf1.hashCode() : 0);
        result = 31 * result + (impactTo != null ? impactTo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return creature + " Edge{" + impactOf0 + " --> " + impactTo + " --> " + impactOf1 + '}';
    }
}
