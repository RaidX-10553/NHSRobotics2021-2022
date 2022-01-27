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
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;

    public static double kV = 0;
    public static double kA = 0;
    public static double kStatic = 0;
    public static double kG = 0;


    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;


    public Arm(DcMotorEx armMotor) {
        this.armMotor = armMotor;

    }

    public void Level1() {
        PIDCoefficients coeffs = new PIDCoefficients(kP, kI, kD);
        PIDFController controller = new PIDFController(coeffs, kV, kA, kStatic, (x, v) -> kG);
        controller.setTargetPosition(1120);
        double correction = controller.update(armMotor.getCurrentPosition());
        this.armMotor.setPower(correction);


    }
    public void Level2() {
        PIDCoefficients coeffs = new PIDCoefficients(kP, kI, kD);
        PIDFController controller = new PIDFController(coeffs, kV, kA, kStatic, (x, v) -> kG);
        controller.setTargetPosition(2240);
        double correction = controller.update(armMotor.getCurrentPosition());
        this.armMotor.setPower(correction);
    }

    public void Level3() {
        PIDCoefficients coeffs = new PIDCoefficients(kP, kI, kD);
        PIDFController controller = new PIDFController(coeffs, kV, kA, kStatic, (x, v) -> kG);
        controller.setTargetPosition(3360);
        double correction = controller.update(armMotor.getCurrentPosition());
        this.armMotor.setPower(correction);
    }

    public void Home() {
        PIDCoefficients coeffs = new PIDCoefficients(kP, kI, kD);
        PIDFController controller = new PIDFController(coeffs, kV, kA, kStatic, (x, v) -> kG);
        controller.setTargetPosition(-armMotor.getCurrentPosition());
        double correction = controller.update(armMotor.getCurrentPosition());
        this.armMotor.setPower(correction);

    }
}
