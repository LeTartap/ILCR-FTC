/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "LINEARHolonomicDriveREGIO", group = "Linear Opmode")
public class LINEARHolonomicDriveREGIO extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor arm;
    DcMotor lift;
    DcMotor armtilt;
    CRServo rotatingBands;
    CRServo landerBox;
    double standardPower = 0.7;
    boolean isUp = false;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

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
        if (gamepad2.right_stick_button/*&&isUp==false*/) {
            liftbyEncoder(true);
        } else if (gamepad2.left_stick_button/*&&isUp==true*/) {
            liftbyEncoder(false);
        }
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
            armtilt.setPower(-.5);
        } else if (gamepad2.right_bumper) {
            armtilt.setPower(.5);
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

    public void liftbyEncoder(boolean direction) {
        int targetpos = 8640;

        if (direction == false) {
            lift.setDirection(DcMotorSimple.Direction.REVERSE);
            isUp = false;
        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(1);
        lift.setTargetPosition(targetpos);
        while (lift.isBusy()) {
            //Loop body can be empty
        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (direction == false) {
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
        }

    }


    @Override
    public void runOpMode() {
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
        //SFARIST INIT

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // left stick controls direction
            // right stick X controls rotation


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


            float gamepad1LeftY = -gamepad1.left_stick_y;
            float gamepad1LeftX = gamepad1.left_stick_x;
            float gamepad1RightX = gamepad1.right_stick_x;

            //holonomic formulas
            float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;


            // clip the right/left values so that the values never exceed +/- 1
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
            motorBackLeft.setPower(scaleInput(BackLeft));
            motorBackRight.setPower(scaleInput(BackRight));


            /*
             * Telemetry for debugging
             */
            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("Joy XL YL XR", String.format("%.2f", gamepad1LeftX) + " " +
                    String.format("%.2f", gamepad1LeftY) + " " + String.format("%.2f", gamepad1RightX));
            telemetry.addData("f left pwr", "front left  pwr: " + String.format("%.2f", FrontLeft));
            telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
            telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
            telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));
        }
    }
}
