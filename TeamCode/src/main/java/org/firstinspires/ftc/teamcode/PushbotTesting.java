// pushbot for various things
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;




@TeleOp(name = "Teleop Pushbot", group = "TeleOp")
public class PushbotTesting extends LinearOpMode {
    SampleMecanumDrive mecanumDrive;

    @Override
    public void runOpMode() throws InterruptedException {

        mecanumDrive = new SampleMecanumDrive(hardwareMap);
     
    waitForStart();
   
    while (opModeIsActive()) {
        mecanumDrive.setDrivePower(
                new Pose2d(gamepad1.left_stick_y,
                        gamepad1.right_stick_x,
                        gamepad1.left_stick_x));

        mecanumDrive.updatePoseEstimate();
        
      
      
      

      
    } 
        idle();
    }
}
    
