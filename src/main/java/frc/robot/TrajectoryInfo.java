package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

/**
 * This class includes all of the possible trajectories for the autonomous
 * program.
 */
public final class TrajectoryInfo {

    // Create a voltage constraint to ensure we don't accelerate too fast
    private static final DifferentialDriveVoltageConstraint autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.KS_VOLTS,
        Constants.KV_VOLT_SEC_PER_METER, Constants.KA_VOLT_SEC_SQUARE_PER_METER), Constants.DRIVE_KINEMATICS, 10);

    // Create config for trajectory
    private static final TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_VEL_MPS, Constants.MAX_ACC_MPS2)
        .setKinematics(Constants.DRIVE_KINEMATICS).addConstraint(autoVoltageConstraint);

    // Waypoints
    /* Origin is the center of the robot when the center of the robot is placed
        on the middle of the initiation line. The axis flips when the robot is placed on
        different alliances.*/
    // +x is towards the center of the field.
    // +y is to the left of the robot.
    // Distances are in METERS, Angles are in DEGREES
    private final static Pose2d poseOrigin = new Pose2d(0, 0, new Rotation2d(0));
    public static final Pose2d kCenterStartPose = new Pose2d(0.0, -4.0, Rotation2d.fromDegrees(180.0));
    public static final Pose2d kSimpleSwitchStartPose = new Pose2d(0.0, -2.0, Rotation2d.fromDegrees(180.0));
    public static final Pose2d kRightSwitchPose = new Pose2d(new Translation2d(100.0, -60.0), Rotation2d.fromDegrees(0.0 + 180.0));
    public static final Pose2d kLeftSwitchPose = new Pose2d(new Translation2d(100.0, 60.0), Rotation2d.fromDegrees(0.0 + 180.0));

    /**
     * No instantiation.
     */
    private TrajectoryInfo() {
    }

    public static Trajectory driveForward() {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
            List.of(new Translation2d(1, 0));
            new Pose2d(2, 0, new Rotation2d(0)),
            config);
    }

    public static Trajectory simple3BallStn1() {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
                // TODO set waypoints to travel through
                List.of(
                    new Translation2d(1, 1),
                    new Translation2d(2, -1)
                ),
                // Ending point: TODO set ending point and direction facing.
                new Pose2d(3, 0, new Rotation2d(0)),
                config);
        return t;
    }

    public static Trajectory simple3BallStn2() {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
                // TODO set waypoints to travel through
                List.of(
                    new Translation2d(1, 1),
                    new Translation2d(2, -1)
                ),
                // Ending point: TODO set ending point and direction facing.
                new Pose2d(3, 0, new Rotation2d(0)),
                config);
        return t;
    }

    public static Trajectory simple3BallStn3(TrajectoryConfig config) {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
                // TODO set waypoints to travel through
                List.of(
                    new Translation2d(1, 1),
                    new Translation2d(2, -1)
                ),
                // Ending point: TODO set ending point and direction facing.
                new Pose2d(3, 0, new Rotation2d(0)),
                config);
        return t;
    }

    public static Trajectory complex10BallStation1(TrajectoryConfig config) {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
                // TODO set waypoints to travel through
                List.of(
                    new Translation2d(1, 1),
                    new Translation2d(2, -1)
                ),
                // Ending point: TODO set ending point and direction facing.
                new Pose2d(3, 0, new Rotation2d(0)),
                config);
        return t;
    }

    public static Trajectory complex5BallStation3(TrajectoryConfig config) {
        Trajectory t = TrajectoryGenerator.generateTrajectory(
            poseOrigin,
                // TODO set waypoints to travel through
                List.of(
                    new Translation2d(1, 1),
                    new Translation2d(2, -1)
                ),
                // Ending point: TODO set ending point and direction facing.
                new Pose2d(3, 0, new Rotation2d(0)),
                config);
        return t;
    }

}
