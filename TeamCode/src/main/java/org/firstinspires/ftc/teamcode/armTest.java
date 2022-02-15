package org.firstinspires.ftc.teamcode;


import android.view.KeyEvent;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Arm;




@TeleOp(name = "ArmTest", group = "TeleOp")
public class armTest extends LinearOpMode {

    //Arm

    DcMotorEx armMotor;
    Arm arm;
    private boolean d_loop = false;

    @Override
    public void runOpMode() throws InterruptedException {

        //Arm
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        arm = new Arm(armMotor);


        waitForStart();
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {

            // Arm Motors
            if (gamepad2.a && !armMotor.isBusy()) {
                telemetry.addData("Level:", "1" );
                arm.Level1();
            }

            if (gamepad2.b && !armMotor.isBusy()) {
                telemetry.addData("Level:", "2" );
                arm.Level2();
            }

            if (gamepad2.y && !armMotor.isBusy()) {
                telemetry.addData("Level:", "3" );
                arm.Level3();
            }

            if (gamepad2.x && !armMotor.isBusy()) {
                telemetry.addData("Level:", "Home" );
                arm.Home();


            }

            // Sets while loop to start
            // Manual Mode
            if (gamepad2.dpad_down) {
                d_loop = true;
            }

            // Dpad_Loop
            // Allows for manual control
            while (!armMotor.isBusy() && d_loop && opModeIsActive()) {
                telemetry.addData("Arm Mode:", "MANUAL" );
                telemetry.addData("velocity", armMotor.getVelocity());
                telemetry.addData("position", armMotor.getCurrentPosition());
                telemetry.update();

                armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                armMotor.setPower((-gamepad2.left_stick_y)/2);

                if (  (armMotor.getCurrentPosition() <0) || (armMotor.getCurrentPosition() >3360) || gamepad2.dpad_up ) {
                    telemetry.addData("Loop Ends", "true");
                    armMotor.setPower(0);
                    d_loop = false;
                }

            }


            telemetry.addData("Arm Mode:", "AUTOMATIC" );
            telemetry.addData("velocity", armMotor.getVelocity());
            telemetry.addData("position", armMotor.getCurrentPosition());
            telemetry.addData("is at target", !armMotor.isBusy());
            telemetry.update();

        }
        idle();
    }
}

