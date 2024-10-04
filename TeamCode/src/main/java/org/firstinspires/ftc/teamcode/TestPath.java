package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
@Autonomous
public class TestPath extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        waitForStart();
        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(15, 65, Math.toRadians(270)));
        Action x = drive.actionBuilder(new Pose2d(15, 65, Math.toRadians(270)))
                .lineToYConstantHeading(45)
                .turn(Math.toRadians(90))
//                .lineToX(56)
//                .turn(Math.toRadians(-90))
//                .lineToY(55)
//                .turn(Math.toRadians(-45))
//                .waitSeconds(2)
//                .turn(Math.toRadians(45))
//                .lineToY(36)
//                .waitSeconds(2)
//                .lineToY(55)
//                .turn(Math.toRadians(-45))
                .build();
        while (opModeIsActive()) {
            Actions.runBlocking((
                    new SequentialAction(
                            new Action () {
                                @Override
                                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                    telemetry.addLine("Message 1: This is working");
                                    telemetry.update();
                                    return false;
                                }
                            },
                            x,
                            new Action () {
                                @Override
                                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                    telemetry.addLine("Message 2: This is working");
                                    telemetry.update();
                                    return false;
                                }
                            })));
        }



//            telemetry.addData("x", drive.pose.position.x);
//            telemetry.addData("y", drive.pose.position.y);
//            telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
//            telemetry.update();

        }
    }

