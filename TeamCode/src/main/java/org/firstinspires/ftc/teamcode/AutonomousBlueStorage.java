// Autonomous Period
// Starting at Blue wall | Storage side
// Scoring pre-loaded box
// Going to Carousel and delivering duck onto ground
// Parking completely in storage

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.AprilTagLocation;
import org.firstinspires.ftc.teamcode.subsystems.MarkerDetectionPipeline;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvWebcam;


@Autonomous(name="AutoBlueStorage", group="Autonomous")
public class AutonomousBlueStorage extends LinearOpMode {

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
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        //Still working on the trajectories, not final
        //Road Runner Trajectory


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-35,61.5, Math.toRadians(270));
        drive.setPoseEstimate(startPose);

        TrajectorySequence level1 = drive.trajectorySequenceBuilder(startPose)
                .forward(26.5)
                .turn(Math.toRadians(90))
                .forward(23.5)
                .build();

        TrajectorySequence level2 = drive.trajectorySequenceBuilder(startPose)
                .forward(26.5)
                .turn(Math.toRadians(90))
                .forward(23.5)
                .build();

        TrajectorySequence level3 = drive.trajectorySequenceBuilder(startPose)
                .forward(26.5)
                .turn(Math.toRadians(90))
                .forward(23.5)
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


