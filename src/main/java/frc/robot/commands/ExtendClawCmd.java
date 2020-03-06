/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSstm;

public class ExtendClawCmd extends CommandBase {

  private final ClimbSstm m_climbSstm;

  public ExtendClawCmd(ClimbSstm subsystem) {
    this.m_climbSstm = subsystem;
    addRequirements(m_climbSstm);
  }

  @Override
  public void initialize() {
    m_climbSstm.spinClimb();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSstm.stopAll();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
