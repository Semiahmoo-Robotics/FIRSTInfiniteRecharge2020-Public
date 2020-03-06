/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TunnelSstm extends SubsystemBase {

  //Tunnel
  private final Spark m_tunnelSpark = new Spark(Constants.TUNNEL_PORT);

  public TunnelSstm() {
    m_tunnelSpark.setInverted(true);
  }

  //Tunnel Functions
  public void setTunnel(double speed) {
    m_tunnelSpark.set(speed);
  }

  public void stopTunnel() {
    m_tunnelSpark.stopMotor();
  }

}
