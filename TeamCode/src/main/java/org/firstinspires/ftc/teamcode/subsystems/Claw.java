package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Claw {

    CRServo clawServo;


    public Claw (CRServo clawServo ) {
        this.clawServo = clawServo;


    }
    public void Close(){
        this.clawServo.setPower(1);
    }
    public void Open(){
        this.clawServo.setPower(0);
    }



}


