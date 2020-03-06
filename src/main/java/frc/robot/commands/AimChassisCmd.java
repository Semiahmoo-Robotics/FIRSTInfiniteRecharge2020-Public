/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class AimChassisCmd extends CommandBase {

  private final DriveSstm m_driveSstm;
  DoubleSupplier tx = () -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  DoubleSupplier ty = () -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);

  //Proportional Constant 
  private final float AIM_KP = Constants.LIMELIGHT_AIM_KP;
  private final float DISTANCE_KP = Constants.LIMELIGHT_DISTANCE_KP;
  private final float DEADBAND = Constants.LIMELIGHT_AIM_MIN_DEADBAND;
  private float steering_adjust = 0.0f;
  private float distance_adjust = 0.0f;


  private float left_command = 0;
  private float right_command = 0;

  public AimChassisCmd(DriveSstm sstm) {
    m_driveSstm = sstm;
    addRequirements(sstm);
  }

  @Override
  public void execute() {
    float heading_error = -((float) tx.getAsDouble());
    float distance_error = -((float) ty.getAsDouble());
    SmartDashboard.putNumber("tx", heading_error);
    SmartDashboard.putNumber("ty", distance_error);

    if (-heading_error > 0) {
      steering_adjust = (AIM_KP * heading_error) - DEADBAND;
    } else if (-heading_error < 0) {
      steering_adjust = (AIM_KP * heading_error) + DEADBAND;
    }
    distance_adjust = DISTANCE_KP * distance_error;

    left_command = -steering_adjust + distance_adjust;
    right_command = steering_adjust + distance_adjust;
    m_driveSstm.tankDrive(left_command, right_command);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSstm.tankDrive(0, 0);
  }
}
