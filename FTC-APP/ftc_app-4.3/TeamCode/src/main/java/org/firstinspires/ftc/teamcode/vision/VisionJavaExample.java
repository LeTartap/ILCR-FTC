package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name = "VisionJava", group = "")

//@Disabled
public class VisionJavaExample extends LinearOpMode{
    MasterVision vision;
    SampleRandomizedPositions goldPosition;
    private DcMotor stanga0;
    private DcMotor dreapta1;
    private CRServo s0;
    private CRServo s1;
    private CRServo s2;
    private DcMotor arm2;
    private DcMotor extension3;

    @Override
    public void runOpMode() throws InterruptedException {
        stanga0 = hardwareMap.dcMotor.get("stanga0");
        dreapta1 = hardwareMap.dcMotor.get("dreapta1");
        s0 = hardwareMap.crservo.get("s0");
        s0 = hardwareMap.crservo.get("s1");
        s0 = hardwareMap.crservo.get("s2");
        arm2 = hardwareMap.dcMotor.get("arm2");
        extension3 =hardwareMap.dcMotor.get("extension3");
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "AWW9QST/////AAABmQteW8XAo0pNgaCx1lJdVKJZTDDuNOY0d6INToiFK3enrsIjMHnETEAjp+Xnr1G0cSZHi6XKqWxGccgCTVB2StwRTA1fxHS/IgfodmrCXBioTEnLzHjHZg1Pl3pFOhpF83DoY8Z7oP8kZHYFDAa5M2DFdqnyNGzTCo6uG8dYjD5Vb76KI7rhJwyCkOh22l8lBj4VL5wNIM1Jp3svMwafo44IFcg9Av3khzwhO+OMD8+/QPQ7Ifj9a/G4NuOTEXhFRiDBQ/9mX/cPOgkX6OARb1QwYbpM7bFLYkVUT6R3Tt5C2S6ANEuO9ciCDmDZYs94AKj3AIDDTNvexq1akGwLqwzeXvhHVx1Y3QknpxoLeNPC";

        vision = new MasterVision(parameters, hardwareMap, false, MasterVision.TFLiteAlgorithm.INFER_NONE);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time
        stanga0.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();

        while(opModeIsActive()){
            telemetry.addData("goldPosition was", goldPosition);// giving feedback
//            extension3.setPower(-1);
//            sleep(3350);
            extension3.setPower(0);
            switch (goldPosition){ // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    stanga0.setPower(-0.2);
                    dreapta1.setPower(0.2);
                    sleep(700);
                    stanga0.setPower(0.5);
                    dreapta1.setPower(0.5);
                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    stanga0.setPower(0.5);
                    dreapta1.setPower(0.5);
                    sleep(800);
                    stanga0.setPower(0);
                    dreapta1.setPower(0);
                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    stanga0.setPower(0.2);
                    dreapta1.setPower(-0.2);
                    sleep(700);
                    stanga0.setPower(0.5);
                    dreapta1.setPower(0.5);
                    sleep(800);
                    stanga0.setPower(0);
                    dreapta1.setPower(0);
                    break;
                case UNKNOWN:
                    telemetry.addLine("staying put");
                    stanga0.setPower(0.2);
                    dreapta1.setPower(-0.2);
                    sleep(700);
                    stanga0.setPower(0.5);
                    dreapta1.setPower(0.5);
                    sleep(700);
                    stanga0.setPower(0);
                    dreapta1.setPower(0);

                    break;
            }
            stanga0.setPower(0);
            dreapta1.setPower(0);

            telemetry.update();
        }

        vision.shutdown();
    }
}
