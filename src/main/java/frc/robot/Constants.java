/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //PWM
    public static final int L_DRIVE_PORT = 0;
    public static final int R_DRIVE_PORT = 1;
    public static final int L_SHOOTER_PORT = 2;
    public static final int R_SHOOTER_PORT = 3;
    public static final int INTAKE_PORT = 4;
    public static final int R_TUNNEL_PORT = 5;
    public static final int L_TUNNEL_PORT = 6;
    public static final int CHAMBER_PORT = 7;
    public static final int DIAL_SPINNER_PORT = 8;
    
    //TODO DIO
    public static final int L_ENCODER_A = 0;
    public static final int L_ENCODER_B = 1;
    public static final int R_ENCODER_A = 2;
    public static final int R_ENCODER_B = 3;

    //Controller
    public static final int CONTROLLER_PORT = 0;

    //PCM
    public static final int PCM_MODULE = 0;

    public static final int INTAKE_PISTONS_FWD_PORT = 0;
    public static final int INTAKE_PISTONS_REV_PORT = 1;
    public static final int EXTENDER_PISTONS_PORT = 2;
    public static final int CLIMB_PISTONS_FWD_PORT = 3;
    public static final int CLIMB_PISTONS_REV_PORT = 4;

    //Other
    public static final int PDP_CAN_PORT = 0;
    public static final I2C.Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;
    public static final double DEFAULT_SCALING_FACTOR = 5.0/1024.0;

    //Reverse Rotations
    public static final boolean L_ENCODER_REVERSED = false;
	public static final boolean R_ENCODER_REVERSED = false;
    public static final boolean L_SPARK_REVERSED = true;
    public static final boolean R_SPARK_REVERSED = true;
    public static final boolean GYRO_REVERSED = false;

    //Robot Speeds
    public static final double PRECISION_SPEED = 0.5;
    public static final double NORMAL_SPEED = 0.75;
    public static final double BOOST_SPEED = 1.0;

    //limelight
    public static final float LIMELIGHT_AIM_KP = -0.1f;
    public static final float LIMELIGHT_AIM_MIN_DEADBAND = 0.05f;

    //Encoder constants
    //1024 if using am-3445, 5 if using am-3314a
    public static final int ENCODER_COUNT_PER_REVOLUTION = 1024;
    //6 inch wheels
    public static final double WHEEL_DIAMETER_METERS = 0.15;
    public static final double ENCODER_DISTANCE_PER_PULSE =
        (WHEEL_DIAMETER_METERS * Math.PI) / (double) ENCODER_COUNT_PER_REVOLUTION;

    // TODO Robot characterization values
    // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
    // values for your robot.
    public static final double KS_VOLTS = 0;
    public static final double KV_VOLT_SEC_PER_METER = 0;
    public static final double KA_VOLT_SEC_SQUARE_PER_METER = 0;
    // Proportional term for drive
    public static final double KP_DRIVE_VEL = 8.5;

    // The DifferentialDriveKinematics class allows us to use the trackwidth
    // (i.e. horizontal distance between the wheels) of the robot to convert from 
    // chassis speeds to wheel speeds.
    // Physically measured track width = 0.559 m
    public static final double TRACK_WIDTH_METERS = 0.60;
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);
    
    // TODO The nominal max acceleration and max velocity for the robot during path-following.
    // The maximum velocity value should be set somewhat below the nominal free-speed of the robot.
    public static final double MAX_VEL_MPS = 3;
    public static final double MAX_ACC_MPS2 = 3;

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
    // DO NOT CHANGE THESE VALUES
    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;
}
