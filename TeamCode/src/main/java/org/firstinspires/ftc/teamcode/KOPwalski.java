package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.GuesstimatesProvider;
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
        // Controller 1
        // - Driving
        drivetrain.setDriveSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger);

        // - Container
        if (gamepad1.a) // Drop sample from container
            slides.setAngle(GuesstimatesProvider.CONTAINER_DROP_ROTATION);
        else if (gamepad1.b) // Return container to holding position
            slides.setAngle(GuesstimatesProvider.CONTAINER_HOLD_ROTATION);

        // Controller 2
        // - Slides
        if (gamepad2.dpad_down) // Initial position
            slides.setPosition(0, GuesstimatesProvider.SLIDES_SPEED);
        else if (gamepad2.dpad_left) // Lower basket
            slides.setPosition(GuesstimatesProvider.LOWER_BASKET_POSITION, GuesstimatesProvider.SLIDES_SPEED);
        else if (gamepad2.dpad_up) // Upper basket
            slides.setPosition(GuesstimatesProvider.UPPER_BASKET_POSITION, GuesstimatesProvider.SLIDES_SPEED);

        // - Arm
        // - - Movement
        if (gamepad2.x) // Arm to container
            arm.smartyPantsSetPosition(
                GuesstimatesProvider.ARM_CONTAINER_POSITION,
                GuesstimatesProvider.ARM_SPEED,
                GuesstimatesProvider.ANGLER_SERVO_CONTAINER_POSITION
            );
        else if (gamepad2.y) // Arm to gripping position
            arm.smartyPantsSetPosition(
                GuesstimatesProvider.ARM_GRIPPING_POSITION,
                GuesstimatesProvider.ARM_SPEED,
                GuesstimatesProvider.ANGLER_SERVO_GRIPPING_POSITION
            );

        // Manual control
        double manualDirection = gamepad2.left_trigger - gamepad2.right_trigger; // This might need inversion
        if (manualDirection != 0) // This shouldn't always run, that would screw with the automatic positioning
            arm.rotateArm(manualDirection * GuesstimatesProvider.ARM_SPEED);

        // - - Rotation
        // for now automatic, see arm.smartyPantsSetPosition

        // - - Gripping
        if (gamepad2.a) // Open gripper
            arm.setGripperAngle(GuesstimatesProvider.GRIPPER_SERVO_OPEN_ROTATION);
        else if (gamepad2.b) // Close gripper
            arm.setGripperAngle(GuesstimatesProvider.GRIPPER_SERVO_CLOSE_ROTATION);

    }
}