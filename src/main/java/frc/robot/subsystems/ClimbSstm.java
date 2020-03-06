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

public class ClimbSstm extends SubsystemBase {

  private final Spark m_climberSpark = new Spark(Constants.CLIMB_PORT);

  public ClimbSstm() {

  }

  public void spinClimb() {
    m_climberSpark.set(Constants.CLIMB_SPEED);
  }

  public void stopAll() {
    m_climberSpark.stopMotor();
  }
}
