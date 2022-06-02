// Autonomous Period
// Starting at Red wall | Storage side 
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



@Autonomous(name="AutoRedStorage", group="Autonomous")
public class AutonomousRedStorage extends LinearOpMode {

    /* Declare OpMode members. */




    @Override
    public void runOpMode() {

        // Hardware map here








        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("","AUTO TIME");
        telemetry.update();
        //CODE HERE ENCODERS




        while (opModeIsActive()) {



        }
    }
}
