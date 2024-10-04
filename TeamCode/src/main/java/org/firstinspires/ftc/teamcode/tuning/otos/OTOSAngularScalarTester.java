package org.firstinspires.ftc.teamcode.tuning.otos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
@Autonomous
public class OTOSAngularScalarTester extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0,0,0));

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking((
                    drive.actionBuilder(new Pose2d(15, 65, Math.toRadians(270)))
                            .lineToYConstantHeading(45)
                            .turn(Math.toRadians(90))
                            .lineToX(56)
                            .turn(Math.toRadians(-90))
                            .lineToY(55)
                            .turn(Math.toRadians(-45))
                            .waitSeconds(2)
                            .turn(Math.toRadians(45))
                            .lineToY(36)
                            .waitSeconds(2)
                            .lineToY(55)
                            .turn(Math.toRadians(-45))
                            .build()));

            telemetry.addData("x", drive.pose.position.x);
            telemetry.addData("y", drive.pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
            telemetry.update();

        }
    }
}
