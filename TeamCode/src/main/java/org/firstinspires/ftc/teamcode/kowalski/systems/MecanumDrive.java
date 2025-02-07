package org.firstinspires.ftc.teamcode.kowalski.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {
    // x<K> y<K> turnRate<K> are multiplication factors
    private final DcMotor frontLeft;
    private int xFL; private int yFL; private int turnRateFL;

    private final DcMotor frontRight;
    private int xFR; private int yFR; private int turnRateFR;

    private final DcMotor backLeft;
    private int xBL; private int yBL; private int turnRateBL;

    private final DcMotor backRight;
    private int xBR; private int yBR; private int turnRateBR;

    private double xRate = 0, yRate = 0, turnRate = 0;

    public MecanumDrive(
            HardwareMap hwMap,
            String frontLeft, String frontRight,
            String backLeft, String backRight
    ) {
        this.frontLeft = hwMap.get(DcMotor.class, frontLeft);
        this.frontRight = hwMap.get(DcMotor.class, frontRight);
        this.backLeft = hwMap.get(DcMotor.class, backLeft);
        this.backRight = hwMap.get(DcMotor.class, backRight);
    }

    public MecanumDrive(
            DcMotor frontLeft, DcMotor frontRight,
            DcMotor backLeft, DcMotor backRight
    ) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    private int stringToSign(String str) {
        if (str.equalsIgnoreCase("+"))
            return 1;
        else if (str.equalsIgnoreCase("-"))
            return -1;
        else
            throw new IllegalArgumentException("Sign must be either '+' or '-'!");
    }

    public MecanumDrive setSigns(MecanumMotor motor, String x, String y, String turnRate) {
        int xS = this.stringToSign(x);
        int yS = this.stringToSign(y);
        int turnRateS = this.stringToSign(turnRate);

        switch (motor) {
            case FrontLeft:
                xFL = xS; yFL = yS; turnRateFL = turnRateS;
                break;
            case FrontRight:
                xFR = xS; yFR = yS; turnRateFR = turnRateS;
                break;
            case BackLeft:
                xBL = xS; yBL = yS; turnRateBL = turnRateS;
                break;
            case BackRight:
                xBR = xS; yBR = yS; turnRateBR = turnRateS;
                break;
        }

        return this;
    }

    public MecanumDrive move(double x, double y) {
        xRate = x; yRate = y;
        return this;
    }

    public MecanumDrive move(double[] movement) {
        if (movement.length != 2)
            throw new IllegalArgumentException("'movement' was not a two-element array!");

        xRate = movement[0]; yRate = movement[1];
        return this;
    }

    public MecanumDrive rotate(double turnRate) {
        this.turnRate = turnRate;
        return this;
    }

    public void apply() {
        frontLeft.setPower(
                this.xFL * xRate + this.yFL * yRate + this.turnRateFL * turnRate
        );
        frontRight.setPower(
                this.xFR * xRate + this.yFR * yRate + this.turnRateFR * turnRate
        );
        backLeft.setPower(
                this.xBL * xRate + this.yBL * yRate + this.turnRateBL * turnRate
        );
        backRight.setPower(
                this.xBR * xRate + this.yBR * yRate + this.turnRateBR * turnRate
        );
    }
}
