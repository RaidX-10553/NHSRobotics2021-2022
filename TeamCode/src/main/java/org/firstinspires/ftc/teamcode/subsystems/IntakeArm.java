package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;


public class IntakeArm {

    Servo intakeServo;


    public IntakeArm (Servo intakeServo) {
        this.intakeServo = intakeServo;


    }
    public void Raise(){
        this.intakeServo.setPosition(1);
    }
    public void Lower(){
        this.intakeServo.setPosition(0);
    }


}