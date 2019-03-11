package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.vuforia.CameraDevice;
import com.vuforia.ImageTargetBuilder;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.vuforia.CameraDevice.MODE.MODE_OPTIMIZE_QUALITY;
import static com.vuforia.CameraDevice.MODE.MODE_OPTIMIZE_SPEED;

@Autonomous(name = "crater_AutoOmniRegionale", group = "")

//@Disabled
public class CRATER_AutoOmniREGIO extends LinearOpMode {
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


    private boolean plsRun = false;
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
    int ticksPerCm =43;
    double powerInAuto = 0.50f;


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

        plsRun=true;
        while (opModeIsActive() && plsRun) {

            telemetry.addData("goldPosition was", goldPosition);// giving feedback
            telemetry.update();




//            liftbyEncoder(true);
//            sleep(1000);
//            liftbyEncoder(false);


//            goForward(50* ticksPerCm);
//            sleep(1000);
//            goBack(20*ticksPerCm);
//            sleep(1000);
//            goLeft(50*ticksPerCm);
//            sleep(1000);
//            spinRight(10*ticksPerCm);
//            sleep(1000);
//            goForward(100*ticksPerCm);
//            sleep(1000);
//            goBack(200*ticksPerCm);
//            sleep(1000);






            switch (goldPosition) { // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    goForward(25*ticksPerCm);
                    sleep(500);
                    goLeft(33*ticksPerCm);
                    sleep(300);
                    goForward(21*ticksPerCm);
                    goBack(21*ticksPerCm);
                    goRight(33*ticksPerCm);

                    goLeft(79*ticksPerCm);
                    sleep(500);

                    spinLeft(42*ticksPerCm);
                    sleep(500);

                    goForward(60*ticksPerCm);
                    sleep(500);



                    armtilt.setPower(-.4);
                    sleep(1500);
                    armtilt.setPower(0);
                    rotatingBands.setPower(1);
                    sleep(1000);
                    rotatingBands.setPower(0);
                    sleep(300);
                    armtilt.setPower(+.9);
                    sleep(1200);
                    armtilt.setPower(0);

                    goBack(87*ticksPerCm);
                    spinLeft(56*ticksPerCm);
                    goForward(10*ticksPerCm);
                    armtilt.setPower(-.6);
                    sleep(1000);
                    armtilt.setPower(0);

                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    goForward(50*ticksPerCm);
                    sleep(500);

                    goBack(25*ticksPerCm);
                    sleep(500);

                    goLeft(77*ticksPerCm);
                    sleep(500);

                    spinLeft(42*ticksPerCm);
                    sleep(500);

                    goForward(60*ticksPerCm);
                    sleep(500);



                    armtilt.setPower(-.4);
                    sleep(1500);
                    armtilt.setPower(0);

                    rotatingBands.setPower(1);
                    sleep(1000);
                    rotatingBands.setPower(0);

                    sleep(300);

                    armtilt.setPower(+.9);
                    sleep(1200);
                    armtilt.setPower(0);

                    goBack(87*ticksPerCm);
                    spinLeft(56*ticksPerCm);
                    goForward(10*ticksPerCm);
                    armtilt.setPower(-.6);
                    sleep(1000);
                    armtilt.setPower(0);

                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    goForward(25*ticksPerCm);
                    sleep(300);
                    goRight(33*ticksPerCm);
                    sleep(300);
                    goForward(19*ticksPerCm);
                    goBack(19*ticksPerCm);
                    goLeft(33*ticksPerCm);

                    goLeft(77*ticksPerCm);
                    sleep(500);

                    spinLeft(42*ticksPerCm);
                    sleep(500);

                    goForward(60*ticksPerCm);
                    sleep(500);



                    armtilt.setPower(-.4);
                    sleep(1500);
                    armtilt.setPower(0);

                    rotatingBands.setPower(1);
                    sleep(1000);
                    rotatingBands.setPower(0);
                    sleep(300);

                    armtilt.setPower(+.9);
                    sleep(1200);
                    armtilt.setPower(0);

                    goBack(87*ticksPerCm);

                    spinLeft(56*ticksPerCm);

                    goForward(10*ticksPerCm);

                    armtilt.setPower(-.6);
                    sleep(1000);
                    armtilt.setPower(0);


                    break;
                case UNKNOWN:
                    telemetry.addLine("UNKNOWN - going left");
                    goForward(25*ticksPerCm);
                    sleep(500);
                    goLeft(33*ticksPerCm);
                    sleep(300);
                    goForward(21*ticksPerCm);
                    goBack(21*ticksPerCm);
                    goRight(33*ticksPerCm);

                    goLeft(79*ticksPerCm);
                    sleep(500);

                    spinLeft(42*ticksPerCm);
                    sleep(500);

                    goForward(60*ticksPerCm);
                    sleep(500);



                    armtilt.setPower(-.4);
                    sleep(1500);
                    armtilt.setPower(0);
                    rotatingBands.setPower(1);
                    sleep(1000);
                    rotatingBands.setPower(0);
                    sleep(300);
                    armtilt.setPower(+.9);
                    sleep(1200);
                    armtilt.setPower(0);

                    goBack(87*ticksPerCm);
                    spinLeft(56*ticksPerCm);
                    goForward(10*ticksPerCm);
                    armtilt.setPower(-.6);
                    sleep(1000);
                    armtilt.setPower(0);

                    break;
            }
            telemetry.update();
            plsRun = false;
        }
        vision.shutdown();
    }

    public void liftbyEncoder(boolean direction) {
        int targetpos = 8640;

        if (direction == true) {
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


        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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

        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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

        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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

        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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

    public void spinLeft(int targetpos){
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//        motorFrontRight.setPower(-standardPower);//-
//        motorFrontLeft.setPower(-standardPower);//-
//        motorBackLeft.setPower(+standardPower);//+
//        motorBackRight.setPower(+standardPower);//+

        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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


    }

    public void spinRight(int targetpos){
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//        motorFrontRight.setPower(-standardPower);//-
//        motorFrontLeft.setPower(-standardPower);//-
//        motorBackLeft.setPower(+standardPower);//+
//        motorBackRight.setPower(+standardPower);//+

        motorFrontRight.setPower(powerInAuto);
        motorFrontLeft.setPower(powerInAuto);
        motorBackLeft.setPower(powerInAuto);
        motorBackRight.setPower(powerInAuto);

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
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);



    }



}
