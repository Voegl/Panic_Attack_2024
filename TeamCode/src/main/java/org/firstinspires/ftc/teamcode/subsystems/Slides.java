package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Slides {
    private final HardwareMap hardwareMap;

    private DcMotor slideMotor;
    private Servo containerServo;

    public Slides(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void init() {
        slideMotor = hardwareMap.get(DcMotor.class, "slidemotor");
        containerServo = hardwareMap.get(Servo.class, "bakje");

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPosition(int position, double power) {
        slideMotor.setPower(power);
        slideMotor.setTargetPosition(position);
    }

    public void setAngle(double rotation) {
        containerServo.setPosition(rotation);
    }
}
