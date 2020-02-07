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

  private final Spark m_lSpark = new Spark(Constants.L_LAUNCHER_PORT);
  private final Spark m_rSpark = new Spark(Constants.R_LAUNCHER_PORT);

  public LauncherSstm() {
    m_lSpark.setInverted(true);
  }

  public void startLauncher() {
    m_lSpark.set(1.0);
    m_rSpark.set(1.0);
  }

  public void stopLauncher() {
    m_lSpark.stopMotor();
    m_rSpark.stopMotor();
  }

  public void setLeft(double n) {
    m_lSpark.set(n);
  }

  public void setRight(double n) {
    m_rSpark.set(n);
  }

}
