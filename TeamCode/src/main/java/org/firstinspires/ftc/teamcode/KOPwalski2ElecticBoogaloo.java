package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "kOPwalski 2: Electic Boogaloo")
public class KOPwalski2ElecticBoogaloo extends OpMode {
    private Drivetrain drivetrain;

    DcMotor arm;
    DcMotor slides;
    Servo bakje;
    Servo armDraai;
    Servo grijper;

    @Override
    public void init() {
        this.drivetrain = new Drivetrain(hardwareMap);
        arm = hardwareMap.get(DcMotor.class, "armmotor");
        slides = hardwareMap.get(DcMotor.class, "slidemotor");
        bakje = hardwareMap.get(Servo.class, "bakje");
        armDraai = hardwareMap.get(Servo.class, "hoekservo");
        grijper = hardwareMap.get(Servo.class, "grijpservo");

        drivetrain.init();
    }

    @Override
    public void loop() {
        drivetrain.setDriveSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger);

        arm.setPower(gamepad2.left_stick_y * .5);
        slides.setPower(gamepad2.left_trigger - gamepad2.right_trigger);

        if (gamepad1.a){
            bakje.setPosition(0.5);
        }
        if (gamepad1.b){
            bakje.setPosition(0);
        }

        if (gamepad2.a){
            armDraai.setPosition(1);
        }
        if (gamepad2.b){
            armDraai.setPosition(0.4);
        }

        if (gamepad2.x){
            grijper.setPosition(0.6); // dicht
        }
        if (gamepad2.y){
            grijper.setPosition(0.4); // open
        }
    }
}
