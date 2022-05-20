package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;


public class Bucket {

    Servo bucketServo;


    public Bucket(Servo bucketServo ) {
        this.bucketServo = bucketServo;


    }
    public void Home(){
        this.bucketServo.setPosition(0);
    }
    public void Dump(){
        this.bucketServo.setPosition(0);
    }



}


