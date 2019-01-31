package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "JAVA3001cornelia_7_ian_POVDRIVE_1placa (Blocks to Java)", group = "")
public class JAVA3001cornelia_7_ian_POVDRIVE_1placa extends LinearOpMode {

  private DcMotor stanga0;
  private DcMotor dreapta1;
  private Servo s0;
  private DcMotor arm2;
  private DcMotor extension3;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    stanga0 = hardwareMap.dcMotor.get("stanga0");
    dreapta1 = hardwareMap.dcMotor.get("dreapta1");
    s0 = hardwareMap.servo.get("s0");
    arm2 = hardwareMap.dcMotor.get("arm2");
    extension3 =hardwareMap.dcMotor.get("extension3");

    // Reverse one of the drive motors.
    // You will have to determine which motor to reverse for your robot.
    // In this example, the right motor was reversed so that positive
    // applied power makes it move the robot in the forward direction.
    stanga0.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        // Use left stick to drive and right stick to turn
        // The Y axis of a joystick ranges from -1 in its topmost position
        // to +1 in its bottommost position. We negate this value so that
        // the topmost position corresponds to maximum forward power.
        stanga0.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x);
        dreapta1.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x);
        while (gamepad1.right_bumper) {
          s0.setPosition(1);
        }
        while (gamepad1.left_bumper) {
          s0.setPosition(-1);
        }
        while (gamepad1.dpad_up) {
          arm2.setPower(-0.5);
        }
        while (gamepad1.dpad_down) {
          arm2.setPower(0.5);
        }
        while (gamepad1.x) {
          arm2.setPower(-1);
        }
        while (gamepad1.y) {
          arm2.setPower(1);
        }
        while(gamepad1.dpad_left){
          extension3.setPower(1);
        }
        while(gamepad1.dpad_right){
          extension3.setPower(-1);
        }

        arm2.setPower(0);
        extension3.setPower(0);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // s0.setPosition(0);
        telemetry.addData("Left Pow", stanga0.getPower());
        telemetry.addData("Right Pow", dreapta1.getPower());
        telemetry.update();
      }
    }
  }
}
