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
  
  private final Spark m_lSpark = new Spark(Constants.L_TUNNEL_PORT);
  private final Spark m_rSpark = new Spark(Constants.R_TUNNEL_PORT);


  public TunnelSstm() {

  }

  public void startTunnel() {
    m_lSpark.set(0.4);
    m_rSpark.set(0.4);
  }

  public void stopTunnel() {
    m_lSpark.stopMotor();
    m_rSpark.stopMotor();
  }

}
