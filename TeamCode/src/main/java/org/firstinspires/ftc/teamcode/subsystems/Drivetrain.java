package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private DcMotor leftBack;  private DcMotor leftFront;
    private DcMotor rightBack; private DcMotor rightFront;

    private boolean stealth = true;
    private boolean kowalskiIsAnalysing = true;

    private HardwareMap hardwareMap;

    /*
    y
    |
    ^
    |
    +-------x

    y is front of robot
     */

    public Drivetrain(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void init() {
        leftBack = hardwareMap.get(DcMotor.class, "linksachter");
        leftFront = hardwareMap.get(DcMotor.class, "linksvoor");
        rightBack = hardwareMap.get(DcMotor.class, "rechtsachter");
        rightFront = hardwareMap.get(DcMotor.class, "rechtsvoor");
    }

    public void setDriveSpeeds(double x, double y, double turnRate) {
        rightBack.setPower(x + y - turnRate);
        leftBack.setPower(-x + y + turnRate);
        rightFront.setPower(-x + y - turnRate);
        leftFront.setPower(x + y + turnRate);
    }
}
