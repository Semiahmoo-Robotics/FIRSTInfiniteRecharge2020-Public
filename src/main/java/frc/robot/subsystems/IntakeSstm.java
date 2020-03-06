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

public class IntakeSstm extends SubsystemBase {

  //Intake
  private final Spark m_intakeSpark = new Spark(Constants.INTAKE_PORT);

  public IntakeSstm() {
    m_intakeSpark.setInverted(false);
  }

  //Intake Functions
  public void setIntake(double speed) {
    m_intakeSpark.set(speed);
  }

  public void stopIntake() {
    m_intakeSpark.stopMotor();
  }

}
