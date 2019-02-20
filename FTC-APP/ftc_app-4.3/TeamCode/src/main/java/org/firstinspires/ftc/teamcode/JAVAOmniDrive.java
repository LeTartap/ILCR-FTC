package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.sun.tools.javac.util.FatalError;

@TeleOp(name = "JavaOmniDrive", group = "")
public class JAVAOmniDrive extends LinearOpMode{

    private DcMotor dc0stangafata;
    private DcMotor dc1stangaspate;
    private DcMotor dc2dreaptaspate;
    private DcMotor dc3dreaptafata;



    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double fataSpate;
        double stangaDreapta;
        double compusFataSpate;
        double compusStangaDreapta;
        double standardPower=1;

        dc0stangafata = hardwareMap.dcMotor.get("dc0");//fata stanga
        dc1stangaspate = hardwareMap.dcMotor.get("dc1");//spate stanga
        dc2dreaptaspate = hardwareMap.dcMotor.get("dc2");//spate dreapta
        dc3dreaptafata = hardwareMap.dcMotor.get("dc3");//fata dreapta
        // Reverse one of the drive motors.
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
//        dc0stangafata.setDirection(DcMotorSimple.Direction.REVERSE);
//        dc1stangaspate.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                fataSpate = -gamepad1.left_stick_y;
                stangaDreapta = -gamepad1.left_stick_x;
                compusFataSpate =fataSpate+stangaDreapta;


                // Put loop blocks here.
                // Use left stick to drive and right stick to turn
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.

                // pe axa Y, negativ in sus, pozitiv in jos
                // pe axa X, negatic la stanga, pozitiv la dreapta
                dc0stangafata.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x);
                dc1stangaspate.setPower(+gamepad1.left_stick_y-gamepad1.left_stick_x);
                dc2dreaptaspate.setPower(+gamepad1.left_stick_y-gamepad1.left_stick_x);
                dc3dreaptafata.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x);
                dc0stangafata.setPower(stangaDreapta);
                dc1stangaspate.setPower(-fataSpate);
                dc2dreaptaspate.setPower(-stangaDreapta);
                dc3dreaptafata.setPower(fataSpate);



                if(gamepad1.dpad_up)
                {
                    dc0stangafata.setPower(+standardPower);
                    dc1stangaspate.setPower(-standardPower);
                    dc2dreaptaspate.setPower(-standardPower);
                    dc3dreaptafata.setPower(+standardPower);
                }
                if(gamepad1.dpad_down)
                {
                    dc0stangafata.setPower(-standardPower);
                    dc1stangaspate.setPower(+standardPower);
                    dc2dreaptaspate.setPower(+standardPower);
                    dc3dreaptafata.setPower(-standardPower);
                }
                if(gamepad1.dpad_left)
                {
                    dc0stangafata.setPower(-standardPower);
                    dc1stangaspate.setPower(+standardPower);
                    dc2dreaptaspate.setPower(-standardPower);
                    dc3dreaptafata.setPower(+standardPower);
                }
                if(gamepad1.dpad_right)
                {
                    dc0stangafata.setPower(+standardPower);
                    dc1stangaspate.setPower(-standardPower);
                    dc2dreaptaspate.setPower(+standardPower);
                    dc3dreaptafata.setPower(-standardPower);
                }



                telemetry.addData("left stick x  value",gamepad1.left_stick_x);
                telemetry.addData("left stick y value",gamepad1.left_stick_y);
                telemetry.addData("right stick x value",gamepad1.right_stick_x);
                telemetry.addData("left stick y value",gamepad1.right_stick_y);
                telemetry.addData("dc0stangafata Pow", dc0stangafata.getPower());
                telemetry.addData("dc1stangaspate Pow", dc1stangaspate.getPower());
                telemetry.addData("dc2 Pow", dc2dreaptaspate.getPower());
                telemetry.addData("dc3dreaptafata Pow", dc3dreaptafata.getPower());
                telemetry.update();
            }
        }
    }
}
