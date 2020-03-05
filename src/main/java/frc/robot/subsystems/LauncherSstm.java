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

public class LauncherSstm extends SubsystemBase {

  //Launcher
  private final Spark m_lLauncherSpark = new Spark(Constants.L_LAUNCHER_PORT);
  private final Spark m_rLauncherSpark = new Spark(Constants.R_LAUNCHER_PORT);

  //Tunnel
  private final Spark m_tunnelSpark = new Spark(Constants.TUNNEL_PORT);

  //Intake
  private final Spark m_intakeSpark = new Spark(Constants.INTAKE_PORT);

  public LauncherSstm() {
    m_rLauncherSpark.setInverted(true);
    m_tunnelSpark.setInverted(true);
    m_intakeSpark.setInverted(false);
  }

  //Launcher Functions
  public void setLauncher(double speed) {
    m_lLauncherSpark.set(speed);
    m_rLauncherSpark.set(speed);
  }

  public void stopLauncher() {
    m_lLauncherSpark.stopMotor();
    m_rLauncherSpark.stopMotor();
  }

  public void setLeft(double speed) {
    m_lLauncherSpark.set(speed);
  }

  public void setRight(double speed) {
    m_rLauncherSpark.set(speed);
  }

  //Tunnel Functions
  public void setTunnel(double speed) {
    m_tunnelSpark.set(speed);
  }

  public void stopTunnel() {
    m_tunnelSpark.stopMotor();
  }

  //Intake Functions
  public void setIntake(double speed) {
    m_intakeSpark.set(speed);
  }

  public void stopIntake() {
    m_intakeSpark.stopMotor();
  }

}
