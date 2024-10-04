package org.firstinspires.ftc.teamcode.tuning;

import android.sax.StartElementListener;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SparkFunOTOSDrive;


@Config
@Autonomous(name = "TestAuto3")
public class TestAuto3 extends LinearOpMode {

    @Override
    public void runOpMode() {

        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0,0,Math.toRadians(90)));


        // Delcare Trajectory as such
        Action TrajectoryAction1 = drive.actionBuilder(drive.pose)
                .turn(720)
                .build();

        while(!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                TrajectoryAction1
                );

        telemetry.addData("position x", drive.pose.position.x);

        telemetry.addData("position y", drive.pose.position.y);


        telemetry.addData("position theta", drive.pose.position.angleCast());

    }

}