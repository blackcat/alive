package ru.alive.env;

/**
 * @author dart
 * @since 11/14/12 6:20 AM
 */
public class Impact {
    public volatile float worthModifier;
    public volatile int movementEffortX;
    public volatile int movementEffortY;

    public void copyData(Impact toImpact) {
        toImpact.worthModifier = this.worthModifier;
        toImpact.movementEffortX = this.movementEffortX;
        toImpact.movementEffortY = this.movementEffortY;
    }
}
