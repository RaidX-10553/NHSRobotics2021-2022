package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Arm {

    DcMotorEx armMotor;

    public Arm(DcMotorEx armMotor) {
        this.armMotor = armMotor;
        this.armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
    public void Level1() {
        this.armMotor.setTargetPosition(1120);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(2600);
    }
    public void Level2() {
        this.armMotor.setTargetPosition(2240);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(2600);
    }
    public void Level3() {
        this.armMotor.setTargetPosition(4928);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(2600);
    }

    public void Home() {
        this.armMotor.setTargetPosition(-armMotor.getCurrentPosition());
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(2600);
        this.armMotor.setTargetPosition(0);

    }
}