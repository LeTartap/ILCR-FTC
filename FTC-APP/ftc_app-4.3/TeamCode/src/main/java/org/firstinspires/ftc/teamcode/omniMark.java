package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Souvannasouck on 11/20/18.
 */


@TeleOp(name = "modemsroti")
public class omniMark extends LinearOpMode {

    private DcMotor FS;// Motor Fata Stanga , difera dupa configuratia din hub eu le am fix in ordinea asta 1234
    private DcMotor FD;//Motor fata dreapta
    private DcMotor SD;//Spate dreapta
    private DcMotor SS;// Spate stanga
//    private DcMotor Arm;
//    private DcMotor Test;
//    private Servo Sv1;
//    private CRServo Svt;


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        FS = hardwareMap.dcMotor.get("dc0");
        FD = hardwareMap.dcMotor.get("dc3");
        SD = hardwareMap.dcMotor.get("dc2");
        SS = hardwareMap.dcMotor.get("dc1");
//        Arm = hardwareMap.dcMotor.get("Arm");
//        Test = hardwareMap.dcMotor.get("Test");
//        Sv1 = hardwareMap.servo.get("Sv1");
//        Svt = hardwareMap.crservo.get("Svt");



        //double Sv1Pos = Servo.MAX_POSITION;
        // double Tpower = -gamepad2.right_stick_y*0.3;
        // double ArmP = ;


        // Reverse one of the drive motors.
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        FS.setDirection(DcMotorSimple.Direction.REVERSE);
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        FD.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {

                // Put loop blocks here.
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.

                //---------------deplasare cu joystick normala---------------------
                FS.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x);
                SD.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x);
                FD.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x);
                SS.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x);



                //  -----deplasare stanga/ dreapta--------------------------------------------------------------------

                FS.setPower(-gamepad1.left_stick_x);
                SD.setPower(-gamepad1.left_stick_x);
                FD.setPower(-gamepad1.left_stick_x);
                SS.setPower(-gamepad1.left_stick_x);//Grija ca doua motoare sunt inversate deaia sunt toate cu minus


                //-----------------------------rotatie stanga dreapta------------------
                FS.setPower(-gamepad1.right_stick_x);
                SD.setPower(gamepad1.right_stick_x);
                FD.setPower(-gamepad1.right_stick_x);
                SS.setPower(gamepad1.right_stick_x);

                telemetry.addData("Left Pow", FS.getPower());
                telemetry.addData("Right Pow", FS.getPower());
                telemetry.update();
            }
        }
    }

    public void example() {
        FS.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}


