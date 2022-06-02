// Autonomous Period
// Starting at Red wall | warehouse side 
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



@Autonomous(name="AutoRedWarehouse", group="Autonomous")
public class AutonomousRedWarehouse extends LinearOpMode {

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
