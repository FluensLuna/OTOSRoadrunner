package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SparkFunOTOSDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
@TeleOp(name="therealspline")
public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        waitForStart();
        Pose2d beginPose = new Pose2d(0, 0, 0);

        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
            Action TrajectionAction1 = drive.actionBuilder(beginPose)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .build();
            Actions.runBlocking(TrajectionAction1);

        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, beginPose);


            Action TrajectionAction1 = drive.actionBuilder(beginPose)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .build();
            Actions.runBlocking(TrajectionAction1);
        } else if (TuningOpModes.DRIVE_CLASS.equals(SparkFunOTOSDrive.class)) {
            SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, beginPose);
            Action TrajectionAction1 = drive.actionBuilder(beginPose)
                    .splineTo(new Vector2d(4, 4), Math.PI / 2)
                    .splineTo(new Vector2d(6, 8), Math.PI)
                    .build();
            while (opModeIsActive()) {
                Actions.runBlocking(TrajectionAction1);

            }
        }
        else {
            throw new RuntimeException();
        }
    }
}
