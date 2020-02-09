/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TunnelSstm;

public class ShootCmd extends CommandBase {
  
  private TunnelSstm m_tunnelSstm;

  public ShootCmd(TunnelSstm sstm) {
    m_tunnelSstm = sstm;
    addRequirements(sstm);
  }

  @Override
  public void initialize() {
    m_tunnelSstm.startShooter();
  }

  @Override
  public void end(boolean interrupted) {
    m_tunnelSstm.stopShooter();
  }

}
