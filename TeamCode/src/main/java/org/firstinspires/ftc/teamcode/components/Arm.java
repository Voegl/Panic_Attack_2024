package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    HardwareMap hardwareMap;

    public Arm(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    void init() {}

    void turn(ArmPart armPart, double degrees) {
        if (armPart == ArmPart.ALL) {
            throw new RuntimeException("Waarom zou je in godsnaam alle onderdelen tegelijk draaien?");
        }
    }

    boolean hasFinishedTurning(ArmPart armPart) {
        return false;
    }
}
