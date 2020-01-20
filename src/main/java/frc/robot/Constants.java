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
    public static final boolean R_SPARK_REVERSED = false;

}
