/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class PreciseRobotDriveCmd extends CommandBase {

  private final DriveSstm m_DriveSstm;

  public PreciseRobotDriveCmd(DriveSstm sstm) {
    m_DriveSstm = sstm;
  }

  @Override
  public void initialize() {
    m_DriveSstm.setMaxOutput(Constants.PRECISION_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    m_DriveSstm.setMaxOutput(Constants.NORMAL_SPEED);
  }

}
