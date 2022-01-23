package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Duck;
import org.firstinspires.ftc.teamcode.subsystems.Arm;




@TeleOp(name = "Comp TeleOp", group = "TeleOp")
public class Robot extends LinearOpMode {
    //Intake
    DcMotor intakeMotor;
    Intake intakewheel;

    //Drive
    SampleMecanumDrive mecanumDrive;

    //Arm
    DcMotorEx armMotor;
    Arm arm;

    //Bucket Servo
    CRServo bucketServo;
    Bucket bucket;
    
    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;


    @Override
    public void runOpMode() throws InterruptedException {

        //Drive
        mecanumDrive = new SampleMecanumDrive(hardwareMap);

        //Intake
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakewheel = new Intake(intakeMotor);

        //Bucket Servo
        bucketServo = hardwareMap.crservo.get("bucket");
        bucket = new Bucket(bucketServo);

        //Arm
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        arm = new Arm(armMotor);

        //Duck Spin
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        duckSpin = new Duck(duckMotor);


        


        waitForStart();

        while (opModeIsActive()) {

            //Drive
            mecanumDrive.setDrivePower(
                    new Pose2d(-gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x));

            mecanumDrive.updatePoseEstimate();


            //Intake motor
            if (gamepad1.left_trigger >= 0.5) {
                intakewheel.In();
            } else {
                intakewheel.Off();
            }

            if (gamepad1.right_trigger >= 0.5) {
                intakewheel.Out();
            } else {
                intakewheel.Off();

            }


            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Arm Motors
            if (gamepad1.a && !armMotor.isBusy()) {
                arm.Level1();
            }

            if (gamepad1.b && !armMotor.isBusy()) {
                arm.Level2();
            }

            if (gamepad1.y && !armMotor.isBusy()) {
                arm.Level3();
            }

            if (gamepad1.x && !armMotor.isBusy()) {
                arm.Home();
                armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets encoders at the home position

            }


            //Bucket Servo
            if (gamepad2.right_bumper) {
                bucket.Drop();
            }
            if (gamepad2.left_bumper) {
                bucket.Return();
            }


            //DUCK SPIN
            if (gamepad2.a) {
                duckSpin.Spin();
            } else{
                duckSpin.DontSpin();
            }

            if (gamepad2.b) {
                duckSpin.ReverseSpin();
            } else{
                duckSpin.DontSpin();
            }



            telemetry.addData("velocity", armMotor.getVelocity());
            telemetry.addData("position", armMotor.getCurrentPosition());
            telemetry.addData("is at target", !armMotor.isBusy());
            telemetry.update();

        }
        idle();
    }
}

