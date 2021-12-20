package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;


    public class Intake {

        DcMotor intakeMotor;


        public Intake (DcMotor intakeMotor ) {
            this.intakeMotor = intakeMotor;


        }
        public void In(){
            this.intakeMotor.setPower(1);
        }
        public void Off(){
            this.intakeMotor.setPower(0);
        }
        public void Out(){
            this.intakeMotor.setPower(-1);
        }


    }





