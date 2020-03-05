/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSstm extends SubsystemBase {

  private final Spark m_climberSpark = new Spark(Constants.CLIMB_PORT);

  //Shuffleboard Delay
  private NetworkTableEntry m_breakTime = Shuffleboard.getTab("Climb Diag").add("Break Time", 5).getEntry();


  public ClimbSstm() {

  }

  public void breakLink() {
    m_climberSpark.set(1.0);
    try {
      Thread.sleep((long)(m_breakTime.getDouble(5)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void retractClaw() {
    m_climberSpark.set(1.0);
  }

  public void stopAll() {
    m_climberSpark.stopMotor();
  }
}
