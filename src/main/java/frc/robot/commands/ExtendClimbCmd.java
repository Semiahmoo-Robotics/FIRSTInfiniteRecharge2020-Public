/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSstm;

public class ExtendClimbCmd extends CommandBase {

  private final ClimberSstm m_ClimberSstm;

  public ExtendClimbCmd(ClimberSstm m_ClimberSstm) {
    this.m_ClimberSstm = m_ClimberSstm;
    addRequirements(m_ClimberSstm);
  }

  @Override
  public void initialize() {
    m_ClimberSstm.setupClimb();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
