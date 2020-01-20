/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class AimChassisCmd extends CommandBase {

  private final DriveSstm m_driveSstm;
  private final BooleanSupplier m_isHoldingButton;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  //Proportional Constant 
  final float KP = Constants.LIMELIGHT_AIM_KP;
  final float DEADBAND = Constants.LIMELIGHT_AIM_MIN_DEADBAND;


  public AimChassisCmd(DriveSstm sstm, BooleanSupplier isHoldingButton) {
    m_driveSstm = sstm;
    m_isHoldingButton = isHoldingButton;
    addRequirements(sstm);
  }

  @Override
  public void execute() {
    float tx  = (float) table.getEntry("tx").getDouble(0);
    float heading_error = -tx;
    float steering_adjust = 0.0f;
    if (tx > 1.0) {
      steering_adjust = KP * heading_error - DEADBAND;
    } else if (tx < 1.0) {
      steering_adjust = KP*heading_error + DEADBAND;
    }
    m_driveSstm.tankDrive(steering_adjust, -steering_adjust);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSstm.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_isHoldingButton.getAsBoolean();
  }
}
