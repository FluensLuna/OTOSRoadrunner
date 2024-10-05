package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class Slides extends OpMode {
    private PIDController pidController;
    public static double p = 0, i = 0, d = 0, f = 0;
    public static int target = 0;

    private DcMotorEx slideMotor;


    @Override
    public void init(){
        pidController = new PIDController(p,i,d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");
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
        telemetry.update();
    }

}
