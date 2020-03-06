/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    //PWM
    public static final int L_DRIVE_PORT = 0;
    public static final int R_DRIVE_PORT = 1;
    public static final int L_SHOOTER_PORT = 2;
    public static final int R_SHOOTER_PORT = 3;
    public static final int TUNNEL_PORT = 4;
    public static final int INTAKE_PORT = 5;
    public static final int CLIMB_PORT = 6;
    
    //DIO
    public static final int L_ENCODER_A = 0;
    public static final int L_ENCODER_B = 1;
    public static final int R_ENCODER_A = 2;
    public static final int R_ENCODER_B = 3;

    //Controller
    public static final int DRIVE_CONTROLLER_PORT = 0;
    public static final int CONTROL_JOYSTICK_PORT = 1;
    public static final double TRIGGER_DEADBAND = 0.3d;
    public static final double LOGITECH_DEADBAND = 0.1d;

    //Other
	public static final int PDP_CAN_PORT = 0;

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
    public static final double AUTO_STRAIGHT_DRIVE_SPEED = 0.5;
    public static final double INTAKE_SPEED = 0.7;
    public static final double TUNNEL_SPEED = 0.75;
    public static final double TUNNEL_SHUFFLE_SPEED = 0.4;
    public static final double LAUNCHER_SPEED = 1.0;
    public static final double CLIMB_SPEED = 1.0;

    //TODO limelight
    public static final float LIMELIGHT_AIM_KP = -0.07f;
    public static final float LIMELIGHT_DISTANCE_KP = -0.07f;
    public static final float LIMELIGHT_AIM_MIN_DEADBAND = 0.01f;

    //TODO Encoder constants
    //1024 if using am-3445, 5 if using am-3314a
    public static final int ENCODER_COUNT_PER_REVOLUTION = 1024;
    //6 inch wheels
    public static final double WHEEL_DIAMETER_METERS = 0.15;
    // USE METERS
    public static final double ENCODER_DISTANCE_METERS_PER_PULSE =
    (WHEEL_DIAMETER_METERS * Math.PI) / (double) ENCODER_COUNT_PER_REVOLUTION;

    //PID Constants
    public static final double KP_ANGLE = 0.03;
    public static final double KI_ANGLE = 0;
    public static final double KD_ANGLE = 0;
    public static final double KP_DRIVE = 0.03;
    public static final double KI_DRIVE = 0;
    public static final double KD_DRIVE = 0;
	public static final double TURN_TOLERANCE_DEG = 5;
	public static final double TURN_RATE_TOL_DEGPERSEC = 2;
	public static final double TURN_TOLERANCE_METERS = 5;
}
