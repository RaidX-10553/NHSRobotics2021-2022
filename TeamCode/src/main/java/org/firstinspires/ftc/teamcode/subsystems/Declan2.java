package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Declan2 {

    DcMotorEx armMotor2;

    public Declan2(DcMotorEx armMotor2) {
        this.armMotor2 = armMotor2;

    }
    public void Level1() {
        this.armMotor2.setTargetPosition(1120);
        this.armMotor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor2.setVelocity(1400);
    }
    public void Level2() {
        this.armMotor2.setTargetPosition(2240);
        this.armMotor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor2.setVelocity(1400);
    }
    public void Level3() {
        this.armMotor2.setTargetPosition(3360);
        this.armMotor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor2.setVelocity(1400);
    }

    public void Home() {
        this.armMotor2.setTargetPosition(-armMotor2.getCurrentPosition());
        this.armMotor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor2.setVelocity(1400);
        this.armMotor2.setTargetPosition(0);

    }
}