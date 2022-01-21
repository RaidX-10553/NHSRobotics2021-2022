package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Arm {
 
    DcMotorEx armMotor;

    public Arm(DcMotorEx armMotor) {
        this.armMotor = armMotor;


    }

    public void Level1() {
        this.armMotor.setTargetPosition(257);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }
    public void Level2() {
        this.armMotor.setTargetPosition(257);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }

    public void Level3() {
        this.armMotor.setTargetPosition(257);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }

    public void Home() {
        this.armMotor.setTargetPosition(0);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }
}
