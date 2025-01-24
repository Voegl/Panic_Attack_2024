package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Slides;

@TeleOp(name = "kOPwalski")
public class KOPwalski extends OpMode {
     private Drivetrain drivetrain;
     private Arm arm;
     private Slides slides;

    @Override
    public void init() {
        this.drivetrain = new Drivetrain(hardwareMap);
        this.arm = new Arm(hardwareMap);
        this.slides = new Slides(hardwareMap);

        this.drivetrain.init();
        this.arm.init();
        this.slides.init();
    }

    @Override
    public void loop() {
        this.drivetrain.setDriveSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger);
    }
}