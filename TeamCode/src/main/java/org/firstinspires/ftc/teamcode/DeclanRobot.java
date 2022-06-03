package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Declan;
import org.firstinspires.ftc.teamcode.subsystems.Declan2;
import org.firstinspires.ftc.teamcode.subsystems.Duck;
import org.firstinspires.ftc.teamcode.subsystems.Duck2;





@TeleOp(name = "Declan Teleop", group = "TeleOp")
public class DeclanRobot extends LinearOpMode {
    //Intake
   // DcMotor intakeMotor;
    //Intake intakewheel;

    //Drive
    SampleMecanumDrive mecanumDrive;

    //Arm
    DcMotorEx armMotor1;
    Declan arm1;

    DcMotorEx armMotor2;
    Declan2 arm2;


    //claw Servo
    CRServo clawServo;
    Claw claw;
    
    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;

    DcMotor duckMotor2;
    Duck2 duckSpin2;



    //Boolean
    private Boolean d_loop = false;
    private boolean reverse = false;
    private boolean precise = false;

    @Override
    public void runOpMode() throws InterruptedException {

        //Drive
        mecanumDrive = new SampleMecanumDrive(hardwareMap);

        //Intake
        //intakeMotor = hardwareMap.dcMotor.get("intake");
        //intakewheel = new Intake(intakeMotor);

        //Bucket Servo
        clawServo = hardwareMap.crservo.get("claw");
        claw = new Claw(clawServo);


        //Arm
        armMotor1 = hardwareMap.get(DcMotorEx.class, "arm1");
        arm1 = new Declan(armMotor1);

        armMotor2 = hardwareMap.get(DcMotorEx.class, "arm2");
        arm2 = new Declan2(armMotor2);

        //Duck Spin
        duckMotor = hardwareMap.dcMotor.get("duck");
        duckSpin = new Duck(duckMotor);

        duckMotor2 = hardwareMap.dcMotor.get("duck2");
        duckSpin2 = new Duck2(duckMotor2);

        
        armMotor2.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();





        while (opModeIsActive()) {

            //Drive
            if (gamepad1.dpad_down) {
                reverse = true;
            }

            if (gamepad1.dpad_up) {
                precise = true;
            }

            // Precise driving activated with Dpad_up
            // To exit press Dpad_up again
            while (precise && opModeIsActive()) {
                telemetry.addData("Mode", "PRECISE");
                telemetry.update();

                mecanumDrive.setDrivePower(
                        new Pose2d((gamepad1.left_stick_y)/2.5,
                                (gamepad1.right_stick_x)/2.5,
                                (gamepad1.left_stick_x)/2.5));
                mecanumDrive.updatePoseEstimate();

                if (gamepad1.dpad_right) {
                    telemetry.addData("Mode", "NORMAL");
                    precise = false;
                }

            }

            // Reverse driving activated with Dpad_down
            // To exit press Dpad_down again
            while (reverse && opModeIsActive()) {
                telemetry.addData("Mode", "REVERSE");
                telemetry.update();

                mecanumDrive.setDrivePower(
                        new Pose2d(-gamepad1.left_stick_y,
                                -gamepad1.right_stick_x,
                                -gamepad1.left_stick_x));
                mecanumDrive.updatePoseEstimate();

                if (gamepad1.dpad_right) {
                    telemetry.addData("Mode", "NORMAL");
                    reverse = false;
                }

            }


            // Normal Driving
            mecanumDrive.setDrivePower(
                    new Pose2d(gamepad1.left_stick_y,
                            gamepad1.right_stick_x,
                            gamepad1.left_stick_x));
            mecanumDrive.updatePoseEstimate();

            telemetry.update();






            
            // Arm Motors
            if (gamepad2.a) {
                telemetry.addData("Level:", "1" );
                arm1.Level1();
                arm2.Level1();
            }

            if (gamepad2.b) {
                telemetry.addData("Level:", "2" );
                arm1.Level2();
                arm2.Level2();
            }

            if (gamepad2.y) {
                telemetry.addData("Level:", "3" );
                arm1.Level3();
                arm2.Level3();
            }

            if (gamepad2.x) {
                telemetry.addData("Level:", "Home" );
                arm1.Home();
                arm2.Home();
            }
            




            // Claw
            /*
            if (gamepad2.dpad_down) {
                claw.Open();
            }
            if (gamepad2.dpad_up) {
                claw.Close();

            }
            */
            clawServo.setPower(gamepad2.left_stick_y);





            //DUCK SPIN
            if (gamepad2.left_bumper) {
                duckSpin.Spin();
                duckSpin2.Spin();
            } else{
                duckSpin.DontSpin();
                duckSpin2.DontSpin();
            }

            if (gamepad2.right_bumper) {
                duckSpin.ReverseSpin();
                duckSpin2.ReverseSpin();
            } else{
                duckSpin.DontSpin();
                duckSpin2.DontSpin();
            }




            telemetry.update();

        }
        idle();
    }
}

