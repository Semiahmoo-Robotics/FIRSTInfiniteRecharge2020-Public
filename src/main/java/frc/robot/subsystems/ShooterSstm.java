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

public class ShooterSstm extends SubsystemBase {

  //Shooter
  private final Spark m_lShooterSpark = new Spark(Constants.L_SHOOTER_PORT);
  private final Spark m_rShooterSpark = new Spark(Constants.R_SHOOTER_PORT);

  public ShooterSstm() {
    m_lShooterSpark.setInverted(true);
    m_rShooterSpark.setInverted(false);
  }

  //Shooter Functions
  public void setShooter(double speed) {
    m_lShooterSpark.set(speed);
    m_rShooterSpark.set(speed);
  }

  public void stopShooter() {
    m_lShooterSpark.stopMotor();
    m_rShooterSpark.stopMotor();
  }

  public void setLeft(double speed) {
    m_lShooterSpark.set(speed);
  }

  public void setRight(double speed) {
    m_rShooterSpark.set(speed);
  }

}
