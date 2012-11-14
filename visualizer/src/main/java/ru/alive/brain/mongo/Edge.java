package ru.alive.brain.mongo;

/**
 * @author dart
 * @since 11/14/12 5:55 PM
 */
public class Edge {

    private int impactOf0;
    private int impactOf1;
    private int impactTo;

    public int getImpactOf0() {
        return impactOf0;
    }

    public void setImpactOf0(int impactOf0) {
        this.impactOf0 = impactOf0;
    }

    public int getImpactOf1() {
        return impactOf1;
    }

    public void setImpactOf1(int impactOf1) {
        this.impactOf1 = impactOf1;
    }

    public int getImpactTo() {
        return impactTo;
    }

    public void setImpactTo(int impactTo) {
        this.impactTo = impactTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (impactOf0 != edge.impactOf0) return false;
        if (impactOf1 != edge.impactOf1) return false;
        if (impactTo != edge.impactTo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = impactOf0;
        result = 31 * result + impactOf1;
        result = 31 * result + impactTo;
        return result;
    }
}
