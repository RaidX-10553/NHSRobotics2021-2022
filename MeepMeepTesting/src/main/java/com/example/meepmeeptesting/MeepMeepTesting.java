package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800, 60);

        Pose2d START_POSE = new Pose2d(-35, -61.5, Math.toRadians(90));

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 13.5)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(START_POSE)
                                //put ur trajectory here
                                //for example
                                .forward(26.5)
                                .turn(Math.toRadians(90))
                                .forward(23.5)
                                .build()
                );

        Pose2d START_POSE2 = new Pose2d(-35,61.5, Math.toRadians(270));

        // You can add a second bot if you want.
        // You can comment this out as well if you only want one
        RoadRunnerBotEntity bot2 = new DefaultBotBuilder(meepMeep)
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 13)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(START_POSE2)
                                //put ur trajectory here
                                //for example
                                .forward(26.5)
                                .turn(Math.toRadians(-90))
                                .forward(23.5)
                                .build()
                );

        Pose2d START_POSE3 = new Pose2d(11.5, -61.5, Math.toRadians(90));

        RoadRunnerBotEntity bot3 = new DefaultBotBuilder(meepMeep)
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 13.5)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(START_POSE3)
                                //put ur trajectory here
                                //for example
                                .splineTo(new Vector2d(0, -39.5), Math.toRadians(-230))
                                .back(15)
                                .turn(Math.toRadians(-130))
                                .forward(30)
                                .forward(5)
                                .build()
                );

        Pose2d START_POSE4 = new Pose2d(11.5, 61.5, Math.toRadians(270));

        RoadRunnerBotEntity bot4 = new DefaultBotBuilder(meepMeep)
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 13.5)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(START_POSE4)
                                //put ur trajectory here
                                //for example
                                .splineTo(new Vector2d(0, 39.5), Math.toRadians(230))
                                .back(15)
                                .turn(Math.toRadians(130))
                                .forward(30)
                                .forward(5)
                                .build()
                );

        meepMeep
                .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setTheme(new ColorSchemeBlueDark())
                .setBackgroundAlpha(1f)
                .addEntity(bot.setDimensions(14.5, 18))
                .addEntity(bot2.setDimensions(14.5, 18)) // comment out if you are only using one bot
                .addEntity(bot3.setDimensions(14.5, 18))
                .addEntity(bot4.setDimensions(14.5, 18))
                .start();
    }
}
