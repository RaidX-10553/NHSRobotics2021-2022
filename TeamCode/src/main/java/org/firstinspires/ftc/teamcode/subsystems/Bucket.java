package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Bucket {

    CRServo bucketServo;


    public Bucket (CRServo bucketServo ) {
        this.bucketServo = bucketServo;


    }
    public void Dump(){
        this.bucketServo.setPower(1);

    }
    public void Home(){
        this.bucketServo.setPower(-1);
    }
    public void off(){
        this.bucketServo.setPower(0);
    }



}


