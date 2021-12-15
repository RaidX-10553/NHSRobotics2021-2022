package org.firstinspires.ftc.teamcode.William;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.checkerframework.checker.units.qual.Speed;

@Autonomous(name="BasicEncoderAuto", group="Autonomous")
public class BasicEncoderAuto extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private int frontLeftEncoderPosition;
    private int frontRightEncoderPosition;
    private int backLeftEncoderPosition;
    private int backRightEncoderPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        //initialization
        //declare motors
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");

        //set encoder values to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //left motor is reversed so drive wheels don't spin in opposite directions
        //since the left drive train is opposite
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeftEncoderPosition = 0;
        frontRightEncoderPosition = 0;
        backLeftEncoderPosition = 0;
        backRightEncoderPosition = 0;


        waitForStart();

        //insert calls to method here
        drive (100, 100, 0.1);
        // 1000 is the left target, 1000 is the right target, 0.25 is the speed
    }
    //*****possibly change parameters
    // possibly change targets
    private void drive (int leftTarget, int rightTarget, double speed){
        frontLeftEncoderPosition += leftTarget;
        frontRightEncoderPosition += rightTarget;
        backLeftEncoderPosition += leftTarget;
        backRightEncoderPosition += rightTarget;

        //sets values
        frontLeft.setTargetPosition(frontLeftEncoderPosition);
        frontRight.setTargetPosition(frontRightEncoderPosition);
        backLeft.setTargetPosition(backLeftEncoderPosition);
        backRight.setTargetPosition(backRightEncoderPosition);

        //actually tells the motors to run to said position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs the motors at the specified speed until they reach the target position
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

        //stops other code from running until the target position is reached
        while(opModeIsActive()&& frontLeft.isBusy()&& frontRight.isBusy() && backLeft.isBusy()&& backRight.isBusy())
        {
            idle();
        }
    }
}
