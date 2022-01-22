// Autonomous Period
// Starting at Blue wall | warehouse side
// Scoring pre-loaded box
// Going to netural warehouse and possibly picking up and delivering more elements on to shipping hub. (Gotta be fast) TBD
// Parking completely in warehouse

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.AprilTagLocation;
import org.firstinspires.ftc.teamcode.subsystems.MarkerDetectionPipeline;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvWebcam;


@Autonomous(name="AutoBlueWarehouse", group="Autonomous")
public class AutonomousBlueWarehouse extends LinearOpMode {

    /* Declare OpMode members. */

    OpenCvWebcam webcam;


    MarkerDetectionPipeline pipeline;

    boolean started = false;

    @Override
    public void runOpMode() {
        pipeline = new MarkerDetectionPipeline();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webby"), cameraMonitorViewId);

        webcam.setPipeline(pipeline);


        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
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
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
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


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(11.5, -61.5, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        TrajectorySequence level1 = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(0, -39.5), Math.toRadians(-230))
                .back(15)
                .turn(Math.toRadians(-130))
                .forward(30)
                .forward(5)
                .build();

        TrajectorySequence level2 = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(0, -39.5), Math.toRadians(-230))
                .back(15)
                .turn(Math.toRadians(-130))
                .forward(30)
                .forward(5)
                .build();

        TrajectorySequence level3 = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(0, -39.5), Math.toRadians(-230))
                .back(15)
                .turn(Math.toRadians(-130))
                .forward(30)
                .forward(5)
                .build();





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
            drive.followTrajectorySequence(level1);


        } else if (position == AprilTagLocation.MIDDLE) {
            started = true;
            telemetry.addData("","Going to Level 2");
            telemetry.update();
            drive.followTrajectorySequence(level2);


        } else if (position == AprilTagLocation.RIGHT) {
            started = true;
            telemetry.addData("","Going to Level 3");
            telemetry.update();
            drive.followTrajectorySequence(level3);


        }
        else {
            started = true;
            telemetry.addData("","Failed to detect");
            telemetry.update();
            drive.followTrajectorySequence(level3);


        }

        while (opModeIsActive()) {

            if (started = true) {

                webcam.stopStreaming();
            }

        }
    }
}