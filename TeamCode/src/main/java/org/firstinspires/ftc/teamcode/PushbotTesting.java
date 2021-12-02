// pushbot for various things
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class PushbotTesting extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
  
    public void init() {
       frontLeft = hardwareMap.dcMotor.get("FL");
       frontRight = hardwareMap.dcMotor.get("FR");
       backLeft = hardwareMap.dcMotor.get("BL");
       backRight = hardwareMap.dcMotor.get("BR");
    }

    public void loop() {
        float drivePower = -gamepad1.left_stick_y;
        float rotatePower = gamepad1.left_stick_x;      
      
        frontLeft.setPower(drivePower + rotatePower);
        frontRight.setPower(drivePower - rotatePower);
        backLeft.setPower(drivePower + rotatePower);
        backRight.setPower(drivePower - rotatePower);
    }
}
