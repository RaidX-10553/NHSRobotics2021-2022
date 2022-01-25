package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.subsystems.Arm;


@TeleOp(name = "Arm Test", group = "TeleOp")
public class armTest extends LinearOpMode {


    //Arm
    DcMotorEx armMotor;
    Arm arm;


    @Override
    public void runOpMode() throws InterruptedException {

        //Arm
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        arm = new Arm(armMotor);



        waitForStart();

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {

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
                 //Resets encoders at the home position

            }


            telemetry.addData("velocity", armMotor.getVelocity());
            telemetry.addData("position", armMotor.getCurrentPosition());
            telemetry.addData("is at target", !armMotor.isBusy());
            telemetry.update();

        }
        idle();
    }
}

