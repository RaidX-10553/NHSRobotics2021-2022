// Autonomous Period
// Starting at Blue wall | Storage side
// Scoring pre-loaded box
// Going to Carousel and delivering duck onto ground
// Parking completely in storage

package org.firstinspires.ftc.teamcode.William;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.AprilTagLocation;
import org.firstinspires.ftc.teamcode.subsystems.MarkerDetectionPipeline;


import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous(name="EncoderBlueStorage", group="Autonomous")
public class EncoderBlueStorage extends LinearOpMode {
    //encoder variable declaration
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private int frontLeftEncoderPosition;
    private int frontRightEncoderPosition;
    private int backLeftEncoderPosition;
    private int backRightEncoderPosition;
    //end of encoder variable declaration

    /* Declare OpMode members. */

    OpenCvCamera phoneCam;

    MarkerDetectionPipeline pipeline;

    boolean started = false;

    @Override
    public void runOpMode() {
        //initialization for encoders
        //declare motors
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");

        //set encoder values to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //left motor is reversed so drive wheels don't spin in opposite directions
        //since the left drive train is opposite
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeftEncoderPosition = 0;
        frontRightEncoderPosition = 0;
        backLeftEncoderPosition = 0;
        backRightEncoderPosition = 0;
        //End of encoder code
        pipeline = new MarkerDetectionPipeline();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.setPipeline(pipeline);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                /*
                 * Tell the camera to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Also, we specify the rotation that the camera is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                phoneCam.startStreaming(640, 480, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        //Still working on the trajectories, not final
        //Road Runner Trajectory





        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        if (isStopRequested()) return;


        AprilTagLocation position = pipeline.getLastPosition();


        if (position == AprilTagLocation.LEFT) {
            started = true;
            telemetry.addData("","Going to Level 1");
            telemetry.update();
            //encoder shit here

        } else if (position == AprilTagLocation.MIDDLE) {
            started = true;
            telemetry.addData("","Going to Level 2");
            telemetry.update();
            //encoder shit here


        } else if (position == AprilTagLocation.RIGHT) {
            started = true;
            telemetry.addData("","Going to Level 3");
            telemetry.update();
            //encoder shit here


        }
        else {
            started = true;
            telemetry.addData("","Failed to detect");
            telemetry.update();
            //encoder shit here


        }

        while (opModeIsActive()) {

            if (started = true) {

                phoneCam.stopStreaming();

            }

       }

    }
    //encoder method
    private void drive (int leftTarget, int rightTarget, double speed){
        frontLeftEncoderPosition += leftTarget;
        frontRightEncoderPosition += rightTarget;
        backLeftEncoderPosition += leftTarget;
        backRightEncoderPosition += rightTarget;

        //sets values
        frontLeft.setTargetPosition(frontLeftEncoderPosition);
        frontRight.setTargetPosition(frontRightEncoderPosition);
        backLeft.setTargetPosition(backLeftEncoderPosition);
        backRight.setTargetPosition(backRightEncoderPosition);

        //actually tells the motors to run to said position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs the motors at the specified speed until they reach the target position
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

        //stops other code from running until the target position is reached
        while(opModeIsActive()&& frontLeft.isBusy()&& frontRight.isBusy() && backLeft.isBusy()&& backRight.isBusy())
        {
            idle();
        }
    }
}


