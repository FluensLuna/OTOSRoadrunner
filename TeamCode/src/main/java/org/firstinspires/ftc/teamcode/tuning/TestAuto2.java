package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.MecanumDrive.FollowTrajectoryAction;


@Config
@Autonomous(name = "TestAuto")
public class TestAuto2 extends LinearOpMode {

    @Override
    public void runOpMode() {

        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0,0,0));


        // Delcare Trajectory as such
        Action TrajectoryAction1 = drive.actionBuilder(drive.pose)
                .lineToX(10)
                .turn(Math.toRadians(180))
                .build();

        Action TrajectoryAction2 = drive.actionBuilder(new Pose2d(15,20,0))
                .lineToX(0)
                .build();


        while(!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        TrajectoryAction1, // Example of a drive action

                        // This action and the following action do the same thing
                        new Action() {
                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        },
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        new ParallelAction( // several actions being run in parallel
                                TrajectoryAction2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action
                                    return false;
                                }
                        )
//                        drive.actionBuilder(new Pose2d(15,10,Math.toRadians(125))) // Another way of running a trajectory (not recommended because trajectories take time to build and will slow down your code, always try to build them beforehand)
//                                .splineTo(new Vector2d(25, 15), 0)
//                                .build()

                )
        );


    }

}