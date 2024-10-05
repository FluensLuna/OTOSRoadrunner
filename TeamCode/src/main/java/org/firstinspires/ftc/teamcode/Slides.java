package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
@TeleOp
public class Slides extends OpMode {
    private PIDController pidController;
    public static double p = 0.005, i = 0.01, d = 0.001, f = .1;
    public static int target = 0;

    private DcMotorEx slideMotor;


    @Override
    public void init(){
        pidController = new PIDController(p,i,d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop(){
        pidController.setPID(p,i,d);
        int slidePose = slideMotor.getCurrentPosition();
        double pid = pidController.calculate(slidePose, target);
        double power = pid + f;

        slideMotor.setPower(power);

        telemetry.addData("slidePose: ", slidePose);
        telemetry.addData("target: ", target);
        telemetry.addData("pid: ", pid);
        telemetry.addData("power: ", power);
        telemetry.addData("current: ", slideMotor.getCurrent(CurrentUnit.AMPS));
        telemetry.update();
    }

}
