package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "JavaOmniDrive", group = "")
public class JAVAOmniDrive extends LinearOpMode{

    private DcMotor dc0;
    private DcMotor dc1;
    private DcMotor dc2;
    private DcMotor dc3;


    private double standardPower=1;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        dc0 = hardwareMap.dcMotor.get("dc0");
        dc1 = hardwareMap.dcMotor.get("dc1");
        dc2 = hardwareMap.dcMotor.get("dc2");
        dc3 = hardwareMap.dcMotor.get("dc3");

        // Reverse one of the drive motors.
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
//        dc0.setDirection(DcMotorSimple.Direction.REVERSE);
//        dc1.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // Use left stick to drive and right stick to turn
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.
                dc0.setPower(-gamepad1.left_stick_y);
                dc1.setPower(+gamepad1.left_stick_y);
                dc2.setPower(+gamepad1.left_stick_y);
                dc3.setPower(-gamepad1.left_stick_y);

                if(gamepad1.dpad_up)
                {
                    dc0.setPower(+standardPower);
                    dc1.setPower(-standardPower);
                    dc2.setPower(-standardPower);
                    dc3.setPower(+standardPower);
                }
                if(gamepad1.dpad_down)
                {
                    dc0.setPower(-standardPower);
                    dc1.setPower(+standardPower);
                    dc2.setPower(+standardPower);
                    dc3.setPower(-standardPower);
                }
                if(gamepad1.dpad_left)
                {
                    dc0.setPower(-standardPower);
                    dc1.setPower(+standardPower);
                    dc2.setPower(-standardPower);
                    dc3.setPower(+standardPower);
                }
                if(gamepad1.dpad_right)
                {
                    dc0.setPower(+standardPower);
                    dc1.setPower(-standardPower);
                    dc2.setPower(+standardPower);
                    dc3.setPower(-standardPower);
                }




                telemetry.addData("dc0 Pow", dc0.getPower());
                telemetry.addData("dc1 Pow", dc1.getPower());
                telemetry.addData("dc2 Pow", dc2.getPower());
                telemetry.addData("dc3 Pow", dc3.getPower());
                telemetry.update();
            }
        }
    }
}
