package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.checkerframework.checker.units.qual.Speed;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@Autonomous(name="BasicEncoderVuforia", group="Autonomous")
public class BasicEncoderVuforia extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private int frontLeftEncoderPosition;
    private int frontRightEncoderPosition;
    private int backLeftEncoderPosition;
    private int backRightEncoderPosition;

    //vuforia variables
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    private static final String VUFORIA_KEY =
            "AVgDgHH/////AAABmYSjDOxUjkoloTDlPbTfcxMdY+UnPMMGHvIoENz7ljjIJLU7u/WzCXUMDrkDD3rtaVaTTqHY2RiMeeBO0+nWwRe3aHkzxtpSa0LEdicMGhjyk0JyTKusUjg3l0kj1xYOmTidIjIlCc18/Z3FKZTBKEwZrSgakYxiot2r4zBdXcyMekArDle5NCxpDHATu261ZnwhBJc7UKazEkRCbtn9qaN0a5dB0kX3dhGxrargryTg0AuEj17NaXxy8tnq10HEXb2NiwvOJVFiw3YJhEMvyUq5bmY/c0yEchStOyy2bOswp5xtXE5+Qwy8Ty474gYH5ROWRdwrf+6mzFtS4CGdotST1dAOo3uuMgcTNvxsU4CZ";


    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;
    //end of vuforia variables

    @Override
    public void runOpMode() throws InterruptedException {
        //initialization
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

        //Vuforia Init
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());

                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        boolean isDuckDetected = false;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;

                            // check label to see if the camera now sees a Ball         ** ADDED **
                            if (recognition.getLabel().equals("Duck")) {            //  ** ADDED **
                                isDuckDetected = true;                             //  ** ADDED **
                                telemetry.addData("Duck Detected", "Going to location");      //  ** ADDED **
                            } else {                                               //  ** ADDED **
                                isDuckDetected = false;
                                telemetry.addData("Duck Not Detected", "Going to level 3");   //  ** ADDED **
                            }                                                      //  ** ADDED **
                        }
                        telemetry.update();
                    }
                }
            }

        }
        // Continue Auto here with roadrunner
        // Need to figure out how to determine location of duck and use the if statement
        //if (//duck location = 1) {
        //drive.followTrajectorySequence(level1);
        //}

        //if (//duck location = 2) {
        //drive.followTrajectorySequence(level2);
        //}

        //if (//duck location = 3) {
        //drive.followTrajectorySequence(level3);
        //}

        // Should I use the one above? or the if, else-if, else
        // Benefit of using the one below is that if tfod doesn't detect anything at all,
        // the robot will still deliver pre-box on top level.
        //if (//duck location = 1) {
        //    drive.followTrajectorySequence(level1);
        //} else if (//duck location = 2) {
        //    drive.followTrajectorySequence(level2);
        //} else {
        //    drive.followTrajectorySequence(level3);
        //}


        //ahhhhh
        //Continue more auto stuff

        //insert calls to method here
        drive (100, 100, 0.1);
        // 1000 is the left target, 1000 is the right target, 0.25 is the speed
    }
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.5f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }


    //*****possibly change parameters
    // possibly change targets
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
