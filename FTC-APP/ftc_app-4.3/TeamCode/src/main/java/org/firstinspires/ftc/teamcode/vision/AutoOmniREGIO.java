package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name = "AutoOmniRegionale", group = "")

//@Disabled
public class AutoOmniREGIO extends LinearOpMode {
    MasterVision vision;
    SampleRandomizedPositions goldPosition;
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor arm;
    DcMotor lift;
    DcMotor armtilt;

    CRServo rotatingBands;
    CRServo landerBox;


    private boolean plsRun =true;
    //holonomic formulas

//    float FrontLeft = -gamepad1LeftY - gamepad1LeftX ;//-
//    float FrontRight = gamepad1LeftY - gamepad1LeftX ;//+
//    float BackRight = gamepad1LeftY + gamepad1LeftX ;//+
//    float BackLeft = -gamepad1LeftY + gamepad1LeftX //-

    float FrontLeft;
    float FrontRight;
    float BackRight;
    float BackLeft;

    double standardPower;
    boolean isUp = false;

    @Override
    public void runOpMode() throws InterruptedException {
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





        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;// recommended camera direction
        parameters.vuforiaLicenseKey = "AWW9QST/////AAABmQteW8XAo0pNgaCx1lJdVKJZTDDuNOY0d6INToiFK3enrsIjMHnETEAjp+Xnr1G0cSZHi6XKqWxGccgCTVB2StwRTA1fxHS/IgfodmrCXBioTEnLzHjHZg1Pl3pFOhpF83DoY8Z7oP8kZHYFDAa5M2DFdqnyNGzTCo6uG8dYjD5Vb76KI7rhJwyCkOh22l8lBj4VL5wNIM1Jp3svMwafo44IFcg9Av3khzwhO+OMD8+/QPQ7Ifj9a/G4NuOTEXhFRiDBQ/9mX/cPOgkX6OARb1QwYbpM7bFLYkVUT6R3Tt5C2S6ANEuO9ciCDmDZYs94AKj3AIDDTNvexq1akGwLqwzeXvhHVx1Y3QknpxoLeNPC";

        vision = new MasterVision(parameters, hardwareMap, false, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time
        waitForStart();

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();


        while(opModeIsActive()&& plsRun){

            telemetry.addData("goldPosition was", goldPosition);// giving feedback
            telemetry.update();

            switch (goldPosition){ // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    break;
                case UNKNOWN:
                    telemetry.addLine("UNKNOWN, guessing it's straight ahead");
                    break;
            }
            telemetry.update();
            plsRun =false;
        }
        vision.shutdown();
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

    public void goForward(int targetpos) {
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // REFFERENCE
//        motorFrontRight.setPower(+standardPower);//+
//        motorFrontLeft.setPower(-standardPower);//-
//        motorBackLeft.setPower(-standardPower);//-
//        motorBackRight.setPower(+standardPower);//+


        motorFrontRight.setPower(.8);
        motorFrontLeft.setPower(.8);
        motorBackLeft.setPower(.8);
        motorBackRight.setPower(.8);

        motorFrontRight.setTargetPosition(targetpos);
        motorFrontLeft.setTargetPosition(targetpos);
        motorBackLeft.setTargetPosition(targetpos);
        motorBackRight.setTargetPosition(targetpos);
        while (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy() && motorBackRight.isBusy()) {
            //Loop body can be empty
        }


        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    public void goBack(int targetpos) {
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//        motorFrontRight.setPower(-standardPower);//-
//        motorFrontLeft.setPower(+standardPower);//+
//        motorBackLeft.setPower(+standardPower);//+
//        motorBackRight.setPower(-standardPower);//-

        motorFrontRight.setPower(.8);
        motorFrontLeft.setPower(.8);
        motorBackLeft.setPower(.8);
        motorBackRight.setPower(.8);

        motorFrontRight.setTargetPosition(targetpos);
        motorFrontLeft.setTargetPosition(targetpos);
        motorBackLeft.setTargetPosition(targetpos);
        motorBackRight.setTargetPosition(targetpos);
        while (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy() && motorBackRight.isBusy()) {
            //Loop body can be empty
        }

        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void goLeft(int targetpos) {
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        motorFrontRight.setPower(+standardPower);//+
//        motorFrontLeft.setPower(+standardPower);//+
//        motorBackLeft.setPower(-standardPower);//-
//        motorBackRight.setPower(-standardPower);//-

        motorFrontRight.setPower(.8);
        motorFrontLeft.setPower(.8);
        motorBackLeft.setPower(.8);
        motorBackRight.setPower(.8);

        motorFrontRight.setTargetPosition(targetpos);
        motorFrontLeft.setTargetPosition(targetpos);
        motorBackLeft.setTargetPosition(targetpos);
        motorBackRight.setTargetPosition(targetpos);
        while (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy() && motorBackRight.isBusy()) {
            //Loop body can be empty
        }

        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void goRight(int targetpos) {
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//        motorFrontRight.setPower(-standardPower);//-
//        motorFrontLeft.setPower(-standardPower);//-
//        motorBackLeft.setPower(+standardPower);//+
//        motorBackRight.setPower(+standardPower);//+

        motorFrontRight.setPower(.8);
        motorFrontLeft.setPower(.8);
        motorBackLeft.setPower(.8);
        motorBackRight.setPower(.8);

        motorFrontRight.setTargetPosition(targetpos);
        motorFrontLeft.setTargetPosition(targetpos);
        motorBackLeft.setTargetPosition(targetpos);
        motorBackRight.setTargetPosition(targetpos);
        while (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy() && motorBackRight.isBusy()) {
            //Loop body can be empty
        }

        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void spinRight() {

    }

}
