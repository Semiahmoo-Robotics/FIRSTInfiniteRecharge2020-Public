/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSstm extends SubsystemBase {
  
private final Solenoid m_extenderSolenoid = new Solenoid(Constants.EXTENDER_PISTONS_PORT);
private final DoubleSolenoid m_climberDoubleSolenoid = new DoubleSolenoid(Constants.CLIMB_PISTONS_FWD_PORT, Constants.CLIMB_PISTONS_REV_PORT);


  public ClimberSstm() {

  }

  public void extendExtenders() {
    m_extenderSolenoid.set(true);
  }

  public void extendClimbers() {
    m_climberDoubleSolenoid.set(Value.kForward);
  }

  public void retractClimbers() {
    m_climberDoubleSolenoid.set(Value.kReverse);
  }

  public void setupClimb() {
    m_extenderSolenoid.set(true);
    try {
      Thread.sleep((long)(55)); //55 is the number when tested on high pressure.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    m_climberDoubleSolenoid.set(Value.kForward);
  } 
}
