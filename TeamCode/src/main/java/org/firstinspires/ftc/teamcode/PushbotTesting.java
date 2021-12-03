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
      
       frontRight.setDirection(DcMotor.Direction.REVERSE);
       backRight.setDirection(DcMotor.Direction.REVERSE);
      
    //   frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    //   backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
     
    waitForStart();
   
    while (opModeIsActive()) {
        //float drivePower = gamepad1.left_stick_y;
        //float rotatePower = gamepad1.left_stick_x;
      
        //frontLeft.setPower(drivePower + rotatePower);
        //frontRight.setPower(drivePower - rotatePower);
        //backLeft.setPower(drivePower + rotatePower);
        //backRight.setPower(drivePower - rotatePower);
        
      
      
      
          double y = gamepad1.left_stick_y; // Remember, this is reversed!
          double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
          double rx = gamepad1.left_stick_x;
          
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
      
    } 
        idle();
    }
}
    
