package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;


public class Duck {

    DcMotor duckMotor;


    public Duck(DcMotor duckMotor) {
        this.duckMotor = duckMotor;


    }

    public void Spin() {
        this.duckMotor.setPower(1);
    }
    public void ReverseSpin() {
        this.duckMotor.setPower(1);
    }

    public void DontSpin() {
        this.duckMotor.setPower(0);
    }
}






