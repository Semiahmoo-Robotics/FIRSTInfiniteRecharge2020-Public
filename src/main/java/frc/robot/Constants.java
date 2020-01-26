/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static final int L_LAUNCHER_PORT = 0;
    public static final int R_LAUNCHER_PORT = 1;
    
    //DIO
    public static final int L_ENCODER_A = 0;
    public static final int L_ENCODER_B = 1;
    public static final int R_ENCODER_A = 2;
    public static final int R_ENCODER_B = 3;

    //Controller
    public static final int CONTROLLER_PORT = 0;

    //Other
	public static final int PDP_CAN_PORT = 0;

    //Reverse Rotations
    public static final boolean L_ENCODER_REVERSED = false;
	public static final boolean R_ENCODER_REVERSED = false;
    public static final boolean L_SPARK_REVERSED = true;
    public static final boolean R_SPARK_REVERSED = true;

    //Robot Speeds
    public static final double PRECISION_SPEED = 0.5;
    public static final double NORMAL_SPEED = 0.75;
    public static final double BOOST_SPEED = 1.0;

    //limelight
    public static final float LIMELIGHT_AIM_KP = -0.1f;
    public static final float LIMELIGHT_AIM_MIN_DEADBAND = 0.05f;

    //Encoder constants
    public static final double ENCODER_DISTANCE_PER_PULSE = 0;

    // Robot characterization values
    // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
    // values for your robot.
    public static final double KS_VOLTS = 0;
    public static final double KV_VOLT_SEC_PER_METER = 0;
    public static final double KA_VOLT_SEC_SQUARE_PER_METER = 0;

    //  this must be tuned for your drive!
    public static final double KP_DRIVE_VEL = 8.5;
}
