package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "xDrive TeleOp", group = "TeleOp")
public class ProjectX_William extends LinearOpMode {

    //initialize motors
    DcMotor frontMotor;
    DcMotor backMotor;
    DcMotor rightMotor;
    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        /*
        power for moving up and down (y axis)
        power for moving left and right (x axis)
        Spin power for turning the robot
         */
        double yAxisPower;
        double xAxisPower;
        double spinPower;

        //change to fit with anton's phones
        frontMotor = hardwareMap.dcMotor.get("motorA");
        rightMotor = hardwareMap.dcMotor.get("motorB");
        backMotor = hardwareMap.dcMotor.get("motorC");
        leftMotor = hardwareMap.dcMotor.get("motorD");

        frontMotor.setPower(0);
        backMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setPower(0);


        waitForStart();


        //initialization
        while(opModeIsActive()) {
            spinPower =-gamepad1.right_stick_x;

            //if rightStick is being used
            if (spinPower != 0){
                //direction probably wrong, fix when testing
                frontMotor.setDirection(DcMotorSimple.Direction.FORWARD);

                frontMotor.setPower(spinPower);
                backMotor.setPower(spinPower);

                rightMotor.setPower(spinPower);
                leftMotor.setPower(spinPower);
            }

            //not spin stuff
            else {
                //direction probably wrong, fix when testing
                frontMotor.setDirection(DcMotorSimple.Direction.REVERSE);

                yAxisPower = -gamepad1.left_stick_y;
                xAxisPower = -gamepad1.left_stick_x;

                frontMotor.setPower(xAxisPower);
                backMotor.setPower(-xAxisPower);

                rightMotor.setPower(yAxisPower);
                leftMotor.setPower(-yAxisPower);
            }

            //might change values to positive when testing
            telemetry.addData("X-Axis Power", -gamepad1.left_stick_x);
            telemetry.addData("Y-Axis Power", -gamepad1.left_stick_y);
            telemetry.addData("Spin Power", -gamepad1.right_stick_x);
            telemetry.update();



        }







    }

}
