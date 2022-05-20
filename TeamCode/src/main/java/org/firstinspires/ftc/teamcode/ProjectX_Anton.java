package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "xDrive Teleop Mk2", group = "TeleOp")
public class ProjectX_Anton extends LinearOpMode {

    //Initialize motors
    DcMotor frontMotor;
    DcMotor backMotor;
    DcMotor rightMotor;
    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        /*
        power for moving up and down (y axis) (left stick)
        power for moving left and right (x axis) (left stick)
        Spin power for turning the robot (x axis) (right stick)
         */

        double yAxis;
        double xAxis;
        double r;


        frontMotor = hardwareMap.dcMotor.get("motorA");
        rightMotor = hardwareMap.dcMotor.get("motorB");
        backMotor = hardwareMap.dcMotor.get("motorC");
        leftMotor = hardwareMap.dcMotor.get("motorD");

        backMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontMotor.setPower(0);
        backMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setPower(0);




        waitForStart();


        //When "Play" is pressed
        while(opModeIsActive()) {

            //Rotate!!
            r =-gamepad1.right_stick_x;
            //Axis Powers
            yAxis = -gamepad1.left_stick_y;
            xAxis = -gamepad1.left_stick_x;


            //Rotation
            frontMotor.setPower(r);
            backMotor.setPower(r);
            rightMotor.setPower(r);
            leftMotor.setPower(r);


            //xAxis (left and right) (relative to front)
            frontMotor.setPower(-xAxis);
            backMotor.setPower(xAxis);
            leftMotor.setPower(xAxis);
            rightMotor.setPower(-xAxis);

            //yAxis (up and down) (relative to front)
            frontMotor.setPower(yAxis);
            backMotor.setPower(-yAxis);
            leftMotor.setPower(yAxis);
            rightMotor.setPower(-yAxis);


            //Telemetry
            telemetry.addData("X-Axis Power", gamepad1.left_stick_x);
            telemetry.addData("Y-Axis Power", -gamepad1.left_stick_y);
            telemetry.addData("Spin Power", -gamepad1.right_stick_x);
            telemetry.update();



        }







    }

}
