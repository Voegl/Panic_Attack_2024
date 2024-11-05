package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    HardwareMap hardwareMap;

    public Slides(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    void init() {
    }

    void raise(double offset) {
        if (offset < 0) {
            throw new ArithmeticException("Wil je de robot kapotmaken of zo?");
        }
    }

    boolean finishedRaising() {
        return false;
    }
}
