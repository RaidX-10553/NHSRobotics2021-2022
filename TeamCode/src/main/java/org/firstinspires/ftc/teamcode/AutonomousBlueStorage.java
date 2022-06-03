// Autonomous Period
// Starting at Blue wall | Storage side
// Scoring pre-loaded box
// Going to Carousel and delivering duck onto ground
// Parking completely in storage

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Duck;
import org.firstinspires.ftc.teamcode.subsystems.Arm;



@Autonomous(name="AutoBlueStorage", group="Autonomous")
public class AutonomousBlueStorage extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor FL;
    DcMotor BL;
    DcMotor BR;
    DcMotor FR;

    //Arm
    DcMotorEx armMotor;
    Arm arm;

    //Bucket Servo
    CRServo bucketServo;
    Bucket bucket;
    
    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;

    private void left() {
        FL.setTargetPosition(-1120);
        FR.setTargetPosition(1120);
        BL.setTargetPosition(-1120);
        BR.setTargetPosition(1120);

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);
   }
    private void right() {
        FL.setTargetPosition(1120);
        FR.setTargetPosition(-1120);
        BL.setTargetPosition(1120);
        BR.setTargetPosition(-1120);

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);
   } 
    private void front(int inches) {
        FL.setTargetPosition(1120 * (inches / 3.77953));
        FR.setTargetPosition(1120 * (inches / 3.77953));
        BL.setTargetPosition(1120 * (inches / 3.77953));
        BR.setTargetPosition(1120 * (inches / 3.77953));

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   }
    private void back(int inches) {
        FL.setTargetPosition(-(1120 * (inches / 3.77953)));
        FR.setTargetPosition(-(1120 * (inches / 3.77953)));
        BL.setTargetPosition(-(1120 * (inches / 3.77953)));
        BR.setTargetPosition(-(1120 * (inches / 3.77953)));

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   }
    private void leftStrafe(int inches) {
        FL.setTargetPosition(-(1120 * (inches / 3.77953)));
        FR.setTargetPosition((1120 * (inches / 3.77953)));
        BL.setTargetPosition((1120 * (inches / 3.77953)));
        BR.setTargetPosition(-(1120 * (inches / 3.77953)));

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   }
    private void rightStrafe(int inches) {
        FL.setTargetPosition((1120 * (inches / 3.77953)));
        FR.setTargetPosition(-(1120 * (inches / 3.77953)));
        BL.setTargetPosition(-(1120 * (inches / 3.77953)));
        BR.setTargetPosition((1120 * (inches / 3.77953)));

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(2800);
        FR.setVelocity(2800);
        BL.setVelocity(2800);
        BR.setVelocity(2800);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   }

    @Override
    public void runOpMode() {

        // Hardware map here
        FL = hardwareMap.get(DcMotorEx.class, "FL");
        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BR = hardwareMap.get(DcMotorEx.class, "BR");
        FR = hardwareMap.get(DcMotorEx.class, "FR");

        //Bucket Servo
        bucketServo = hardwareMap.crservo.get("bucket");
        bucket = new Bucket(bucketServo);

        //Arm
        armMotor = hardwareMap.get(DcMotorEx.class, "arm");
        arm = new Arm(armMotor);






        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        if (isStopRequested()) return;
        
        front(18);

        

        while (opModeIsActive()) {
            telemetry.addData("","AUTO TIME");
            telemetry.addData("position", FL.getCurrentPosition());
            telemetry.addData("", FR.getCurrentPosition());
            telemetry.addData("", BL.getCurrentPosition());
            telemetry.addData("", BR.getCurrentPosition());
            telemetry.update();



            idle();
        }
        
        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }
}


