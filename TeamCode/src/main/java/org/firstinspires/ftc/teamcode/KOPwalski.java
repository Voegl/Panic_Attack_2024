package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.kowalski.control.ControlUtils;
import org.firstinspires.ftc.teamcode.kowalski.hardware.GearRatio;
import org.firstinspires.ftc.teamcode.kowalski.hardware.PhysicalGearedMotor;
import org.firstinspires.ftc.teamcode.kowalski.systems.MecanumDrive;
import org.firstinspires.ftc.teamcode.kowalski.systems.MecanumMotor;

@TeleOp(name = "kOPwalski")
public class KOPwalski extends LinearOpMode {
    private MecanumDrive drivetrain;
    private PhysicalGearedMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        // Create a PhysicalGearedMotor with a gear ratio of 10:1 with a default ticks per revolution count
        motor = new PhysicalGearedMotor(hardwareMap.get(DcMotor.class, "test2"), GearRatio.of(10, 1), PhysicalGearedMotor.TicksPerRevolution.RevHDHexMotor);

        // Create a Mecanum drive and set the signs
        drivetrain = new MecanumDrive(
            hardwareMap,
            "linksvoor", "rechtsvoor",
            "linksachter", "rechtsachter"
        );
        drivetrain
            .setSigns(MecanumMotor.FrontLeft, "+", "+", "+")
            .setSigns(MecanumMotor.FrontRight, "-", "+", "-")
            .setSigns(MecanumMotor.BackLeft, "-", "+", "+")
            .setSigns(MecanumMotor.BackRight, "+", "+", "-");

        waitForStart();

        // Reset the motor to 0 (not necessary, just to showcase)
        motor.resetEncoder();

        while (opModeIsActive()) {
            // You can now do this
            if (gamepad1.a)
                motor.rotateTo(180);
            if (gamepad1.b)
                motor.rotateTo(10);

            // Or this
            motor.rotateBy(ControlUtils.discreteAxis(gamepad1.left_bumper, gamepad1.right_bumper) * 10);

            // And drive like this
            // Note how you can do .move().apply() or .rotate().apply() too if you need that!
            drivetrain
                .move(gamepad1.left_stick_x, gamepad1.left_stick_y)
                .rotate(ControlUtils.discreteAxis(gamepad1.left_trigger, gamepad1.right_trigger))
                .apply();
        }
    }
}