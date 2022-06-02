package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;


public class Duck2 {

    DcMotor duckMotor2;


    public Duck2(DcMotor duckMotor2) {
        this.duckMotor2 = duckMotor2;


    }

    public void Spin() {
        this.duckMotor2.setPower(1);
    }
    public void ReverseSpin() {
        this.duckMotor2.setPower(-1);
    }

    public void DontSpin() {
        this.duckMotor2.setPower(0);
    }
}






