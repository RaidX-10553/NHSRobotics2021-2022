package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Arm {
 
    DcMotorEx armMotor;

    public Arm(DcMotor armMotor) {
        this.armMotor = armMotor;


    }

    public void Level1() {
        this.armMotor.setTargetPosition();
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setVelocity(1400);
    }
    public void Level2() {
        this.amrMotor.setTargetPosition();
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setVelocity(1400);
    }

    public void Level3() {
        this.armMotor.setTargetPosition();
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setVelocity(1400);
    }

    public void Home() {
        this.armMotor.setTargetPosition();
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setVelocity(1400);
    }
}
