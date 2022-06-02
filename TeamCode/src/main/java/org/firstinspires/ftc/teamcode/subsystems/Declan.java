package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Declan {

    DcMotorEx armMotor1;

    public Declan(DcMotorEx armMotor1) {
        this.armMotor1 = armMotor1;

    }
    public void Level1() {
        this.armMotor1.setTargetPosition(1120);
        this.armMotor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor1.setVelocity(1400);
    }
    public void Level2() {
        this.armMotor1.setTargetPosition(2240);
        this.armMotor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor1.setVelocity(1400);
    }
    public void Level3() {
        this.armMotor1.setTargetPosition(3360);
        this.armMotor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor1.setVelocity(1400);
    }

    public void Home() {
        this.armMotor1.setTargetPosition(-armMotor1.getCurrentPosition());
        this.armMotor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor1.setVelocity(1400);
        this.armMotor1.setTargetPosition(0);

    }
}