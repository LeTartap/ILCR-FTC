package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name = "AutoJava", group = "")

//@Disabled
public class AutoOmni extends LinearOpMode{
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
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "AWW9QST/////AAABmQteW8XAo0pNgaCx1lJdVKJZTDDuNOY0d6INToiFK3enrsIjMHnETEAjp+Xnr1G0cSZHi6XKqWxGccgCTVB2StwRTA1fxHS/IgfodmrCXBioTEnLzHjHZg1Pl3pFOhpF83DoY8Z7oP8kZHYFDAa5M2DFdqnyNGzTCo6uG8dYjD5Vb76KI7rhJwyCkOh22l8lBj4VL5wNIM1Jp3svMwafo44IFcg9Av3khzwhO+OMD8+/QPQ7Ifj9a/G4NuOTEXhFRiDBQ/9mX/cPOgkX6OARb1QwYbpM7bFLYkVUT6R3Tt5C2S6ANEuO9ciCDmDZYs94AKj3AIDDTNvexq1akGwLqwzeXvhHVx1Y3QknpxoLeNPC";

        vision = new MasterVision(parameters, hardwareMap, false, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time
        waitForStart();

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();

        while(opModeIsActive()&& plsRun){

            telemetry.addData("goldPosition was", goldPosition);// giving feedback


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
    
    public void goForward(){
        FrontLeft = -0.7f;//-
        FrontRight = 0.7f ;//+
        BackRight = 0.7f ;//+
        BackLeft = -0.7f; //-
    }
    public void goBack(){
        FrontLeft = +0.7f;//-
        FrontRight = 0.7f ;//+
        BackRight = 0.7f ;//+
        BackLeft = +0.7f; //-
    }
    public void goLeft(){

    }
    public void goRight(){

    }
}
