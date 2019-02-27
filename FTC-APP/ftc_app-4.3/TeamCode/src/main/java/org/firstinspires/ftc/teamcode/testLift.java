package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "testLift", group = "")
public class testLift extends LinearOpMode{

    private DcMotor dc0stangafata;



    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        dc0stangafata = hardwareMap.dcMotor.get("dc0");//fata stanga

        // reset encoder count kept by left motor.
        dc0stangafata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dc0stangafata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();
        if (opModeIsActive()) {
            int position = dc0stangafata.getCurrentPosition();
            // Put run blocks here.
            while (opModeIsActive()) {
                if(gamepad1.left_bumper)
                {
                    dc0stangafata.setPower(.5);
                }
                if(gamepad1.right_bumper)
                {
                    dc0stangafata.setPower(-.5);
                }
                dc0stangafata.setPower(0);

                if(gamepad1.a)
                {   dc0stangafata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    dc0stangafata.setTargetPosition(1000);
                }
                // set left motor to run to target encoder position and stop with brakes on.
                //dc0stangafata.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//                dc0stangafata.setTargetPosition(position);
//                if(gamepad1.a){
//                    dc0stangafata.setPower(.25);
//                }


                telemetry.addData("Encoder Position", position);
                telemetry.update();
            }
        }
    }
}
