package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;


public class Arm {
 
    DcMotorEx armMotor;
    public static double kP = -0.025;
    public static double kI = 0;
    public static double kD = -0.001;

    public static double kV = 0;
    public static double kA = 0;
    public static double kStatic = 0;
    public static double kG = 0.25;


    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    public Arm(DcMotorEx armMotor) {
        this.armMotor = armMotor;
        this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.armMotor.setDirection(DcMotor.Direction.REVERSE);
        this.armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        PIDCoefficients coeffs = new PIDCoefficients(kP, kI, kD);
        PIDFController controller = new PIDFController(coeffs, kV, kA, kStatic, (x, v) -> kG);

    }

    public void Level1() {
        controller.setTargetPosition(1120);
        double correction = controller.update(armMotor.getCurrentPosition());
        this.armMotor.setPower(correction);


    }
    public void Level2() {
        this.armMotor.setTargetPosition(2240);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }

    public void Level3() {
        this.armMotor.setTargetPosition(3360);
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
    }

    public void Home() {
        this.armMotor.setTargetPosition(-armMotor.getCurrentPosition());
        this.armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.armMotor.setVelocity(1400);
        this.armMotor.setTargetPosition(0);

    }
}
