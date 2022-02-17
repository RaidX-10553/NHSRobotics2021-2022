// pushbot for various things
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;




@TeleOp(name = "Pushbot", group = "TeleOp")
public class PushbotTesting extends LinearOpMode {

    SampleMecanumDrive mecanumDrive;

    private boolean reverse = false;
    private boolean precise = false;


    @Override
    public void runOpMode() throws InterruptedException {

        mecanumDrive = new SampleMecanumDrive(hardwareMap);




    waitForStart();

    while (opModeIsActive()) {



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





    }
        idle();
    }
}
    
