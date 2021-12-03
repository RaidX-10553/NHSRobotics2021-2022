// pushbot for various things
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Teleop Pushbot", group = "TeleOp")
public class PushbotTesting extends LinearOpMode {
  
    @Override
    public void runOpMode() throws InterruptedException {
       DcMotor frontLeft;
       DcMotor frontRight;
       DcMotor backLeft;
       DcMotor backRight;
  
       frontLeft = hardwareMap.dcMotor.get("FL");
       frontRight = hardwareMap.dcMotor.get("FR");
       backLeft = hardwareMap.dcMotor.get("BL");
       backRight = hardwareMap.dcMotor.get("BR");      
      
      // If bot spins when going forward, uncomment the stuff below
      // either left or right
      
    //   frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    //   backRight.setDirection(DcMotorSimple.Direction.REVERSE);
      
       frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
     
    waitForStart();
   
    while (opModeIsActive()) {
        float drivePower = -gamepad1.left_stick_y;
        float rotatePower = gamepad1.left_stick_x;      
      
        frontLeft.setPower(drivePower + rotatePower);
        frontRight.setPower(drivePower - rotatePower);
        backLeft.setPower(drivePower + rotatePower);
        backRight.setPower(drivePower - rotatePower);
        
    } 
        idle();
    }
}
    
