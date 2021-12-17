package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Duck;



@TeleOp(name = "Comp TeleOp", group = "TeleOp")
public class Robot extends LinearOpMode {
    //Intake
    DcMotor intakeMotor;
    Intake intakewheel;

    //Intake Servo
    Servo intakeServo1;
    Servo intakeServo2;

    //Arm
    DcMotor longArm;
    DcMotor smallArm;

    //Arm Servo
    Servo ArmServo;

    //Bucket Servo
    CRServo bucketServo;
    Bucket bucket;

    //Drive
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    //Duck Spin
    DcMotor duckMotor;
    Duck duckSpin;

    @Override
    public void runOpMode() throws InterruptedException {

        //Drive
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");

        //Intake
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakewheel = new Intake(intakeMotor);

        //Intake Servos
        intakeServo1 = hardwareMap.servo.get("NormalServo");
        intakeServo2 = hardwareMap.servo.get("ReversedServo");

        //Bucket Servo
        bucketServo = hardwareMap.crservo.get("bucket");
        bucket = new Bucket(bucketServo);

        //Arm Servo
        ArmServo = hardwareMap.servo.get("ArmServo");

        //Arm Motors
        longArm = hardwareMap.dcMotor.get("ArmMotor");
        smallArm = hardwareMap.dcMotor.get("smallArmMotor");

        //Duck Spin
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        duckSpin = new Duck(duckMotor);

        // either left or right
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.left_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);



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
                intakeServo1.setPosition(intakeServo1.getPosition()+0.05);
                intakeServo2.setPosition(intakeServo1.getPosition()-0.05);
            }
            if (gamepad1.left_bumper) {
                intakeServo1.setPosition(intakeServo1.getPosition()-0.05);
                intakeServo2.setPosition(intakeServo1.getPosition()+0.05);

            }


            //Arm Motors
            double arm = gamepad2.left_stick_y;
                longArm.setPower(arm);

            double smolarm = gamepad2.right_stick_y;
                longArm.setPower(smolarm);



            //Arm Servo
            if (gamepad2.right_trigger > 0.5) {
                ArmServo.setPosition(ArmServo.getPosition()+0.02);
            }
            if (gamepad2.left_trigger > 0.5) {
                ArmServo.setPosition(ArmServo.getPosition()-0.02);


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

        }
        idle();
    }
}

