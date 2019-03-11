package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "ITERATIVEHolonomicDrivetrain", group = "Concept")

public class ITERATIVEHolonomicDriveREGIO extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor arm;
    DcMotor lift;
    DcMotor armtilt;

    CRServo rotatingBands;
    CRServo landerBox;

    /**
     * Constructor
     */
    public ITERATIVEHolonomicDriveREGIO() {

    }

    double standardPower = 0.7;
    boolean isUp = false;
    float spincoefficient= 0.7f;

    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //INCEPUT INIT
        //Wheelbase
        motorFrontRight = hardwareMap.dcMotor.get("dc3");
        motorFrontLeft = hardwareMap.dcMotor.get("dc0");
        motorBackLeft = hardwareMap.dcMotor.get("dc1");
        motorBackRight = hardwareMap.dcMotor.get("dc2");

        //extensions
        arm = hardwareMap.dcMotor.get("arm0");
        lift = hardwareMap.dcMotor.get("lift1");
        armtilt = hardwareMap.dcMotor.get("collectiontilt2");

        //SERVOS
        rotatingBands = hardwareMap.crservo.get("rotatingbands0");
        landerBox = hardwareMap.crservo.get("landerbox5");


        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        armtilt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


//    void goByDpad() {
//        if (gamepad1.dpad_up) {
//            motorFrontLeft.setPower(-standardPower);
//            motorBackLeft.setPower(-standardPower);
//            motorBackRight.setPower(+standardPower);
//            motorFrontRight.setPower(+standardPower);
//        }
//        if (gamepad1.dpad_down) {
//            motorFrontLeft.setPower(+standardPower);
//            motorBackLeft.setPower(+standardPower);
//            motorBackRight.setPower(-standardPower);
//            motorFrontRight.setPower(-standardPower);
//        }
//        if (gamepad1.dpad_left) {
//            motorFrontLeft.setPower(+standardPower);
//            motorBackLeft.setPower(-standardPower);
//            motorBackRight.setPower(-standardPower);
//            motorFrontRight.setPower(+standardPower);
//        }
//        if (gamepad1.dpad_right) {
//            motorFrontLeft.setPower(-standardPower);
//            motorBackLeft.setPower(+standardPower);
//            motorBackRight.setPower(+standardPower);
//            motorFrontRight.setPower(-standardPower);
//        }
//    }

    double scaleInput(double dVal) {
    double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
            0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

    // get the corresponding index for the scaleInput array.
    int index = (int) (dVal * 16.0);

    // index should be positive.
    if (index < 0) {
        index = -index;
    }

    // index cannot exceed size of array minus 1.
    if (index > 16) {
        index = 16;
    }

    // get value from the array.
    double dScale = 0.0;
    if (dVal < 0) {
        dScale = -scaleArray[index];
    } else {
        dScale = scaleArray[index];
    }

    // return scaled value.
    return dScale;
}

    void extensionControl() {
        if (gamepad1.left_bumper) {
            arm.setPower(-0.7);
        } else if (gamepad1.right_bumper) {
            arm.setPower(0.7);
        } else {
            arm.setPower(0);
        }
    }

    void liftControl() {
//        if (gamepad2.right_stick_button/*&&isUp==false*/) {
//            liftbyEncoder(true);
//        } else if (gamepad2.left_stick_button/*&&isUp==true*/) {
//            liftbyEncoder(false);
//        }
        if (gamepad2.dpad_up) {
            lift.setPower(-1);
        } else if (gamepad2.dpad_down) {
            lift.setPower(1);
        } else {
            lift.setPower(0);
        }
    }

    void landerboxControl() {
        if (gamepad2.dpad_left) {
            landerBox.setPower(-0.7);
        } else if (gamepad2.dpad_right) {
            landerBox.setPower(0.7);
        } else {
            landerBox.setPower(0);
        }
    }

    void armtiltControl() {

        if (gamepad2.left_bumper) {
            armtilt.setPower(-.7);
        } else if (gamepad2.right_bumper) {
            armtilt.setPower(.7);
        } else {
            armtilt.setPower(0);
        }
    }

    void rotatingBandsControl() {
        if (gamepad2.a) {
            rotatingBands.setPower(1);
        } else if (gamepad2.y) {
            rotatingBands.setPower(-1);
        } else if (gamepad2.b) {
            rotatingBands.setPower(0);
        }
    }

//    public void liftbyEncoder(boolean direction) {
//
//        if (direction == false) {
//            lift.setDirection(DcMotorSimple.Direction.REVERSE);
//            isUp = false;
//        }
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setPower(.5);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lift.setTargetPosition(8640);
//
////        while (lift.isBusy()) {
////            //Loop body can be empty
////        }
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        if (direction == false) {
//            lift.setDirection(DcMotorSimple.Direction.FORWARD);
//        }
//
//    }

    @Override
    public void loop() {


        //region Dpad CONTROL
        if (gamepad1.dpad_up) {
            motorFrontRight.setPower(+standardPower);
            motorFrontLeft.setPower(-standardPower);
            motorBackLeft.setPower(-standardPower);
            motorBackRight.setPower(+standardPower);
        }
        if (gamepad1.dpad_down) {
            motorFrontRight.setPower(-standardPower);
            motorFrontLeft.setPower(+standardPower);
            motorBackLeft.setPower(+standardPower);
            motorBackRight.setPower(-standardPower);
        }
        if (gamepad1.dpad_left) {
            motorFrontRight.setPower(+standardPower);
            motorFrontLeft.setPower(+standardPower);
            motorBackLeft.setPower(-standardPower);
            motorBackRight.setPower(-standardPower);
        }
        if (gamepad1.dpad_right) {
            motorFrontRight.setPower(-standardPower);
            motorFrontLeft.setPower(-standardPower);
            motorBackLeft.setPower(+standardPower);
            motorBackRight.setPower(+standardPower);
        }
        //endregion


        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;


        //formule holonomic
        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - (gamepad1RightX*spincoefficient);
        float FrontRight = gamepad1LeftY - gamepad1LeftX - (gamepad1RightX*spincoefficient);
        float BackRight = gamepad1LeftY + gamepad1LeftX - (gamepad1RightX*spincoefficient);
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - (gamepad1RightX*spincoefficient);



        //the value of motor power shall not exceed -1 or 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);


        armtiltControl();
        liftControl();
        extensionControl();
        rotatingBandsControl();
        landerboxControl();


        // write the values to the motors WITH SCALEINPUT METHOD
//        motorFrontRight.setPower(scaleInput(FrontRight));
//        motorFrontLeft.setPower(scaleInput(FrontLeft));
//        motorBackLeft.setPower(scaleInput(BackLeft));
//        motorBackRight.setPower(scaleInput(BackRight));

        motorFrontRight.setPower(scaleInput(FrontRight));
        motorFrontLeft.setPower(scaleInput(FrontLeft));
        motorBackRight.setPower(scaleInput(BackRight));
        motorBackLeft.setPower(scaleInput(BackLeft));
        /*
         * Telemetry for debugging
         */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " +
                String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));

    }

    @Override
    public void stop() {
        telemetry.addData("STOPPED","EXPECTED STOP");

    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it eafsier to drive
     * the robot more precisely at slower speeds.
     */


}