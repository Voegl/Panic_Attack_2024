package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.Arm;
import org.firstinspires.ftc.teamcode.components.SamplePositionalData;
import org.firstinspires.ftc.teamcode.components.Slides;

@TeleOp(name="Autonomous Test")
public class AutonomousOpMode extends LinearOpMode {
    Arm arm;
    Slides slides;
    SamplePositionalData samplePositionalData;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialization
        this.samplePositionalData = new SamplePositionalData();

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
