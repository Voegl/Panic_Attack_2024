package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.kowalski.control.ControlUtils;

@TeleOp(name = "Lolcow OP")
public class LolcowOP extends OpMode {
    DcMotor arm;

    @Override
    public void init() {
        arm = hardwareMap.get(DcMotor.class, "armmotor");
        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        arm.setPower(ControlUtils.discreteAxis(gamepad2.right_trigger, gamepad2.left_trigger));
    }
}
