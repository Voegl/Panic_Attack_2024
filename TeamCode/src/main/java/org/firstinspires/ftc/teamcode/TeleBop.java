package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.kowalski.control.ControlUtils;
import org.firstinspires.ftc.teamcode.kowalski.control.FieldCentric;
import org.firstinspires.ftc.teamcode.kowalski.hardware.GearRatio;
import org.firstinspires.ftc.teamcode.kowalski.hardware.PhysicalGearedMotor;
import org.firstinspires.ftc.teamcode.kowalski.hardware.PhysicalServo;
import org.firstinspires.ftc.teamcode.kowalski.systems.MecanumDrive;
import org.firstinspires.ftc.teamcode.kowalski.systems.MecanumMotor;

@TeleOp(name = "telebOP")
public class TeleBop extends OpMode {
    MecanumDrive mecanumDrive;
    FieldCentric fieldCentric;

    PhysicalGearedMotor arm;
    PhysicalGearedMotor slides;

    PhysicalServo containerServo;
    PhysicalServo handServo;
    PhysicalServo handRotServo;

    @Override
    public void init() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParams = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));
        imu.initialize(imuParams);
        fieldCentric = new FieldCentric(imu);

        mecanumDrive = new MecanumDrive(
                hardwareMap,
                "linksvoor", "rechtsvoor",
                "linksachter", "rechtsachter"
        );
        mecanumDrive
                .setSigns(MecanumMotor.FrontLeft, "+", "-", "-")
                .setSigns(MecanumMotor.FrontRight, "-", "-", "+")
                .setSigns(MecanumMotor.BackLeft, "+", "+", "+")
                .setSigns(MecanumMotor.BackRight, "+", "-", "+");

        arm = new PhysicalGearedMotor(
                hardwareMap.get(DcMotor.class, "armmotor"),
                GearRatio.of(184, 1),
                PhysicalGearedMotor.TicksPerRevolution.RevHDHexMotor
        );
        slides = new PhysicalGearedMotor(
                hardwareMap.get(DcMotor.class, "slidemotor"),
                GearRatio.of(20, 1),
                PhysicalGearedMotor.TicksPerRevolution.RevHDHexMotor
        );
        slides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slides.initDistanceMode(0.0003); // 1/20 cm per degree
        slides.setInvertedMode(true);

        handServo = new PhysicalServo(hardwareMap.get(Servo.class, "grijpservo"));
        handServo.setRotation(0);

        handRotServo = new PhysicalServo(hardwareMap.get(Servo.class, "hoekservo"));
        handRotServo.setRotation(0);

        containerServo = new PhysicalServo(hardwareMap.get(Servo.class, "bakje"));
        containerServo.setRotation(0);
    }

    @Override
    public void loop() {
        // Driving [gamepad 1]
        mecanumDrive
                .move(fieldCentric.rotate(gamepad1.left_stick_x, gamepad1.left_stick_y))
                .rotate(ControlUtils.discreteAxis(gamepad1.right_trigger, gamepad1.left_trigger))
                .apply();

        // Arm rotation [gamepad 1]
        if (gamepad1.a) { // to container
            arm.rotateTo(50);
            handRotServo.setRotation(270);
        } else if (gamepad1.b) { // to field
            arm.rotateTo(0);
            handRotServo.setRotation(108);
        }

        arm.setPower(ControlUtils.discreteAxis(gamepad1.right_bumper, gamepad1.left_bumper));

        // Gripper rotation [gamepad 1]
        if (gamepad1.x) // grip
            handServo.setRotation(162);
        else if (gamepad1.y) // release
            handServo.setRotation(108);

        // Slides [gamepad 1]
        if (gamepad1.dpad_up) // up
            slides.moveTo(1);
        else if (gamepad1.dpad_down) // down
            slides.moveTo(0);

        // Bakje [gamepad 1]
        if (gamepad1.dpad_left) // drop
            containerServo.setRotation(180);
        else if (gamepad1.dpad_right) // hold
            containerServo.setRotation(0);
    }
}
