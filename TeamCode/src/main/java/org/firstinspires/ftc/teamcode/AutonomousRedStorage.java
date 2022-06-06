// Autonomous Period
// Starting at Red wall | Storage side 
// Scoring pre-loaded box
// Going to Carousel and delivering duck onto ground 
// Parking completely in storage

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Duck;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;



@Autonomous(name="AutoRedStorage", group="Autonomous")
public class AutonomousRedStorage extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotorEx FL;
    DcMotorEx BL;
    DcMotorEx BR;
    DcMotorEx FR;

    //Arm
    DcMotorEx armMotor;
    Arm arm;

    //Bucket Servo
    CRServo bucketServo;
    Bucket bucket;

    CRServo clawServo;
    Claw claw;

    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;



    private void left() {
        FL.setTargetPosition((int) -(1120 * (28.2743338823 / 23.747487364)));
        FR.setTargetPosition((int) (1120 * (28.2743338823 / 23.747487364)));
        BL.setTargetPosition((int) -(1120 * (28.2743338823 / 23.747487364)));
        BR.setTargetPosition((int) (1120 * (28.2743338823 / 23.747487364)));

        FL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        FL.setVelocity(1400);
        FR.setVelocity(1400);
        BL.setVelocity(1400);
        BR.setVelocity(1400);
    }
    private void right() {
        FL.setTargetPosition((int) (1120 * (28.2743338823 / 23.747487364)));
        FR.setTargetPosition((int) -(1120 * (28.2743338823 / 23.747487364)));
        BL.setTargetPosition((int) (1120 * (28.2743338823 / 23.747487364)));
        BR.setTargetPosition((int) -(1120 * (28.2743338823 / 23.747487364)));

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

        FL.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        FR.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        BL.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        BR.setTargetPosition((int) (1120 * (inches / 23.747487364)));

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
        FL.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        FR.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        BL.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        BR.setTargetPosition((int) -(1120 * (inches / 23.747487364)));

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
        FL.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        FR.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        BL.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        BR.setTargetPosition((int) -(1120 * (inches / 23.747487364)));

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
        FL.setTargetPosition((int) (1120 * (inches / 23.747487364)));
        FR.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        BL.setTargetPosition((int) -(1120 * (inches / 23.747487364)));
        BR.setTargetPosition((int) (1120 * (inches / 23.747487364)));

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

        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);




        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();


        if (isStopRequested()) return;

        left();
        //front(27);
        //right();
        //front(24);



        while (opModeIsActive()) {
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
        bucketServo.setPower(0);
    }
}



