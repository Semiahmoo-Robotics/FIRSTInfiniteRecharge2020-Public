/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TunnelSstm;

public class TunnelCmd extends CommandBase {

  private TunnelSstm m_launcherSstm;
  private IntSupplier POVdirection;

  public TunnelCmd(TunnelSstm sstm, IntSupplier POVdirection) {
    this.m_launcherSstm = sstm;
    this.POVdirection = POVdirection;
    addRequirements(sstm);
  }

  @Override
  public void execute() {
    switch (POVdirection.getAsInt()) {
      case 0:
        m_launcherSstm.setTunnel(Constants.TUNNEL_SPEED);
      break;
      case 180:
        m_launcherSstm.setTunnel(-Constants.TUNNEL_SPEED);
      break;
      default:
        m_launcherSstm.stopTunnel();
      break;
    }
  }

}
