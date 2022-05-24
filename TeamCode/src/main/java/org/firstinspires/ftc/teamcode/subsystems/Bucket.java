package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Bucket {

    Servo bucketServo;


    public Bucket (Servo bucketServo ) {
        this.bucketServo = bucketServo;


    }
    public void Dump(){
        this.bucketServo.setPosition(1);
    }
    public void Home(){
        this.bucketServo.setPosition(0);
    }



}


