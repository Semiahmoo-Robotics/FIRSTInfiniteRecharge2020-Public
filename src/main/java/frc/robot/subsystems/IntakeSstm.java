/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSstm extends SubsystemBase {
  
  private final Spark m_intakeSpark = new Spark(Constants.INTAKE_PORT);
  private final DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(Constants.INTAKE_PISTONS_FWD_PORT, Constants.INTAKE_PISTONS_REV_PORT);

  public IntakeSstm() {

  }

  public void extend() {
    m_DoubleSolenoid.set(Value.kForward);
  }

  public void retract() {
    m_DoubleSolenoid.set(Value.kReverse);
  }

  public void startIntake() {
    m_intakeSpark.set(0.4);
  }

  public void stopIntake() {
    m_intakeSpark.stopMotor();
  }

  public void reverseIntake() {
    m_intakeSpark.set(-0.2);
  }
}
