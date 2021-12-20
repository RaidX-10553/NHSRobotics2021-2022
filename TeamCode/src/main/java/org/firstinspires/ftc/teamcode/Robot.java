package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;
import org.firstinspires.ftc.teamcode.drive.mecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Duck;



@TeleOp(name = "Comp TeleOp", group = "TeleOp")
public class Robot extends LinearOpMode {
    //Intake
    DcMotor intakeMotor;
    Intake intakewheel;

    //Intake Servo
    CRServo intakeServo1;
    CRServo intakeServo2;

    //Arm
    //DcMotor longArm;
    //DcMotor smallArm;

    //Arm Servo
    CRServo ArmServo;

    //Bucket Servo
    //CRServo bucketServo;
    //Bucket bucket;

    

    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;

    @Override
    public void runOpMode() throws InterruptedException {

        //Drive
        

        //Intake
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakewheel = new Intake(intakeMotor);

        //Intake Servos
        intakeServo1 = hardwareMap.crservo.get("NormalServo");
        intakeServo2 = hardwareMap.crservo.get("ReversedServo");

        //Bucket Servo
        //bucketServo = hardwareMap.crservo.get("bucket");
        //bucket = new Bucket(bucketServo);

        //Arm Servo
        ArmServo = hardwareMap.crservo.get("ArmServo");

        //Arm Motors
        //longArm = hardwareMap.dcMotor.get("ArmMotor");
        //smallArm = hardwareMap.dcMotor.get("smallArmMotor");

        //Duck Spin
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        duckSpin = new Duck(duckMotor);

        // either left or right
        


        waitForStart();

        while (opModeIsActive()) {


        
            mecanumDrive.setDrivePower(new Pose2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x,-gamepad1.right_stick_x));
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



            //Intake Arm Servos
            //Needs to increase each time by 0.05
            if (gamepad1.right_bumper) {
                intakeServo1.setPower(0.05);
                intakeServo2.setPower(0.05);
            }
            if (gamepad1.left_bumper) {
                intakeServo1.setPower(-0.05);
                intakeServo2.setPower(-0.05);

            }

            /*

            //Arm Motors
            double arm = gamepad2.left_stick_y;
                longArm.setPower(arm);

            double smolarm = gamepad2.right_stick_y;
                smallArm.setPower(smolarm);

            */




            //Arm Servo

            ArmServo.setPower(gamepad2.right_trigger * 0.4);
            ArmServo.setPower(-gamepad2.left_trigger * 0.4);




            /*
            //Bucket Servo
            if (gamepad2.right_bumper) {
                bucket.Drop();
            }
            if (gamepad2.left_bumper) {
                bucket.Return();
            }
            */


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

        }
        idle();
    }
}

