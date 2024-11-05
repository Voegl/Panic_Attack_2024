package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.Arm;
import org.firstinspires.ftc.teamcode.components.SamplePositionalData;
import org.firstinspires.ftc.teamcode.components.Slides;

@Autonomous(name="Autonomous System Crasher")
public class AutonomousOpMode extends LinearOpMode {
    MecanumDrive mecanumDrive;
    Pose2d mecanumDriveInitialPose = new Pose2d(
            new Vector2d(0, 0), // Positie in (TODO: welke eenheid heeft dit / moet dit hebben)
            Rotation2d.fromDouble(0) // Hoek in graden
    );

    Arm arm;
    Slides slides;
    SamplePositionalData samplePositionalData;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialization
        this.samplePositionalData = new SamplePositionalData();

        this.mecanumDrive = new MecanumDrive(hardwareMap, this.mecanumDriveInitialPose);

        this.arm = new Arm(hardwareMap);
        this.arm.init();

        this.slides = new Slides(hardwareMap);
        this.slides.init();

        waitForStart();

        // Start

        while (opModeIsActive()) {
            // While active
        }
    }
}
