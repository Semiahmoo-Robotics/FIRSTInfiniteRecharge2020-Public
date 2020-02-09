/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TunnelSstm;

public class IntakeCmd extends CommandBase {  

  private TunnelSstm m_TunnelSstm;

  public IntakeCmd(TunnelSstm m_TunnelSstm) {
    this.m_TunnelSstm = m_TunnelSstm;
    addRequirements(m_TunnelSstm);
  }

  @Override
  public void initialize() {
    m_TunnelSstm.startIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_TunnelSstm.getDistance() <= 30) {
      m_TunnelSstm.startTunnel();
    } else {
      m_TunnelSstm.stopTunnel();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_TunnelSstm.stopIntake();
  }
}
