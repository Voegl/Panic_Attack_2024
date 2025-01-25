package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private final HardwareMap hardwareMap;

    public DcMotor armMotor;
    private Servo angleServo;
    private Servo gripServo;

    public Arm(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "armmotor");
        angleServo = hardwareMap.get(Servo.class, "hoekservo");
        gripServo = hardwareMap.get(Servo.class, "grijpservo");

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setGripperAngle(double rotation) {
        gripServo.setPosition(rotation);
    }

    public void setAnglerAngle(double rotation) {
        angleServo.setPosition(rotation);
    }

    public void rotateArm(double power) {
        armMotor.setPower(power);
    }

    public void smartyPantsSetPosition(int position, double power, double servoPosition) {
//        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setPower(power);
        armMotor.setTargetPosition(position);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setAnglerAngle(servoPosition);
    }
}
