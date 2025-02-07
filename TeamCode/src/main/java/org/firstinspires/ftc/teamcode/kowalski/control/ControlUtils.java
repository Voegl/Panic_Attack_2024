package org.firstinspires.ftc.teamcode.kowalski.control;

public class ControlUtils {
    private static double boolToDouble(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Use two discrete controller inputs as one joystick axis.
     * @param negative The discrete input for the negative direction, range [0, 1].
     * @param positive The discrete input for the positive direction, range [0, 1].
     * @return The joystick-style input, range [-1, 1]
     */
    public static double discreteAxis(double negative, double positive) {
        return positive - negative;
    }
    /**
     * Use two discrete controller inputs as one joystick axis.
     * @param negative The discrete input for the negative direction, range [0, 1].
     * @param positive The discrete input for the positive direction, range [0, 1].
     * @return The joystick-style input, range [-1, 1]
     */
    public static double discreteAxis(boolean negative, boolean positive) {
        return ControlUtils.boolToDouble(positive) - ControlUtils.boolToDouble(negative);
    }
}
