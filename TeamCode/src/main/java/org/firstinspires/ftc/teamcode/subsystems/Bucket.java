package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Bucket {

    CRServo bucketServo;


    public Bucket (CRServo bucketServo ) {
        this.bucketServo = bucketServo;


    }
    public void Drop(){
        this.bucketServo.setPower(1);
    }
    public void Return(){
        this.bucketServo.setPower(-1);
    }



}


